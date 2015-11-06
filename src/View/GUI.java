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
import Jogo.Jogador;
import Jogo.Jogo;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
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

    public GUI() {
        setTitle("Batalha Terrestre");

        setLayout(null);
        btnPvp = new JButton("Jogar");
        btnPvp.setBounds(590, 580, 100, 30);
        add(btnPvp);

        JTextField jtfJogador1;
        jtfJogador1 = new JTextField("Jogador 1");
        jtfJogador1.setBounds(245,100,150,50);
        add(jtfJogador1);
        
        JTextField jtfJogador2;
        jtfJogador2 = new JTextField("Jogador 2");
        jtfJogador2.setBounds(885,100,150,50);
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
        
        JButton btnM1 = new JButton("M60Patton (3)");
        JButton btnM2 = new JButton("M60Patton (3)");
        JButton btnA1 = new JButton("Astros2020 (4)");
        JButton btnA2 = new JButton("Astros2020 (4)");
        JButton btnL1 = new JButton("L118 (1)");
        JButton btnL2 = new JButton("L118 (1)");
        JButton btnG1 = new JButton("Guarani (2)");
        JButton btnG2 = new JButton("Guarani (2)");
        JButton btnN1 = new JButton("Mina M15 (1)");
        JButton btnN2 = new JButton("Mina M15 (1)");
        
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

        jogoController = new JogoController();
        Jogador jogador1 = new Jogador("a");
        Jogador jogador2 = new Jogador("b");

        Jogo jogo = new Jogo(10, jogador1, jogador2);
        jogoController.addJogo(jogo);

        jogo.getEstrategia1().addArma(new Guarani());
        jogo.getEstrategia1().addArma(new M15());
        jogo.getEstrategia1().addArma(new M15());
        jogo.getEstrategia1().addArma(new Astros2020());
        jogo.getEstrategia1().addArma(new M60Patton());
        jogo.getEstrategia1().addArma(new M60Patton());
        jogo.getEstrategia1().addArma(new L118());
        jogo.getEstrategia1().addArma(new L118());

        jogo.getEstrategia1().dispoeArmas();

        jogo.getEstrategia2().addArma(new Guarani());
        jogo.getEstrategia2().addArma(new M15());
        jogo.getEstrategia2().addArma(new M15());
        jogo.getEstrategia2().addArma(new Astros2020());
        jogo.getEstrategia2().addArma(new Astros2020());
        jogo.getEstrategia2().addArma(new M60Patton());
        jogo.getEstrategia2().addArma(new M60Patton());
        jogo.getEstrategia2().addArma(new L118());

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
