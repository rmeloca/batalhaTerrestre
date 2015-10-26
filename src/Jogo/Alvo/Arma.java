/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Grelha.Campo;
import java.util.List;

/**
 *
 * @author a1137131
 */
public abstract class Arma extends Objeto {

    int tamanho;
    List<Campo> campos;
    boolean vivo;
}
