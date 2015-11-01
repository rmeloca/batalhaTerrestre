/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Jogo.Alvo.Arma;
import Jogo.Alvo.Explosivo;
import Jogo.Tabuleiro.Grelha;
import java.io.Serializable;

/**
 *
 * @author a1137131
 */
public class Jogo implements Serializable {

    private Estrategia estrategia1;
    private Estrategia estrategia2;
    private int dimensao;
    private Jogador jogadorRodada;
    private Jogador vencedor;

    public Jogo(int dimensao, Jogador jogador1, Jogador jogador2) {
        setDimensao(dimensao);
        this.jogadorRodada = jogador1;
        estrategia1 = new Estrategia(jogador1, new Grelha(dimensao));
        estrategia2 = new Estrategia(jogador2, new Grelha(dimensao));
    }

    public int getDimensao() {
        return dimensao;
    }

    private void setDimensao(int dimensao) {
        if (dimensao > 5) {
            this.dimensao = dimensao;
        }
    }

    public Jogador getVencedor() {
        throw new UnsupportedOperationException();
    }

    public Estrategia getEstrategia1() {
        return estrategia1;
    }

    public Estrategia getEstrategia2() {
        return estrategia2;
    }

    public Jogador getJogadorProximaRodada() {
        throw new UnsupportedOperationException();
    }

    /**
     * Realiza a explosão das bombas, considerando a tradução dos campos Utiliza
     * a orientação a objetos, mas não é mais lento?
     */
    public void inicializar() {
        for (Explosivo explosivo : estrategia1.getExplosivos()) {
            explosivo.explodir(estrategia2.getGrelha().getCampo(explosivo.getCampos().get(0).getCoordenada()));
        }
        for (Explosivo explosivo : estrategia2.getExplosivos()) {
            explosivo.explodir(estrategia1.getGrelha().getCampo(explosivo.getCampos().get(0).getCoordenada()));
        }
    }
}
