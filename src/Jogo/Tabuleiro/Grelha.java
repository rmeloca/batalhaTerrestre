/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Tabuleiro;

import Jogo.Alvo.Terra;

/**
 *
 * @author romulo
 */
public class Grelha {

    private Campo[][] campos;
    private int dimensao;

    public Grelha(int dimensao) {
        setDimensao(dimensao);
        campos = new Campo[dimensao][dimensao];
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                //new Terra() pode ser excesso de lixo
                campos[i][j] = new Campo(new Coordenada(i, j), new Terra());
            }
        }
    }

    public Campo[][] getCampos() {
        return campos;
    }

    public int getDimensao() {
        return dimensao;
    }

    private void setDimensao(int dimensao) {
        if (dimensao > 5) {
            this.dimensao = dimensao;
        }
    }

}
