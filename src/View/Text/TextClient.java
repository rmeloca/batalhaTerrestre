/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Text;

import Jogo.Alvo.Astros2020;
import Jogo.Alvo.Guarani;
import Jogo.Alvo.L118;
import Jogo.Alvo.M15;
import Jogo.Alvo.M60Patton;
import Jogo.Estrategia;
import Jogo.Jogador;
import Jogo.Jogo;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import static View.Text.TextView.imprimirGrelha;
import static View.Text.TextView.imprimirJogo;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class TextClient {

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

            Guarani guarani = new Guarani();
            List<Campo> campos = new ArrayList<>();
            while (!jogo.haVencedor()) {
                String coordenadaStr[] = JOptionPane.showInputDialog("Qual coordenada, comandante " + jogador2.getNome() + "?").split(" ");
                Coordenada coordenada = new Coordenada(Integer.parseInt(coordenadaStr[0]), Integer.parseInt(coordenadaStr[1]));
                saida.flush();
                saida.writeObject(coordenada);
                campos.clear();
                campos.add(jogo.getEstrategia1().getGrelha().getCampo(coordenada));
                boolean acertou = guarani.atirar(campos) > 0;
                while (jogo.getJogadorProximaRodada(acertou).equals(jogador2)) {

                }
            }

            saida.close();
            entrada.close();
            System.out.println("Conexão encerrada");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
