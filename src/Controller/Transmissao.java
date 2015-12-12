/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jogo.Tabuleiro.Campo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class Transmissao extends Thread {

    ObjectInputStream entrada;
    ObjectOutputStream saida;
    List<Campo> camposSelecionados;

    public Transmissao(ObjectInputStream origemInputStream, ObjectOutputStream destinoOutputStream) {
        entrada = origemInputStream;
        saida = destinoOutputStream;
    }

    @Override
    public void run() {
        try {
            camposSelecionados = (List<Campo>) entrada.readObject();
            saida.flush();
            saida.writeObject(camposSelecionados);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Transmissao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
