/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Jogo.Alvo.CarroCombate;
import Jogo.Jogo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
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

        PainelGrelha painelGrelha1;
        PainelGrelha painelGrelha2;

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelGrelha1 = new PainelGrelha(jogo.getEstrategia1().getGrelha());
        painelGrelha2 = new PainelGrelha(jogo.getEstrategia2().getGrelha());
        painelPrincipal.add(painelGrelha1);
        painelPrincipal.add(painelGrelha2);

        painelGrelha1.atualizarGrelha();
        painelGrelha2.desabilitaGrelha();
        atualizarToolbar();

        add(painelPrincipal, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

    }

    private void atualizarToolbar() {
        toolBar.removeAll();
        for (CarroCombate carrosCombate : jogo.getEstrategia(jogo.getJogadorProximaRodada()).getCarrosCombate()) {
            toolBar.add(new JButton(carrosCombate.toString()));
        }
    }
}
