/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controller.Transmissao;
import Jogo.Estrategia;
import Jogo.Jogo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket servidor;
        ObjectInputStream entradaJogador1;
        ObjectInputStream entradaJogador2;
        ObjectOutputStream saidaJogador1;
        ObjectOutputStream saidaJogador2;
        try {
            servidor = new ServerSocket(12345);
            System.out.println("Servidor ouvindo a porta 12345");
            while (true) {
                Socket jogador1 = servidor.accept();
                Socket jogador2 = servidor.accept();

                saidaJogador1 = new ObjectOutputStream(jogador1.getOutputStream());
                entradaJogador1 = new ObjectInputStream(jogador1.getInputStream());

                saidaJogador2 = new ObjectOutputStream(jogador2.getOutputStream());
                entradaJogador2 = new ObjectInputStream(jogador2.getInputStream());

                Estrategia estrategia1 = (Estrategia) entradaJogador1.readObject();
                Estrategia estrategia2 = (Estrategia) entradaJogador2.readObject();

                estrategia1.getJogador().setId(0);
                estrategia2.getJogador().setId(1);

                Jogo jogo = new Jogo(10);
                jogo.setEstrategia1(estrategia1);
                jogo.setEstrategia2(estrategia2);

                jogo.inicializar();

                saidaJogador1.flush();
                saidaJogador1.writeObject(jogo);

                Estrategia auxiliar = estrategia1;
                jogo.setEstrategia1(estrategia2);
                jogo.setEstrategia2(auxiliar);
                jogo.bloqueado = true;

                saidaJogador2.flush();
                saidaJogador2.writeObject(jogo);

                Transmissao transmissao1 = new Transmissao(entradaJogador1, saidaJogador2);
                Transmissao transmissao2 = new Transmissao(entradaJogador2, saidaJogador1);

                transmissao1.start();
                transmissao2.start();

                transmissao1.join();
                transmissao2.join();
                break;
            }

            servidor.close();
            System.out.println("Servidor fechado");
        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
