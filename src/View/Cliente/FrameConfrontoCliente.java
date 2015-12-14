/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cliente;

import Jogo.Alvo.CarroCombate;
import Jogo.Estrategia;
import Jogo.Jogo;
import Server.Escutar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public PainelGrelhaCliente painelGrelhaMinha;
    public PainelGrelhaCliente painelGrelhaInimiga;
    public JToolBar toolBar;
    public Jogo jogo;
    protected static ObjectInputStream inputStream;
    protected static ObjectOutputStream outputStream;

    public FrameConfrontoCliente(ObjectInputStream inputStream, ObjectOutputStream outputStream, Jogo jogo) {
        setTitle("Confronto Cliente");

        JPanel painelPrincipal;

        setLayout(new BorderLayout());
        painelPrincipal = new JPanel();
        toolBar = new JToolBar();

        this.jogo = jogo;
        FrameConfrontoCliente.inputStream = inputStream;
        FrameConfrontoCliente.outputStream = outputStream;

        Escutar threadEscutarSocket = new Escutar(inputStream, this);
        threadEscutarSocket.start();

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelGrelhaMinha = new PainelGrelhaCliente(jogo.getEstrategia1().getGrelha());
        painelGrelhaInimiga = new PainelGrelhaCliente(jogo.getEstrategia2().getGrelha());

        painelGrelhaInimiga.setFrameConfronto(this);
        painelGrelhaMinha.setFrameConfronto(this);

        painelPrincipal.add(painelGrelhaMinha);
        painelPrincipal.add(painelGrelhaInimiga);

        painelGrelhaInimiga.atualizarGrelhaInimiga();
        painelGrelhaMinha.atualizarMinhaGrelha();
        if (!jogo.bloqueado) {
            painelGrelhaInimiga.desabilitarGrelha();
        }

        atualizarToolBar(jogo.getEstrategia2());

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
