/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Jogo.Alvo.Guarani;
import Jogo.Jogador;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import View.Cliente.FrameConfrontoCliente;
import View.Cliente.GUICliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
    private final ObjectInputStream entrada;

    public Escutar(ObjectInputStream inputStream, FrameConfrontoCliente frameConfrontoCliente) {
        this.frameConfronto = frameConfrontoCliente;
        entrada = inputStream;
    }

    @Override
    public void run() {
        Guarani guarani;
        Jogador jogador;
        boolean acertou;
        int retorno;
        List<Campo> camposSelecionados;
        Campo campo;
        guarani = new Guarani();
        Coordenada coordenada;
        try {
            while (true) {
                coordenada = (Coordenada) entrada.readObject();

                camposSelecionados = new ArrayList<>();
                campo = frameConfronto.painelGrelhaMinha.grelha.getCampo(coordenada);
                camposSelecionados.add(campo);

                retorno = guarani.atirar(camposSelecionados);
                acertou = retorno > 0 || retorno == -1;

                frameConfronto.painelGrelhaMinha.atualizarMinhaGrelha();

                jogador = frameConfronto.jogo.getJogadorProximaRodada(acertou);

                if (frameConfronto.jogo.haVencedor()) {
                    frameConfronto.painelGrelhaInimiga.desabilitarGrelha();
                    frameConfronto.painelGrelhaMinha.desabilitarGrelha();
                    if (JOptionPane.showConfirmDialog(null, jogador.getNome() + " Venceu!\nRevanche?") == JOptionPane.OK_OPTION) {
                        frameConfronto.dispose();
                        new GUICliente();
                    }
                    break;
                }
                if (!acertou) {
                    frameConfronto.painelGrelhaInimiga.atualizarGrelhaInimiga();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Escutar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
