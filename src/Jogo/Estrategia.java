/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Jogo.Alvo.Arma;
import Jogo.Tabuleiro.Grelha;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Estrategia {

    private List<Arma> armas;
    private Jogador jogador;
    private Grelha grelha;

    public Estrategia(Jogador jogador, Grelha grelha) {
        setJogador(jogador);
        this.grelha = grelha;
        this.armas = new ArrayList<>();
    }

    public Grelha getGrelha() {
        return grelha;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public void addArma(Arma arma) {
        this.armas.add(arma);
    }

    public Jogador getJogador() {
        return jogador;
    }

    private void setJogador(Jogador jogador) {
        if (jogador != null) {
            this.jogador = jogador;
        }
    }

    public void dispoeArmas() {
        throw new UnsupportedOperationException();
    }
}
