/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogo;

/**
 *
 * @author a1137131
 */
public class Jogo {
    private Jogador rodada;
    private Jogador jogador1;
    private Jogador jogador2;
    private int dimensao;
    private Jogador vencedor;

    public Jogador getRodada() {
        return rodada;
    }

    public void setRodada(Jogador rodada) {
        this.rodada = rodada;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public Jogador getVencedor() {
        return vencedor;
    }

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
    }
    
}
