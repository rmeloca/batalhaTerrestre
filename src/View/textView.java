/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.JogoController;
import Jogo.Alvo.*;
import Jogo.Jogador;
import Jogo.Jogo;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Grelha;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class textView {

    private static JogoController jogoController;

    public static void main(String[] args) {
        jogoController = new JogoController();

        System.out.println("Batalha Terrestre");

        Jogador jogador1 = new Jogador(JOptionPane.showInputDialog("Jogador 1"));
        Jogador jogador2 = new Jogador(JOptionPane.showInputDialog("Jogador 2"));

        Jogo jogo = new Jogo(10, jogador1, jogador2);
        jogoController.addJogo(jogo);

        imprimirGrelha(jogo.getEstrategia1().getGrelha());
        System.out.println();
        imprimirGrelha(jogo.getEstrategia2().getGrelha());
        
        JOptionPane.showInputDialog("jog 1 escolha arma");

        jogo.getEstrategia1().addArma(new Guarani());
        jogo.getEstrategia1().addArma(new M15());
        jogo.getEstrategia1().addArma(new M15());
        jogo.getEstrategia1().addArma(new Astros2020());
        jogo.getEstrategia1().addArma(new Astros2020());
        jogo.getEstrategia1().addArma(new M60Patton());
        jogo.getEstrategia1().addArma(new M60Patton());
        jogo.getEstrategia1().addArma(new L118());

        jogo.getEstrategia1().dispoeArmas();
        
        System.out.println();
        imprimirGrelha(jogo.getEstrategia1().getGrelha());

    }

    public static void imprimirGrelha(Grelha grelha) {
        for (Campo[] linha : grelha.getCampos()) {
            for (Campo campo : linha) {
                System.out.print(campo.getObjeto().toString() + " ");
            }
            System.out.println();
        }
    }

}
