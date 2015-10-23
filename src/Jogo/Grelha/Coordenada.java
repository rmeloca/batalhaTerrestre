/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Grelha;

/**
 *
 * @author romulo
 */
public class Coordenada {

    private int x;
    private int y;

    public Coordenada(int x, int y) {

    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        if (x > 0) {
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        if (y > 0) {
        this.y = y;
    }
    }

}
