/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cliente;

import Jogo.Alvo.Guarani;
import Jogo.Alvo.Terra;
import Jogo.Jogador;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import Jogo.Tabuleiro.Grelha;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author romulo
 */
public class PainelGrelhaCliente extends JPanel {

    public JButton[][] campos;
    ActionListener campoActionListener;
    public Grelha grelha;
    FrameConfrontoCliente frameConfronto;

    public PainelGrelhaCliente(Grelha grelha) {
        this.grelha = grelha;
        int dimensao = grelha.getDimensao();
        setLayout(new GridLayout(dimensao, dimensao));
        campos = new JButton[dimensao][dimensao];

        campoActionListener = new ActionListener() {
            JButton btnCampo;
            Campo campo;

            @Override
            public void actionPerformed(ActionEvent e) {
                ObjectOutputStream saida;
                try {
                    saida = FrameConfrontoCliente.outputStream;

                    String[] action;
                    List<Campo> camposSelecionados;
                    Guarani guarani = new Guarani();
                    Coordenada coordenada;

                    camposSelecionados = new ArrayList<>();

                    action = e.getActionCommand().split(",");
                    coordenada = new Coordenada(Integer.parseInt(action[0]), Integer.parseInt(action[1]));

                    saida.flush();
                    saida.writeObject(coordenada);

                    campo = grelha.getCampo(coordenada);
                    camposSelecionados.add(campo);

                    boolean acertou;
                    int retorno;
                    Jogador jogador;
                    retorno = guarani.atirar(camposSelecionados);

                    acertou = retorno > 0 || retorno == -1;

                    frameConfronto.painelGrelhaInimiga.atualizarGrelhaInimiga();

                    jogador = frameConfronto.jogo.getJogadorProximaRodada(acertou);

                    if (frameConfronto.jogo.haVencedor()) {
                        frameConfronto.painelGrelhaInimiga.desabilitarGrelha();
                        frameConfronto.painelGrelhaMinha.desabilitarGrelha();
                        if (JOptionPane.showConfirmDialog(null, jogador.getNome() + " Venceu!\nRevanche?") == JOptionPane.OK_OPTION) {
                            frameConfronto.dispose();
                            new GUICliente();
                        }
                    }
                    if (!acertou) {
                        desabilitarGrelha();
                        //aguarda thread de escuta permitir que eu jogue
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PainelGrelhaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Campo campo;
        JButton btnCampo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = new JButton();
                campos[i][j] = btnCampo;
                campo = grelha.getCampos()[i][j];
                btnCampo.addActionListener(campoActionListener);
                btnCampo.setActionCommand(i + "," + j);
                btnCampo.setText(campo.getObjeto().toString());
                btnCampo.setEnabled(false);
                btnCampo.setBackground(Color.BLUE);
                btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem())));
                add(btnCampo);
            }
        }
    }

    public void setFrameConfronto(FrameConfrontoCliente painelConfronto) {
        this.frameConfronto = painelConfronto;
    }

    public void atualizarGrelhaInimiga() {
        Terra terra = new Terra();
        JButton btnCampo;
        Campo campo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = campos[i][j];
                campo = grelha.getCampos()[i][j];
                if (campo.isAtiravel()) {
                    btnCampo.setEnabled(true);
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(terra.getImagem())));
                } else {
                    btnCampo.setEnabled(false);
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem())));
                }
            }
        }
    }

    public void desabilitarGrelha() {
        JButton btnCampo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = campos[i][j];
                btnCampo.setEnabled(false);
            }
        }
    }

    public void atualizarMinhaGrelha() {
        JButton btnCampo;
        Campo campo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = campos[i][j];
                campo = grelha.getCampos()[i][j];
                btnCampo.setEnabled(true);
                btnCampo.removeActionListener(campoActionListener);
                if (campo.foiAtirado()) {
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem() + ".atirado.png")));
                } else {
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem())));
                }
            }
        }
    }
}
