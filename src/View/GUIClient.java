/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author a1137131
 */
public class GUIClient extends JFrame {

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
    Estrategia estrategia;

    public GUIClient() {
        Jogador jogador2 = new Jogador("Jogador");

        estrategia = new Estrategia(jogador2);

        setTitle("Batalha Terrestre");

        setLayout(null);
        btnJogar = new JButton("Jogar");
        btnJogar.setBounds(590, 580, 100, 30);
        add(btnJogar);

        JTextField jtfJogador2;
        jtfJogador2 = new JTextField("Jogador");
        jtfJogador2.setBounds(245, 100, 150, 50);
        add(jtfJogador2);

        JLabel lblQtdArmas2 = new JLabel("0");
        lblQtdArmas2.setText("0");
        lblQtdArmas2.setBackground(Color.white);
        lblQtdArmas2.setBounds(305, 200, 50, 50);
        Font labelFont = lblQtdArmas2.getFont();
        String labelText = lblQtdArmas2.getText();

        int stringWidth = lblQtdArmas2.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = lblQtdArmas2.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio) / 2;
        int componentHeight = lblQtdArmas2.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);
        lblQtdArmas2.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        add(lblQtdArmas2);

        addArma = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String arg = ae.getActionCommand();
                int tamanho = estrategia.calcTamanhoArmas();
                if (arg.equals("M")) {
                    tamanho += 3;
                    M60Patton m = new M60Patton();
                    m.setAtiva(true);
                    estrategia.addArma(m);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("A")) {
                    tamanho += 4;
                    Astros2020 a = new Astros2020();
                    a.setAtiva(true);
                    estrategia.addArma(a);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("G")) {
                    tamanho += 2;
                    Guarani g = new Guarani();
                    g.setAtiva(true);
                    estrategia.addArma(g);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("L")) {
                    tamanho += 1;
                    L118 l = new L118();
                    l.setAtiva(true);
                    estrategia.addArma(l);
                    lblQtdArmas2.setText(tamanho + "");
                } else if (arg.equals("N")) {
                    tamanho += 1;
                    qtdN++;
                    M15 n = new M15();
                    n.setAtiva(true);
                    estrategia.addArma(n);
                    lblQtdArmas2.setText(tamanho + "");
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
                String qtd1 = lblQtdArmas2.getText();

                if (qtd1.equals("10")) {
                    String nome = jtfJogador2.getText();
                    estrategia.getJogador().setNome(nome);
                    estrategia.dispoeArmas();

                    dispose();

                    ObjectOutputStream saida;
                    ObjectInputStream entrada;
                    try {
                        saida = new ObjectOutputStream(cliente.getOutputStream());
                        entrada = new ObjectInputStream(cliente.getInputStream());

                        saida.flush();
                        saida.writeObject(estrategia);

                        JOptionPane.showMessageDialog(rootPane, "Guerra declarada!\nAo combate");

                        Jogo jogo = (Jogo) entrada.readObject();

                        new FrameConfrontoCliente(entrada, saida, jogo);

                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "É necessário utilizar os 10 espaços disponíveis");
                }
            }
        });

        setResizable(false);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JOptionPane.showMessageDialog(null, "Inimigo declarou Guerra!\nEscolha suas armas, Comandante");
    }

    public static void main(String[] args) {
        try {
            cliente = new Socket("localhost", 12345);
            new GUIClient();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
