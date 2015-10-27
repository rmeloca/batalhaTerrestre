/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Tabuleiro;

import Jogo.Alvo.Arma;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Grelha {

    private Campo[][] campos;
    private List<Arma> armas;
    private int dimensao;

    public Grelha(int dimensao) {
        this.armas = new ArrayList<>();
        campos = new Campo[dimensao][dimensao];
        setDimensao(dimensao);
    }

    public Campo[][] getCampos() {
        return campos;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public void addArma(Arma arma) {
        this.armas.add(arma);
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
