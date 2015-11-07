/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

/**
 *
 * @author a1137131
 */
public class Jogador {

    private String nome;

    public Jogador(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return ((Jogador) obj).nome.equals(nome);
    }

}
