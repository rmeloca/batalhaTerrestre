/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jogo.Jogo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class JogoController {

    List<Jogo> jogos;

    public JogoController() {
        jogos = new ArrayList<>();
    }

    public void addJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }
}
