/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Tabuleiro;

/**
 *
 * @author romulo
 */
public class Grelha {

    private Campo[][] campos;
    private int dimensao;

    public Grelha(int dimensao) {
        campos = new Campo[dimensao][dimensao];
        setDimensao(dimensao);
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
