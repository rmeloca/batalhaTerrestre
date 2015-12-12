/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Alvo.CarroCombate;
import Jogo.Jogador;
import Jogo.Jogo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
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
public class FrameConfronto extends JFrame {

    protected Jogo jogo;
    protected PainelGrelha painelGrelha1;
    protected PainelGrelha painelGrelha2;
    protected JToolBar toolBar;

    public FrameConfronto(Jogo jogo) {
        setTitle("Confronto");

        JPanel painelPrincipal;
        
        this.jogo = jogo;
        setLayout(new BorderLayout());
        painelPrincipal = new JPanel();
        toolBar = new JToolBar();

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelGrelha1 = new PainelGrelha(jogo.getEstrategiaMinha().getGrelha());
        painelGrelha2 = new PainelGrelha(jogo.getEstrategiaInimiga().getGrelha());

        painelGrelha1.setFrameConfronto(this);
        painelGrelha2.setFrameConfronto(this);

        painelPrincipal.add(painelGrelha1);
        painelPrincipal.add(painelGrelha2);

        painelGrelha1.atualizarGrelha();
        painelGrelha2.desabilitarGrelha();
        atualizarToolBar(jogo.getEstrategiaMinha().getJogador());

        add(painelPrincipal, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    protected void atualizarToolBar(Jogador jogador) {
        this.remove(toolBar);
        toolBar = new JToolBar();
        this.add(toolBar, BorderLayout.NORTH);
        JButton jButton;
        toolBar.add(new JLabel(jogador.getNome()));
        for (CarroCombate carroCombate : jogo.getEstrategia(jogador).getCarrosCombate()) {
            jButton = new JButton();
            jButton.setIcon(new ImageIcon(getClass().getResource(carroCombate.getImagem())));
            toolBar.add(jButton);
            if (!carroCombate.isAtiva()) {
                jButton.setEnabled(false);
            }
        }
    }
}
