/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

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

    private final ObjectInputStream entrada;
    private final ObjectOutputStream saida;

    public Transmissao(ObjectInputStream origemInputStream, ObjectOutputStream destinoOutputStream) {
        entrada = origemInputStream;
        saida = destinoOutputStream;
    }

    @Override
    public void run() {
        Coordenada coordenada;
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
