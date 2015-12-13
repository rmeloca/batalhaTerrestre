/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.StandAlone;

import Model.Historico;
import DAO.DAOHistorico;
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author romulo
 */
public class PainelGrelha extends JPanel {

    JButton[][] campos;
    ActionListener campoActionListener;
    Grelha grelha;
    FrameConfronto frameConfronto;

    public PainelGrelha(Grelha grelha) {
        this.grelha = grelha;
        int dimensao = grelha.getDimensao();
        setLayout(new GridLayout(dimensao, dimensao));
        campos = new JButton[dimensao][dimensao];
        DAOHistorico daoHistorico = new DAOHistorico();

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

                int retorno = guarani.atirar(camposSelecionados);
                boolean acertou = retorno > 0 || retorno == -1;

                Jogador jogador = frameConfronto.jogo.getJogadorProximaRodada(acertou);
                btnCampo = campos[coordenada.getX()][coordenada.getY()];
                btnCampo.setEnabled(false);
                btnCampo.setBackground(Color.red);
                btnCampo.setText(campo.getObjeto().toString());
                btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem())));

                if (frameConfronto.jogo.haVencedor()) {
                    Historico historico = new Historico();
                    historico.setJogador1(frameConfronto.jogo.getEstrategiaMinha().getJogador().getNome());
                    historico.setJogador2(frameConfronto.jogo.getEstrategiaInimiga().getJogador().getNome());
                    historico.setVencedor(jogador.getNome());
                    daoHistorico.inserir(historico);

                    List<Historico> lista = daoHistorico.list();
                    String resultado = "";
                    for (Historico historico1 : lista) {
                        resultado += "Jogador1: ";
                        resultado += historico1.getJogador1();
                        resultado += "\n";
                        resultado += "Jogador2: ";
                        resultado += historico1.getJogador2();
                        resultado += "\n";
                        resultado += "Vencedor: ";
                        resultado += historico1.getVencedor();
                        resultado += "\n";
                        resultado += "\n";
                    }

                    JOptionPane.showMessageDialog(null, resultado);

                    frameConfronto.painelGrelha1.desabilitarGrelha();
                    frameConfronto.painelGrelha2.desabilitarGrelha();
                    if (JOptionPane.showConfirmDialog(null, jogador.getNome() + " Venceu!\nRevanche?") == JOptionPane.OK_OPTION) {
                        frameConfronto.dispose();
                        new GUI();
                    }
                } else if (jogador.equals(frameConfronto.jogo.getEstrategiaMinha().getJogador())) {
                    frameConfronto.painelGrelha1.atualizarGrelha();
                    frameConfronto.painelGrelha2.desabilitarGrelha();
                } else {
                    frameConfronto.painelGrelha2.atualizarGrelha();
                    frameConfronto.painelGrelha1.desabilitarGrelha();
                }
                frameConfronto.atualizarToolBar(jogador);
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
                    btnCampo.setEnabled(false);
                    btnCampo.setBackground(Color.BLUE);
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(campo.getObjeto().getImagem())));
                } else {
                    btnCampo.setText(terra.toString());
                    btnCampo.setBackground(Color.GRAY);
                    btnCampo.setIcon(new ImageIcon(getClass().getResource(terra.getImagem())));
                }
                add(btnCampo);
            }
        }
    }

    public void setFrameConfronto(FrameConfronto painelConfronto) {
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
}
