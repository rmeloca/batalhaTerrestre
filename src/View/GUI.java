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
public class GUI extends JFrame {

    private JogoController jogoController;
    private JButton btnJogar;
    private JLabel lblImagem;
    private ActionListener addArma1;
    private ActionListener addArma2;

    private int qtdN1 = 0;
    private int qtdN2 = 0;

    private JButton btnM1;
    private JButton btnM2;
    private JButton btnA1;
    private JButton btnA2;
    private JButton btnL1;
    private JButton btnL2;
    private JButton btnG1;
    private JButton btnG2;
    private JButton btnN1;
    private JButton btnN2;

    public GUI() {
        jogoController = new JogoController();
        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");

        Jogo jogo = new Jogo(10, jogador1, jogador2);
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

        JTextField jtfJogador2;
        jtfJogador2 = new JTextField("Jogador 2");
        jtfJogador2.setBounds(885, 100, 150, 50);
        add(jtfJogador2);

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

        JLabel lblQtdArmas2 = new JLabel("0");
        lblQtdArmas2.setText("0");
        lblQtdArmas2.setBackground(Color.white);
        lblQtdArmas2.setBounds(955, 200, 50, 50);
        lblQtdArmas2.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        add(lblQtdArmas2);

        addArma1 = new ActionListener() {

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
                    qtdN1++;
                    M15 n = new M15();
                    n.setAtiva(true);
                    est.addArma(n);
                    lblQtdArmas1.setText(tamanho + "");
                }
                if (tamanho > 6) {
                    btnA1.setEnabled(false);
                }
                if (tamanho > 7) {
                    btnM1.setEnabled(false);
                }
                if (tamanho > 8) {
                    btnG1.setEnabled(false);
                    if (qtdN1 == 9) {
                        btnN1.setEnabled(false);
                    }
                }
                if (tamanho > 9) {
                    btnL1.setEnabled(false);
                    btnN1.setEnabled(false);

                }
            }
        };
        addArma2 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String arg = ae.getActionCommand();
                Estrategia est = jogo.getEstrategia(jogador2);
                int tamanho = est.calcTamanhoArmas();
                if (arg.equals("M")) {
                    tamanho += 3;
                    M60Patton m = new M60Patton();
                    m.setAtiva(true);
                    est.addArma(m);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("A")) {
                    tamanho += 4;
                    Astros2020 a = new Astros2020();
                    a.setAtiva(true);
                    est.addArma(a);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("G")) {
                    tamanho += 2;
                    Guarani g = new Guarani();
                    g.setAtiva(true);
                    est.addArma(g);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("L")) {
                    tamanho += 1;
                    L118 l = new L118();
                    l.setAtiva(true);
                    est.addArma(l);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("N")) {
                    tamanho += 1;
                    qtdN2++;
                    M15 n = new M15();
                    n.setAtiva(true);
                    est.addArma(n);
                    lblQtdArmas2.setText(tamanho + "");
                }
                if (tamanho > 6) {
                    btnA2.setEnabled(false);
                }
                if (tamanho > 7) {
                    btnM2.setEnabled(false);
                }
                if (tamanho > 8) {
                    btnG2.setEnabled(false);
                    if (qtdN2 == 9) {
                        btnN2.setEnabled(false);
                    }
                }
                if (tamanho > 9) {
                    btnL2.setEnabled(false);
                    btnN2.setEnabled(false);

                }
            }
        };

        btnM1 = new JButton();
        btnM1.addActionListener(addArma1);
        btnM1.setActionCommand("M");
        btnM1.setIcon(new ImageIcon(getClass().getResource(new M60Patton().getImagem())));

        btnM2 = new JButton();
        btnM2.addActionListener(addArma2);
        btnM2.setActionCommand("M");
        btnM2.setIcon(new ImageIcon(getClass().getResource(new M60Patton().getImagem())));

        btnA1 = new JButton();
        btnA1.addActionListener(addArma1);
        btnA1.setActionCommand("A");
        btnA1.setIcon(new ImageIcon(getClass().getResource(new Astros2020().getImagem())));

        btnA2 = new JButton();
        btnA2.addActionListener(addArma2);
        btnA2.setActionCommand("A");
        btnA2.setIcon(new ImageIcon(getClass().getResource(new Astros2020().getImagem())));

        btnL1 = new JButton();
        btnL1.addActionListener(addArma1);
        btnL1.setActionCommand("L");
        btnL1.setIcon(new ImageIcon(getClass().getResource(new L118().getImagem())));

        btnL2 = new JButton();
        btnL2.addActionListener(addArma2);
        btnL2.setActionCommand("L");
        btnL2.setIcon(new ImageIcon(getClass().getResource(new L118().getImagem())));

        btnG1 = new JButton();
        btnG1.addActionListener(addArma1);
        btnG1.setActionCommand("G");
        btnG1.setIcon(new ImageIcon(getClass().getResource(new Guarani().getImagem())));

        btnG2 = new JButton();
        btnG2.addActionListener(addArma2);
        btnG2.setActionCommand("G");
        btnG2.setIcon(new ImageIcon(getClass().getResource(new Guarani().getImagem())));

        btnN1 = new JButton();
        btnN1.addActionListener(addArma1);
        btnN1.setActionCommand("N");
        btnN1.setIcon(new ImageIcon(getClass().getResource(new M15().getImagem())));

        btnN2 = new JButton();
        btnN2.addActionListener(addArma2);
        btnN2.setActionCommand("N");
        btnN2.setIcon(new ImageIcon(getClass().getResource(new M15().getImagem())));

        btnM1.setBounds(200, 300, 80, 80);
        btnA1.setBounds(330, 300, 80, 80);
        btnL1.setBounds(150, 400, 80, 80);
        btnG1.setBounds(260, 400, 80, 80);
        btnN1.setBounds(370, 400, 80, 80);

        btnM2.setBounds(850, 300, 80, 80);
        btnA2.setBounds(980, 300, 80, 80);
        btnL2.setBounds(800, 400, 80, 80);
        btnG2.setBounds(910, 400, 80, 80);
        btnN2.setBounds(1020, 400, 80, 80);

        add(btnM1);
        add(btnA1);
        add(btnL1);
        add(btnG1);
        add(btnN1);

        add(btnM2);
        add(btnA2);
        add(btnL2);
        add(btnG2);
        add(btnN2);

        ImageIcon imagem = new ImageIcon(getClass().getResource("/Icon/entrada.png"));
        lblImagem = new JLabel(imagem);
        lblImagem.setBounds(0, 0, imagem.getIconWidth(), imagem.getIconHeight());
        add(lblImagem);

//        executarMusica("01 - Main Theme.wav");
        btnJogar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String qtd1 = lblQtdArmas1.getText();
                String qtd2 = lblQtdArmas2.getText();

                if (qtd1.equals("10") && qtd2.equals("10")) {

                    String nome1 = jtfJogador1.getText();
                    String nome2 = jtfJogador2.getText();
                    jogador1.setNome(nome1);
                    jogador2.setNome(nome2);

                    dispose();
                    jogo.getEstrategiaMinha().dispoeArmas();
                    jogo.getEstrategiaInimiga().dispoeArmas();
                    jogo.inicializar();
                    JFrame painelConfronto = new FrameConfronto(jogoController.getJogos().get(0));
                } else {
                    JOptionPane.showMessageDialog(null, "É necessário utilizar os 10 espaços disponíveis");
                }
            }
        });

        setResizable(false);
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
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
