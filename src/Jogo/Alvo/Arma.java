/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import Jogo.Tabuleiro.Grelha;
import java.util.List;

/**
 *
 * @author a1137131
 */
public abstract class Arma extends Objeto {

    private int tamanho;
    private List<Campo> campos;

    public boolean rotacionar() {
        if (tamanho == 3) {
            Campo campo1 = campos.get(0);
            Campo campo2 = campos.get(1);
            Grelha grelha = campo1.getGrelha();
            
            Coordenada coordenada = campo2.getCoordenada();
            
            Campo todosCampos[][] = grelha.getCampos();
//            todosCampos[coordenada.getX()][coordenada.getY()];
            Campo campo3 = campos.get(2);
        }

        return true;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean isVivo() {
        return vivo;
    }
    boolean vivo;
}
