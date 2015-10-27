/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Tabuleiro;

import Jogo.Alvo.Objeto;

/**
 *
 * @author romulo
 */
public class Campo {

    private Objeto objeto;
    private boolean atirado;
    private Coordenada coordenada;
    private boolean atiravel;

    public Campo(Objeto objeto, Coordenada coordenada){
        setObjeto(objeto);
        setCoordenada(coordenada);
        setAtirado(false);
        setAtiravel(true);
    }
    
    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public boolean foiAtirado() {
        return atirado;
    }

    public void setAtirado(boolean atirado) {
        this.atirado = atirado;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    private void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public boolean isAtiravel() {
        return atiravel;
    }

    public void setAtiravel(boolean atiravel) {
        this.atiravel = atiravel;
    }

}
