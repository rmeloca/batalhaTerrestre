/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Alvo.CarroCombate;
import Jogo.Estrategia;
import Jogo.Jogo;
import Jogo.Tabuleiro.Campo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author romulo
 */
public class FrameConfrontoCliente extends JFrame {

    protected PainelGrelhaCliente painelGrelhaInimiga;
    protected PainelGrelha painelMinhaGrelha;
    protected JToolBar toolBar;
    protected Jogo jogo;
    protected static Socket cliente;

    public FrameConfrontoCliente(Socket cliente, Jogo jogo) {
        setTitle("Confronto Cliente");

        JPanel painelPrincipal;

        setLayout(new BorderLayout());
        painelPrincipal = new JPanel();
        toolBar = new JToolBar();

        this.cliente = cliente;
        this.jogo = jogo;

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelMinhaGrelha = new PainelGrelha(jogo.getEstrategia2().getGrelha());
        painelGrelhaInimiga = new PainelGrelhaCliente(jogo.getEstrategia1().getGrelha());

        painelMinhaGrelha.setFrameConfronto(null);
        painelGrelhaInimiga.setFrameConfronto(this);

        painelPrincipal.add(painelMinhaGrelha);
        painelPrincipal.add(painelGrelhaInimiga);

        painelGrelhaInimiga.atualizarGrelha();
        painelGrelhaInimiga.desabilitarGrelha();
        painelMinhaGrelha.desabilitarGrelha();

        atualizarToolBar(jogo.getEstrategia1());

        add(painelPrincipal, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    protected void atualizarToolBar(Estrategia estrategia) {
        this.remove(toolBar);
        toolBar = new JToolBar();
        this.add(toolBar, BorderLayout.NORTH);
        JButton jButton;
        toolBar.add(new JLabel(estrategia.getJogador().getNome()));
        for (CarroCombate carroCombate : estrategia.getCarrosCombate()) {
            jButton = new JButton();
            jButton.setIcon(new ImageIcon(getClass().getResource(carroCombate.getImagem())));
            toolBar.add(jButton);
            if (!carroCombate.isAtiva()) {
                jButton.setEnabled(false);
            }
        }
    }
}
