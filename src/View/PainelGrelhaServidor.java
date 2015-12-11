/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
import java.io.ObjectInputStream;
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
public class PainelGrelhaServidor extends JPanel {

    JButton[][] campos;
    ActionListener campoActionListener;
    Grelha grelha;
    FrameConfrontoServidor frameConfronto;

    public PainelGrelhaServidor(Grelha grelha) {
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
                ObjectInputStream entrada;
                try {
                    saida = new ObjectOutputStream(frameConfronto.cliente.getOutputStream());
                    entrada = new ObjectInputStream(frameConfronto.cliente.getInputStream());

                    String[] action;
                    List<Campo> camposSelecionados;
                    Guarani guarani = new Guarani();
                    Coordenada coordenada;

                    camposSelecionados = new ArrayList<>();

                    action = e.getActionCommand().split(",");
                    coordenada = new Coordenada(Integer.parseInt(action[0]), Integer.parseInt(action[1]));
                    campo = grelha.getCampo(coordenada);
                    camposSelecionados.add(campo);

                    boolean acertou;
                    int retorno;
                    Jogador jogador;
                    retorno = guarani.atirar(camposSelecionados);

                    acertou = retorno > 0 || retorno == -1;

                    btnCampo = campos[coordenada.getX()][coordenada.getY()];
                    btnCampo.setEnabled(false);
                    btnCampo.setBackground(Color.red);
                    btnCampo.setText(campo.getObjeto().toString());
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem())));

                    saida.flush();
                    saida.writeObject(camposSelecionados);

                    jogador = frameConfronto.jogo.getJogadorProximaRodada(acertou);

                    if (frameConfronto.jogo.haVencedor()) {
                        frameConfronto.painelMinhaGrelha.desabilitarGrelha();
                        frameConfronto.painelGrelhaInimiga.desabilitarGrelha();
                        if (JOptionPane.showConfirmDialog(null, jogador.getNome() + " Venceu!") == JOptionPane.OK_OPTION) {
                            frameConfronto.dispose();
                            new GUI();
                        }
                    }
                    if (jogador.equals(frameConfronto.jogo.getEstrategia2().getJogador())) {
                        camposSelecionados = (List<Campo>) entrada.readObject();
                        frameConfronto.atualizarToolBar(frameConfronto.jogo.getEstrategia1());
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(PainelGrelhaServidor.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setFrameConfronto(FrameConfrontoServidor painelConfronto) {
        this.frameConfronto = painelConfronto;
    }

    protected void atualizarGrelha() {
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

    protected void desabilitarGrelha() {
        JButton btnCampo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = campos[i][j];
                btnCampo.setEnabled(false);
            }
        }
    }

    protected void habilitarGrelha() {
        JButton btnCampo;
        for (int i = 0; i < grelha.getDimensao(); i++) {
            for (int j = 0; j < grelha.getDimensao(); j++) {
                btnCampo = campos[i][j];
                btnCampo.setEnabled(true);
            }
        }
    }
}
