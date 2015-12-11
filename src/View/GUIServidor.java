/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.JogoController;
import Jogo.Alvo.Astros2020;
import Jogo.Alvo.Guarani;
import Jogo.Alvo.L118;
import Jogo.Alvo.M15;
import Jogo.Alvo.M60Patton;
import Jogo.Estrategia;
import Jogo.Jogador;
import Jogo.Jogo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author a1137131
 */
public class GUIServidor extends JFrame {

    private JogoController jogoController;
    private Jogo jogo;
    private JButton btnJogar;
    private JLabel lblImagem;
    private ActionListener addArma;

    private int qtdN = 0;

    private JButton btnM;
    private JButton btnA;
    private JButton btnL;
    private JButton btnG;
    private JButton btnN;

    static Socket cliente;

    public GUIServidor() {
        jogoController = new JogoController();
        Jogador jogador1 = new Jogador("Jogador 1");

        jogo = new Jogo(10);
        jogo.setEstrategia1(new Estrategia(jogador1));
        jogoController.addJogo(jogo);

        setTitle("Batalha Terrestre");

        setLayout(null);
        btnJogar = new JButton("Jogar");
        btnJogar.setBounds(590, 580, 100, 30);
        add(btnJogar);

        JTextField jtfJogador1;
        jtfJogador1 = new JTextField("Jogador 1");
        jtfJogador1.setBounds(245, 100, 150, 50);
        add(jtfJogador1);

        JLabel lblQtdArmas1 = new JLabel("0");
        lblQtdArmas1.setText("0");
        lblQtdArmas1.setBackground(Color.white);
        lblQtdArmas1.setBounds(305, 200, 50, 50);
        Font labelFont = lblQtdArmas1.getFont();
        String labelText = lblQtdArmas1.getText();

        int stringWidth = lblQtdArmas1.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = lblQtdArmas1.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio) / 2;
        int componentHeight = lblQtdArmas1.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);
        lblQtdArmas1.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        add(lblQtdArmas1);

        addArma = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String arg = ae.getActionCommand();
                Estrategia est = jogo.getEstrategia(jogador1);
                int tamanho = est.calcTamanhoArmas();
                if (arg.equals("M")) {
                    tamanho += 3;
                    M60Patton m = new M60Patton();
                    m.setAtiva(true);
                    est.addArma(m);
                    lblQtdArmas1.setText(tamanho + "");
                } else if (arg.equals("A")) {
                    tamanho += 4;
                    Astros2020 a = new Astros2020();
                    a.setAtiva(true);
                    est.addArma(a);
                    lblQtdArmas1.setText(tamanho + "");
                } else if (arg.equals("G")) {
                    tamanho += 2;
                    Guarani g = new Guarani();
                    g.setAtiva(true);
                    est.addArma(g);
                    lblQtdArmas1.setText(tamanho + "");
                } else if (arg.equals("L")) {
                    tamanho += 1;
                    L118 l = new L118();
                    l.setAtiva(true);
                    est.addArma(l);
                    lblQtdArmas1.setText(tamanho + "");
                } else if (arg.equals("N")) {
                    tamanho += 1;
                    qtdN++;
                    M15 n = new M15();
                    n.setAtiva(true);
                    est.addArma(n);
                    lblQtdArmas1.setText(tamanho + "");
                }
                if (tamanho > 6) {
                    btnA.setEnabled(false);
                }
                if (tamanho > 7) {
                    btnM.setEnabled(false);
                }
                if (tamanho > 8) {
                    btnG.setEnabled(false);
                    if (qtdN == 9) {
                        btnN.setEnabled(false);
                    }
                }
                if (tamanho > 9) {
                    btnL.setEnabled(false);
                    btnN.setEnabled(false);

                }
            }
        };

        btnM = new JButton();
        btnM.addActionListener(addArma);
        btnM.setActionCommand("M");
        btnM.setIcon(new ImageIcon(getClass().getResource(new M60Patton().getImagem())));

        btnA = new JButton();
        btnA.addActionListener(addArma);
        btnA.setActionCommand("A");
        btnA.setIcon(new ImageIcon(getClass().getResource(new Astros2020().getImagem())));

        btnL = new JButton();
        btnL.addActionListener(addArma);
        btnL.setActionCommand("L");
        btnL.setIcon(new ImageIcon(getClass().getResource(new L118().getImagem())));

        btnG = new JButton();
        btnG.addActionListener(addArma);
        btnG.setActionCommand("G");
        btnG.setIcon(new ImageIcon(getClass().getResource(new Guarani().getImagem())));

        btnN = new JButton();
        btnN.addActionListener(addArma);
        btnN.setActionCommand("N");
        btnN.setIcon(new ImageIcon(getClass().getResource(new M15().getImagem())));

        btnM.setBounds(200, 300, 80, 80);
        btnA.setBounds(330, 300, 80, 80);
        btnL.setBounds(150, 400, 80, 80);
        btnG.setBounds(260, 400, 80, 80);
        btnN.setBounds(370, 400, 80, 80);

        add(btnM);
        add(btnA);
        add(btnL);
        add(btnG);
        add(btnN);

        ImageIcon imagem = new ImageIcon(getClass().getResource("/Icon/entrada.png"));
        lblImagem = new JLabel(imagem);
        lblImagem.setBounds(0, 0, imagem.getIconWidth(), imagem.getIconHeight());
        add(lblImagem);

//        executarMusica("01 - Main Theme.wav");
        btnJogar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ServerSocket servidor;

                String qtd1 = lblQtdArmas1.getText();
                ObjectInputStream entrada;
                ObjectOutputStream saida;
                if (qtd1.equals("10")) {
                    try {

                        servidor = new ServerSocket(12345);
                        JOptionPane.showMessageDialog(rootPane, "Última cartada enviada.\nAguardando resposta inimiga");
                        cliente = servidor.accept();

                        entrada = new ObjectInputStream(cliente.getInputStream());
                        saida = new ObjectOutputStream(cliente.getOutputStream());

                        String nome1 = jtfJogador1.getText();
                        jogador1.setNome(nome1);

                        dispose();
                        jogo.getEstrategia1().dispoeArmas();

                        Estrategia estrategia2 = (Estrategia) entrada.readObject();
                        jogo.setEstrategia2(estrategia2);
                        JOptionPane.showMessageDialog(rootPane, "Guerra declarada!\nAo combate");

                        jogo.inicializar();

                        saida.flush();
                        saida.writeObject(jogo);

                        JFrame painelConfronto = new FrameConfrontoServidor(cliente, jogo);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(GUIServidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Utilize toda a sua força, comandante, o inimigo possui muitas tropas");
                }
            }
        });

        setResizable(false);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIServidor();
    }

    private void executarMusica(String nome) {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
        try {
            BGM = new AudioStream(new FileInputStream(getClass().getResource("src/Music/" + nome).getFile()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MGP.start(BGM);
    }
}
