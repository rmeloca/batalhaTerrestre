/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author romulo
 */
public class painelEntrada extends JPanel {

    JButton btnPvp;
    JLabel lblImagem;

    public painelEntrada() {
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
            Logger.getLogger(painelEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }

        MGP.start(BGM);
    }
}
