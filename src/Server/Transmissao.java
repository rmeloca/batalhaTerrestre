/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jogo.Tabuleiro.Coordenada;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class Transmissao extends Thread {

    ObjectInputStream entrada;
    ObjectOutputStream saida;
    Coordenada coordenada;

    public Transmissao(ObjectInputStream origemInputStream, ObjectOutputStream destinoOutputStream) {
        entrada = origemInputStream;
        saida = destinoOutputStream;
    }

    @Override
    public void run() {
        try {
            while (true) {
                coordenada = (Coordenada) entrada.readObject();
                saida.flush();
                saida.writeObject(coordenada);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Transmissao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
