/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogo;

import Jogo.Tabuleiro.Grelha;

/**
 *
 * @author a1137131
 */
public class Jogador {
 private String nome;
 private Grelha grelha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Grelha getGrelha() {
        return grelha;
    }

}
