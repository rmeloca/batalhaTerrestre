/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jogo.Alvo.*;
import Jogo.Jogo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class JogoController {

    List<Jogo> jogos;
    List<Arma> armasDisponiveis;

    public JogoController() {
        jogos = new ArrayList<>();
        armasDisponiveis = new ArrayList<>();
        armasDisponiveis.add(new M60Patton());
        armasDisponiveis.add(new Astros2020());
        armasDisponiveis.add(new L118());
        armasDisponiveis.add(new Guarani());
        armasDisponiveis.add(new Mina());
    }

    public void addJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }
}
