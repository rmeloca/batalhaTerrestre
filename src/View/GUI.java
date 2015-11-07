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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author a1137131
 */
public class GUI extends JFrame {

    JButton btnPvp;
    JLabel lblImagem;
    private JogoController jogoController;
    ActionListener addArma1;
    ActionListener addArma2;
    ActionListener addJogar;

    JButton btnM1;
    JButton btnM2;
    JButton btnA1;
    JButton btnA2;
    JButton btnL1;
    JButton btnL2;
    JButton btnG1;
    JButton btnG2;
    JButton btnN1;
    JButton btnN2;

    public GUI() {
        jogoController = new JogoController();
        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");

        Jogo jogo = new Jogo(10, jogador1, jogador2);
        jogoController.addJogo(jogo);

        setTitle("Batalha Terrestre");

        setLayout(null);
        btnPvp = new JButton("Jogar");
        btnPvp.setBounds(590, 580, 100, 30);
        add(btnPvp);

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
        add(lblQtdArmas1);

        JLabel lblQtdArmas2 = new JLabel("0");
        lblQtdArmas2.setText("0");
        lblQtdArmas2.setBackground(Color.white);
        lblQtdArmas2.setBounds(955, 200, 50, 50);
        add(lblQtdArmas2);
        
        addJogar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        };
        
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
                } else if(arg.equals("N")) {
                    tamanho += 1;
                    M15 n = new M15();
                    n.setAtiva(true);
                    est.addArma(n);
                    lblQtdArmas1.setText(tamanho + "");
                }
                if (tamanho > 6) {
                    btnA1.setEnabled(false);
                } if (tamanho > 7) {
                    btnM1.setEnabled(false);
                } if (tamanho > 8) {
                    btnG1.setEnabled(false);
                } if (tamanho > 9) {
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
                } else if(arg.equals("N")) {
                    tamanho += 1;
                    M15 n = new M15();
                    n.setAtiva(true);
                    est.addArma(n);
                    lblQtdArmas2.setText(tamanho + "");
                }
                if (tamanho > 6) {
                    btnA2.setEnabled(false);
                } if (tamanho > 7) {
                    btnM2.setEnabled(false);
                } if (tamanho > 8) {
                    btnG2.setEnabled(false);
                } if (tamanho > 9) {
                    btnL2.setEnabled(false);
                    btnN2.setEnabled(false);

                }
            }
        };
        
        
        btnM1 = new JButton("M60Patton (3)");
        btnM1.addActionListener(addArma1);
        btnM1.setActionCommand("M");
     
        btnM2 = new JButton("M60Patton (3)");
        btnM2.addActionListener(addArma2);
        btnM2.setActionCommand("M");
        
        btnA1 = new JButton("Astros2020 (4)");
        btnA1.addActionListener(addArma1);
        btnA1.setActionCommand("A");
        
        btnA2 = new JButton("Astros2020 (4)");
        btnA2.addActionListener(addArma2);
        btnA2.setActionCommand("A");
        
        btnL1 = new JButton("L118 (1)");
        btnL1.addActionListener(addArma1);
        btnL1.setActionCommand("L");
        
        btnL2 = new JButton("L118 (1)");
        btnL2.addActionListener(addArma2);
        btnL2.setActionCommand("L");
        
        btnG1 = new JButton("Guarani (2)");
        btnG1.addActionListener(addArma1);
        btnG1.setActionCommand("G");
        
        btnG2 = new JButton("Guarani (2)");
        btnG2.addActionListener(addArma2);
        btnG2.setActionCommand("G");
        
        btnN1 = new JButton("Mina M15 (1)");
        btnN1.addActionListener(addArma1);
        btnN1.setActionCommand("N");
        
        btnN2 = new JButton("Mina M15 (1)");
        btnN2.addActionListener(addArma2);
        btnN2.setActionCommand("N");

        
        btnM1.setBounds(200, 300, 100, 50);
        btnA1.setBounds(330, 300, 100, 50);
        btnL1.setBounds(150, 400, 100, 50);
        btnG1.setBounds(260, 400, 100, 50);
        btnN1.setBounds(370, 400, 100, 50);

        btnM2.setBounds(850, 300, 100, 50);
        btnA2.setBounds(980, 300, 100, 50);
        btnL2.setBounds(800, 400, 100, 50);
        btnG2.setBounds(910, 400, 100, 50);
        btnN2.setBounds(1020, 400, 100, 50);

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

//        
//        int vida1 = 10, vida2 = 10;
//        
//        while(vida1 > 0){
//           
//        }
        ImageIcon imagem = new ImageIcon("src/Icon/entrada.png");
        lblImagem = new JLabel(imagem);
        lblImagem.setBounds(0, 0, imagem.getIconWidth(), imagem.getIconHeight());
        add(lblImagem);

        executarMusica("01 - Main Theme.wav");


        jogo.getEstrategia1().dispoeArmas();
        jogo.getEstrategia2().dispoeArmas();

        jogo.inicializar();

        btnPvp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JPanel painelConfronto = new PainelConfronto(jogoController.getJogos().get(0));
                JFrame jFrame = new JFrame();
                jFrame.setContentPane(painelConfronto);
                jFrame.setSize(1280, 720);
                jFrame.setTitle("Confronto");
                jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                jFrame.setResizable(false);
                jFrame.setVisible(true);

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

    private static void executarMusica(String nome) {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
//        try {
//            BGM = new AudioStream(new FileInputStream("src/Music/" + nome));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        MGP.start(BGM);
    }
}
