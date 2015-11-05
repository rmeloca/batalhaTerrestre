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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author romulo
 */
public class PainelEntrada extends JPanel {

    JButton btnPvp;
    JLabel lblImagem;
    private JogoController jogoController;

    public PainelEntrada() {
        setLayout(null);
        btnPvp = new JButton("PvP");
        btnPvp.setBounds(500, 200, 100, 30);
        add(btnPvp);

        ImageIcon imagem = new ImageIcon("src/Icon/entrada.png");
        lblImagem = new JLabel(imagem);
        lblImagem.setBounds(0, 0, imagem.getIconWidth(), imagem.getIconHeight());
        add(lblImagem);

        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
        try {
            BGM = new AudioStream(new FileInputStream("src/Music/01 - Main Theme.wav"));
        } catch (IOException ex) {
            Logger.getLogger(PainelEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        MGP.start(BGM);

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
                JPanel painelConfronto = new PainelConfronto(jogoController.getJogos().get(0));
                JFrame jFrame = new JFrame();
                jFrame.setContentPane(painelConfronto);
                jFrame.setSize(1280, 720);
                jFrame.setTitle("Confronto");
                jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                jFrame.setResizable(false);
                jFrame.setVisible(true);
            }
        });
    }
}
