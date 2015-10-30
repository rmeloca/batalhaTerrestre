/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public abstract class CarroCombate extends Arma {

    public int atirar(List<Campo> campos) {
        Campo campo;
        Objeto objeto;
        Arma arma;
        List<Campo> camposArma;
        List<Campo> novoCampos;
        int qtdInv = 0, qtdAcer = 0, i;
        int tamanho = campos.size();
        for (i = 0; i < tamanho; i++) {
            campo = campos.get(i);
            if (campo.foiAtirado() || (!(campo.isAtiravel()))) {
                qtdInv++;
            } else if (campo.getObjeto() instanceof Arma) {
                campo.setAtirado(true);
                campo.setAtiravel(false);
                objeto = campo.getObjeto();
                arma = (Arma) objeto;
                if (arma instanceof CarroCombate) {
                    camposArma = arma.getCamposArma();
                    novoCampos = new ArrayList<Campo>();
                    for (Campo c : camposArma) {
                        if ((c.getCoordenada().getX() == campo.getCoordenada().getX()) && (c.getCoordenada().getY() == campo.getCoordenada().getY())) {
                            qtdAcer++;
                        } else {
                            novoCampos.add(c);
                        }
                    }
                    arma.setCamposArma(novoCampos);
                    // CONTINUA A VEZ
                    if(novoCampos.size() == 0){
                        arma.setVivo(false);
                    }
                }
            }
        }
        //Atira de novo
        if(qtdInv == (i-1)){
            return -1;
        }else {
            return qtdAcer;
        }
    }
}
