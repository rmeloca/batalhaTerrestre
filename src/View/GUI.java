/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Tabuleiro.Grelha;
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

        JPanel jPanelMenu = new JPanel(new GridLayout(3, 1));
        jPanelMenu.add(new JButton());
        jPanelMenu.add(new JButton());
        jPanelMenu.add(new JButton());

        JPanel jPanel = new JPanel(new GridLayout(1, 3));
        setContentPane(jPanel);

        jPanel.add(new painelGrelha(new Grelha(10)));
        jPanel.add(jPanelMenu);
        jPanel.add(new painelGrelha(new Grelha(10)));

        setResizable(false);
        setSize(1300, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }
}
