/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author romulo
 */
public class painelEntrada extends JPanel {

    public painelEntrada() {

        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
        try {
            BGM = new AudioStream(new FileInputStream("src/Music/03 - Nightbook.wav"));
        } catch (IOException ex) {
            Logger.getLogger(painelEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }

        MGP.start(BGM);

        ImageIcon image = new ImageIcon("src/Icon/teste.png");
        add(new JLabel(image));
    }
}
