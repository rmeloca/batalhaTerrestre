/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import Jogo.Tabuleiro.Grelha;

/**
 *
 * @author MarcoC
 */
public abstract class Explosivo extends Arma {

    public int explodir(Campo campo) {
        Objeto objeto = campo.getObjeto();
        campo.setAtirado(true);
        campo.setAtiravel(false);

        if (objeto instanceof CarroCombate) {
            Arma arma = (Arma) objeto;
            if (arma.getTamanho() == 1) {
                arma.setAtiva(false);
                Grelha grelha = campo.getGrelha();
                Coordenada coordenada = campo.getCoordenada();
                grelha.getCampo(coordenada.north()).setAtiravel(false);
                grelha.getCampo(coordenada.northeast()).setAtiravel(false);
                grelha.getCampo(coordenada.northwest()).setAtiravel(false);
                grelha.getCampo(coordenada.west()).setAtiravel(false);
                grelha.getCampo(coordenada.east()).setAtiravel(false);
                grelha.getCampo(coordenada.south()).setAtiravel(false);
                grelha.getCampo(coordenada.southeast()).setAtiravel(false);
                grelha.getCampo(coordenada.southwest()).setAtiravel(false);
            }
            return 1;
        }
        return 0;
    }
}
