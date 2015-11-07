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
        this.setAtiva(false);

        if (objeto instanceof CarroCombate) {
            Arma arma = (Arma) objeto;
            if (arma.getTamanho() == 1) {
                arma.setAtiva(false);
                Grelha grelha = campo.getGrelha();
                Coordenada coordenada = campo.getCoordenada();
                desabilitarCampo(grelha.getCampo(coordenada.north()));
                desabilitarCampo(grelha.getCampo(coordenada.northeast()));
                desabilitarCampo(grelha.getCampo(coordenada.northwest()));
                desabilitarCampo(grelha.getCampo(coordenada.west()));
                desabilitarCampo(grelha.getCampo(coordenada.east()));
                desabilitarCampo(grelha.getCampo(coordenada.south()));
                desabilitarCampo(grelha.getCampo(coordenada.southeast()));
                desabilitarCampo(grelha.getCampo(coordenada.southwest()));
            }
            return 1;
        }
        return 0;
    }

    private final void desabilitarCampo(Campo campo) {
        if (campo != null) {
            campo.setAtiravel(false);
        }
    }
}
