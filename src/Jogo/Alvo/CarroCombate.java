/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Grelha;
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
        int qtdInv = 0, qtdAcer = 0, i;
        int tamanho = campos.size();
        int camposAcertados = 0;
        Grelha grelha;
        for (i = 0; i < tamanho; i++) {
            campo = campos.get(i);
            if (campo.foiAtirado() || (!(campo.isAtiravel()))) {
                qtdInv++;
            } else if (campo.getObjeto() instanceof Arma) {
                campo.setAtirado(true);
                campo.setAtiravel(false);
                objeto = campo.getObjeto();
                arma = (Arma) objeto;
                grelha = campo.getGrelha();
                if (arma instanceof CarroCombate) {
                    camposArma = arma.getCampos();
                    for (Campo c : camposArma) {
                        if ((c.getCoordenada().getX() == campo.getCoordenada().getX()) && (c.getCoordenada().getY() == campo.getCoordenada().getY())) {
                            qtdAcer++;
                            c.setAtirado(true);
                        }
                        if (c.foiAtirado()) {
                            camposAcertados++;
                            if ((arma instanceof Astros2020) && (camposAcertados == 4)) {
                                arma.setAtiva(false);
                                grelha.setBordasInativas(camposArma);
                                return -1;
                            } else if ((arma instanceof M60Patton) && (camposAcertados == 3)) {
                                arma.setAtiva(false);
                                grelha.setBordasInativas(camposArma);

                                return -1;
                            } else if ((arma instanceof Guarani) && (camposAcertados == 2)) {
                                arma.setAtiva(false);
                                grelha.setBordasInativas(camposArma);
                                return -1;
                            } else if ((arma instanceof L118) && (camposAcertados == 1)) {
                                arma.setAtiva(false);
                                grelha.setBordasInativas(camposArma);
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        return qtdAcer;
    }
}
