/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Tabuleiro.Grelha;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author a1137131
 */
public class GUI extends JFrame {

    public GUI() {
        JPanel painelPrincipal;
        JPanel jPanelMenuWest;
        JPanel jPanelConfronto;

        painelPrincipal = new JPanel(new BorderLayout());

        setContentPane(painelPrincipal);
        setTitle("Batalha Terrestre");

        jPanelMenuWest = new JPanel(new GridLayout(3, 1));
        jPanelMenuWest.add(new JButton());
        jPanelMenuWest.add(new JButton());
        jPanelMenuWest.add(new JButton());

        jPanelConfronto = new JPanel(new GridLayout(1, 2));
        jPanelConfronto.add(new painelGrelha(new Grelha(10)));
        jPanelConfronto.add(new painelGrelha(new Grelha(10)));

        painelPrincipal.add(jPanelConfronto, BorderLayout.CENTER);
        painelPrincipal.add(jPanelMenuWest, BorderLayout.WEST);

        setResizable(false);
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
