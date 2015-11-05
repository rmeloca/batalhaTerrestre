/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Alvo.Guarani;
import Jogo.Alvo.Terra;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import Jogo.Tabuleiro.Grelha;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author romulo
 */
public class PainelGrelha extends JPanel {

    JButton[][] campos;
    ActionListener campoActionListener;

    public PainelGrelha(Grelha grelha) {
        int dimensao = grelha.getDimensao();
        setLayout(new GridLayout(dimensao, dimensao));
        campos = new JButton[dimensao][dimensao];

        campoActionListener = new ActionListener() {
            JButton btnCampo;
            Campo campo;

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] action;
                List<Campo> camposSelecionados;
                Guarani guarani = new Guarani();
                Coordenada coordenada;

                camposSelecionados = new ArrayList<>();

                action = e.getActionCommand().split(",");
                coordenada = new Coordenada(Integer.parseInt(action[0]), Integer.parseInt(action[1]));
                campo = grelha.getCampo(coordenada);
                camposSelecionados.add(campo);

                guarani.atirar(camposSelecionados);
                btnCampo = campos[coordenada.getX()][coordenada.getY()];
                btnCampo.setEnabled(false);
                btnCampo.setText(campo.getObjeto().toString());

            }
        };

        Terra terra = new Terra();
        Campo campo;
        JButton btnCampo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = new JButton();
                campos[i][j] = btnCampo;
                campo = grelha.getCampos()[i][j];
                btnCampo.addActionListener(campoActionListener);
                btnCampo.setActionCommand(i + "," + j);
                btnCampo.setIcon(null);
                if (campo.foiAtirado()) {
                    btnCampo.setText(campo.getObjeto().toString());
                } else {
                    btnCampo.setText(terra.toString());
                }
                btnCampo.setBackground(Color.GRAY);
                add(btnCampo);
            }
        }
    }

}
