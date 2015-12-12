/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jogo.Alvo.Guarani;
import Jogo.Jogador;
import Jogo.Tabuleiro.Campo;
import View.FrameConfrontoCliente;
import View.GUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class Escutar extends Thread {

    private final FrameConfrontoCliente frameConfronto;
    List<Campo> camposSelecionados;
    ObjectInputStream entrada;
    Guarani guarani;
    Jogador jogador;
    boolean acertou;
    int retorno;

    public Escutar(ObjectInputStream inputStream, FrameConfrontoCliente frameConfrontoCliente) {
        this.frameConfronto = frameConfrontoCliente;
        guarani = new Guarani();
        entrada = inputStream;
    }

    @Override
    public void run() {
        try {

            camposSelecionados = (List<Campo>) entrada.readObject();

//            frameConfronto.jogo.
//            camposSelecionados.get(0).getCoordenada()
            retorno = guarani.atirar(camposSelecionados);
            acertou = retorno > 0 || retorno == -1;

            jogador = frameConfronto.jogo.getJogadorProximaRodada(acertou);

            if (frameConfronto.jogo.haVencedor()) {
                frameConfronto.painelGrelhaMinha.desabilitarGrelha();
                frameConfronto.painelGrelhaInimiga.desabilitarGrelha();
                if (JOptionPane.showConfirmDialog(null, jogador.getNome() + " Venceu!") == JOptionPane.OK_OPTION) {
                    frameConfronto.dispose();
                    new GUI();
                }
            }
            if (!acertou) {
                frameConfronto.painelGrelhaMinha.habilitarGrelha();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Escutar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
