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

    JogoController jogoController;

    public static void main(String[] args) {
        JogoController jogoController = new JogoController();

        System.out.println("Batalha Terrestre");

        JOptionPane.showInputDialog("Jogador 1");
        JOptionPane.showInputDialog("Jogador 2");

        Jogo jogo = new Jogo(10, new Jogador("a"), new Jogador("b"));
        jogoController.addJogo(jogo);
        jogo.getEstrategia1().addArma(new Guarani());
        jogo.getEstrategia1().addArma(new Mina());
        jogo.getEstrategia1().addArma(new Astros2020());

        //jogador 1 plantar minas
        
        jogo.getEstrategia1().dispoeArmas();
    }
}
