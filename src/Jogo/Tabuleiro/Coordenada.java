/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Tabuleiro;

import java.io.Serializable;

/**
 *
 * @author romulo
 */
public final class Coordenada implements Serializable {

    private int x;
    private int y;

    public Coordenada(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        if (x >= 0) {
            this.x = x;
        } else {
            this.x = 0;
        }
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        if (y >= 0) {
            this.y = y;
        } else {
            this.y = 0;
        }
    }

    public Coordenada north() {
        return new Coordenada(x + 1, y);
    }

    public Coordenada northwest() {
        return new Coordenada(x + 1, y - 1);
    }

    public Coordenada northeast() {
        return new Coordenada(x + 1, y + 1);
    }

    public Coordenada south() {
        return new Coordenada(x - 1, y);
    }

    public Coordenada southwest() {
        return new Coordenada(x - 1, y - 1);
    }

    public Coordenada southeast() {
        return new Coordenada(x - 1, y + 1);
    }

    public Coordenada east() {
        return new Coordenada(x, y + 1);
    }

    public Coordenada west() {
        return new Coordenada(x, y - 1);
    }

    /**
     * Retorna igualdade entre coordenadas
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return ((Coordenada) obj).getX() == x && ((Coordenada) obj).getY() == y;
    }

}
