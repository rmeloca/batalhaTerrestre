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

        if (objeto instanceof Arma) {
            Arma arma = (Arma) objeto;
            if (arma instanceof CarroCombate) {
                campo.setAtirado(true);
                campo.setAtiravel(false);
                if (objeto.getCampos().size() == 1) {

                    Grelha grelha = campo.getGrelha();

                    Coordenada coordenada = new Coordenada((campo.getCoordenada().getX() + 1), campo.getCoordenada().getY());
                    grelha.getCampo(coordenada).setAtiravel(false);

                    coordenada = new Coordenada((campo.getCoordenada().getX() - 1), campo.getCoordenada().getY());
                    grelha.getCampo(coordenada).setAtiravel(false);

                    coordenada = new Coordenada(campo.getCoordenada().getX(), (campo.getCoordenada().getY()) + 1);
                    grelha.getCampo(coordenada).setAtiravel(false);

                    coordenada = new Coordenada(campo.getCoordenada().getX(), (campo.getCoordenada().getY()) - 1);
                    grelha.getCampo(coordenada).setAtiravel(false);

                }
                return 1;
            }
        }

        return 0;
    }
}
