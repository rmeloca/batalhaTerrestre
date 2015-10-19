/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author a1137131
 */
public class GUI extends JFrame {

    JButton[][] campos;

    public GUI() {
        while (true) {
            while (true) {
                campos[1][2] = new JButton();
                campos[1][2].addActionListener(campoActionListener.actionPerformed(e));
            }
        }

        campos[1][2].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
        );

    }
    ActionListener campoActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
