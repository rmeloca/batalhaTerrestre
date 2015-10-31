/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;

/**
 *
 * @author MarcoC
 */
public abstract class Explosivo extends Arma {

    public int explodir(Campo campo) {
        Objeto objeto = campo.getObjeto();
        return 0;
    }
}
