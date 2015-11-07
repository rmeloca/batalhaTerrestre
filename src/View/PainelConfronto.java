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
    PainelGrelha painelGrelha1;
    PainelGrelha painelGrelha2;
    JToolBar toolBar;

    public PainelConfronto(Jogo jogo) {
        JPanel painelPrincipal;

        this.jogo = jogo;
        setLayout(new BorderLayout());
        painelPrincipal = new JPanel();
        toolBar = new JToolBar();

        painelPrincipal.setLayout(new GridLayout(1, 2));
        painelGrelha1 = new PainelGrelha(jogo.getEstrategia1().getGrelha());
        painelGrelha2 = new PainelGrelha(jogo.getEstrategia2().getGrelha());

        painelGrelha1.setGrelhaInimiga(painelGrelha2);
        painelGrelha2.setGrelhaInimiga(painelGrelha1);

        painelPrincipal.add(painelGrelha1);
        painelPrincipal.add(painelGrelha2);

        painelGrelha1.atualizarGrelha();
        painelGrelha2.desabilitarGrelha();
        atualizarToolBar();

        add(painelPrincipal, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);

    }

    private void atualizarToolBar() {
        toolBar.removeAll();
        JButton jButton;
        for (CarroCombate carroCombate : jogo.getEstrategia(jogo.getJogadorProximaRodada()).getCarrosCombate()) {
            jButton = new JButton(carroCombate.toString());
            toolBar.add(jButton);
            if (!carroCombate.isAtiva()) {
                jButton.setEnabled(false);
            }
        }
    }
}
