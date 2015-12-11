/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Controller.JogoController;
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
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class Servidor {

    private static JogoController jogoController;

    public static void main(String[] args) {

        jogoController = new JogoController();

        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor ouvindo a porta 12345");

            System.out.println("Batalha Terrestre");
            System.out.print("Colhendo credenciais...");
            Jogador jogador1 = new Jogador(JOptionPane.showInputDialog("Seu nome"));
            System.out.println("Confirmadas");
            System.out.print("Rastreando Inimigo...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

            Jogador jogador2;
            jogador2 = (Jogador) entrada.readObject();

            System.out.println("Encontrado " + jogador2.getNome());
            System.out.print("Iniciando solução diplomática...");
            System.out.println("Diplomacia esgotada");
            System.out.println("Comandante, defenda o seu território contra as forças inimigas");

            Jogo jogo = new Jogo(10);
            jogoController.addJogo(jogo);

            System.out.println(jogador1.getNome() + ", escolha suas armas");
            jogo.setEstrategia1(new Estrategia(jogador1));
            jogo.getEstrategia1().addArma(new Guarani());
            jogo.getEstrategia1().addArma(new M15());
            jogo.getEstrategia1().addArma(new M15());
            jogo.getEstrategia1().addArma(new Astros2020());
            jogo.getEstrategia1().addArma(new Astros2020());
            jogo.getEstrategia1().addArma(new M60Patton());
            jogo.getEstrategia1().addArma(new M60Patton());
            jogo.getEstrategia1().addArma(new L118());

            System.out.print("Movendo arsenal...");
            jogo.getEstrategia1().dispoeArmas();
            System.out.println("Arsenal implantado");
            System.out.println(jogador1.getNome() + ", comande suas tropas");
            System.out.println(jogador1.getNome() + ", ajuste seus explosivos");

            imprimirGrelha(jogo.getEstrategia1().getGrelha());
            System.out.println("Aguardando embuste inimigo...");

            Estrategia estrategia2 = (Estrategia) entrada.readObject();
            jogo.setEstrategia2(estrategia2);

            System.out.println("Confronto avistado");
            System.out.print("Camuflando as tropas...");
            System.out.println("Camufladas");

            jogo.inicializar();
            saida.flush();
            saida.writeObject(jogo);

            System.out.println("Você detonou armas inimigas com sua M15");
            System.out.println("Você sofreu baixas");

            imprimirJogo(jogo);

            JOptionPane.showInputDialog("Qual coordenada, comandante " + jogador1.getNome() + "?");

            entrada.close();
            saida.close();
            cliente.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
        }
    }
}
