/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Tabuleiro.Grelha;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author romulo
 */
public class painelGrelha extends JPanel {

    JButton[][] campos;
    ActionListener campoActionListener;

    public painelGrelha(Grelha grelha) {

        int dimensao = grelha.getDimensao();
        setLayout(new GridLayout(dimensao, dimensao));
        campos = new JButton[dimensao][dimensao];
        
        campoActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                campos[i][j] = new JButton();
                campos[i][j].addActionListener(campoActionListener);
                campos[i][j].setIcon(null);
                campos[i][j].setText("T");
                add(campos[i][j]);
            }
        }

    }

}
