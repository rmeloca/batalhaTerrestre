/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Alvo.Terra;
import Jogo.Tabuleiro.Campo;
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

        Terra terra = new Terra();
        Campo campo;
        JButton btnCampo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = campos[i][j];
                campo = grelha.getCampos()[i][j];
                btnCampo = new JButton();
                btnCampo.addActionListener(campoActionListener);
                btnCampo.setIcon(null);
                if (campo.foiAtirado()) {
                    btnCampo.setText(campo.getObjeto().toString());
                } else {
                    btnCampo.setText(terra.toString());
                }
                add(btnCampo);
            }
        }

    }

}
