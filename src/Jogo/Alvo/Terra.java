/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

/**
 *
 * @author romulo
 */
public final class Terra extends Objeto {

    public Terra() {
        setImagem("80_terra.png");
    }

    @Override
    public String toString() {
        if (campos != null && campos.get(0).foiAtirado()) {
            return "S";
        }
        return "T";
    }

}
