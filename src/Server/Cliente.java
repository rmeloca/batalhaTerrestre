/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Jogo.Alvo.Astros2020;
import Jogo.Alvo.Guarani;
import Jogo.Alvo.L118;
import Jogo.Alvo.M15;
import Jogo.Alvo.M60Patton;
import Jogo.Estrategia;
import Jogo.Jogador;
import Jogo.Jogo;
import static View.TextView.imprimirGrelha;
import static View.TextView.imprimirJogo;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 12345);

            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

            Jogador jogador2 = new Jogador(JOptionPane.showInputDialog("Seu nome"));

            saida.flush();
            saida.writeObject(jogador2);

            Estrategia estrategia2;

            System.out.println(jogador2.getNome() + ", escolha suas armas");
            estrategia2 = new Estrategia(jogador2);
            estrategia2.addArma(new Guarani());
            estrategia2.addArma(new M15());
            estrategia2.addArma(new M15());
            estrategia2.addArma(new Astros2020());
            estrategia2.addArma(new M60Patton());
            estrategia2.addArma(new M60Patton());
            estrategia2.addArma(new L118());

            System.out.print("Movendo arsenal...");
            estrategia2.dispoeArmas();
            System.out.println("Arsenal implantado");
            System.out.println(jogador2.getNome() + ", comande suas tropas");
            System.out.println(jogador2.getNome() + ", ajuste seus explosivos");

            saida.flush();
            saida.writeObject(estrategia2);

            imprimirGrelha(estrategia2.getGrelha());

            Jogo jogo = (Jogo) entrada.readObject();

            System.out.println("Você detonou armas inimigas com sua M15");
            System.out.println("Você sofreu baixas");
            imprimirJogo(jogo);

            JOptionPane.showInputDialog("Qual coordenada, comandante " + jogador2.getNome() + "?");

            saida.close();
            entrada.close();
            System.out.println("Conexão encerrada");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
