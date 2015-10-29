/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author a1137131
 */
public class GUI extends JFrame {

    JButton[][] campos;
    ActionListener campoActionListener;

    public GUI() {

        campoActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        int dimensao = 10;
        campos = new JButton[dimensao][dimensao];

        JPanel jPanelTabuleiro = new JPanel(new GridLayout(dimensao, dimensao));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                campos[i][j] = new JButton();
                campos[i][j].addActionListener(campoActionListener);
                jPanelTabuleiro.add(campos[i][j]);
            }
        }
        
        JPanel jPanelMenu = new JPanel(new GridLayout(3, 1));
        jPanelMenu.add(campos[0][0]);
        jPanelMenu.add(campos[0][1]);
        jPanelMenu.add(campos[0][2]);

        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        setContentPane(jPanel);

        jPanel.add(jPanelMenu);
        jPanel.add(jPanelTabuleiro);

        setResizable(false);
        setSize(700, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }
}
