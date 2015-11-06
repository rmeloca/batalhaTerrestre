/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Alvo.CarroCombate;
import Jogo.Jogo;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author romulo
 */
public class PainelConfronto extends JPanel {

    Jogo jogo;
    JPanel painelPrincipal;
    JToolBar toolBar;

    public PainelConfronto(Jogo jogo) {
        this.jogo = jogo;
        setLayout(new BorderLayout());
        painelPrincipal = new JPanel();
        toolBar = new JToolBar();
        for (CarroCombate carrosCombate : jogo.getEstrategia1().getCarrosCombate()) {
            toolBar.add(new JButton(carrosCombate.toString()));
        }

        PainelGrelha painelGrelha1;
        PainelGrelha painelGrelha2;

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelGrelha1 = new PainelGrelha(jogo.getEstrategia1().getGrelha());
        painelGrelha2 = new PainelGrelha(jogo.getEstrategia2().getGrelha());
        painelPrincipal.add(painelGrelha1);
        painelPrincipal.add(painelGrelha2);

        /*
         for (JButton[] campos : painelGrelha1.campos) {
         for (JButton campo : campos) {
         String action[] = campo.e.getActionCommand().split(",");
         coordenada = new Coordenada(Integer.parseInt(action[0]), Integer.parseInt(action[1]));
         campo = grelha.getCampo(coordenada);
                
         if (campo.foiAtirado()) {
         btnCampo.setText(campo.getObjeto().toString());
         } else {
         btnCampo.setText(terra.toString());
         }
         }
         }
         */
        add(painelPrincipal, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

//        TextView.imprimirGrelha(jogo.getEstrategia1().getGrelha());
//        TextView.imprimirGrelha(jogo.getEstrategia2().getGrelha());
//        TextView.imprimirJogo(jogo);
    }

}
