/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author romulo
 */
public abstract class Objeto implements Serializable {

    String imagem;
    List<Campo> campos;

    public String getImagem() {
        return "/Icon/" + imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public abstract String toString();
}
