/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Jogo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author romulo
 */
public class PainelConfronto extends JPanel {

    Jogo jogo;

    public PainelConfronto(Jogo jogo) {
        JPanel painelPrincipal;
        JPanel jPanelMenuWest;

        this.jogo = jogo;
        setLayout(new BorderLayout());
        painelPrincipal = new JPanel();

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelPrincipal.add(new PainelGrelha(jogo.getEstrategia1().getGrelha()));
        painelPrincipal.add(new PainelGrelha(jogo.getEstrategia2().getGrelha()));

        add(painelPrincipal, BorderLayout.CENTER);
        
//        TextView.imprimirGrelha(jogo.getEstrategia1().getGrelha());
//        TextView.imprimirGrelha(jogo.getEstrategia2().getGrelha());
        
//        TextView.imprimirJogo(jogo);
        
//        jPanelMenuWest = new JPanel(new GridLayout(3, 1));
//        jPanelMenuWest.add(new JButton());
//        jPanelMenuWest.add(new JButton());
//        jPanelMenuWest.add(new JButton());

//        painelPrincipal.add(jPanelMenuWest, BorderLayout.WEST);//toolbar
    }

}
