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
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class textView {

    private static JogoController jogoController;

    public static void main(String[] args) {

        System.out.println("Batalha Terrestre");

        Jogador jogador1 = new Jogador(JOptionPane.showInputDialog("Jogador 1"));
        Jogador jogador2 = new Jogador(JOptionPane.showInputDialog("Jogador 2"));

        Jogo jogo = new Jogo(10, jogador1, jogador2);
        jogoController.addJogo(jogo);
        
//        JOptionPane.showOptionDialog(null, "Escolha arma", null, 1, 2, null, , armas.get(0));
        jogo.getEstrategia1().addArma(new Guarani());
        jogo.getEstrategia1().addArma(new Mina());
        jogo.getEstrategia1().addArma(new Astros2020());

        //jogador 1 plantar minas
        jogo.getEstrategia1().dispoeArmas();
        System.out.println("a");
    }
}
