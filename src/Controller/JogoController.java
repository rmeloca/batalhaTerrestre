/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jogo.Alvo.*;
import Jogo.Jogo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class JogoController {

    private List<Jogo> jogos;
    private List<Arma> armasDisponiveis;

    public JogoController() {
        jogos = new ArrayList<>();
        armasDisponiveis = new ArrayList<>();
        armasDisponiveis.add(new M60Patton());
        armasDisponiveis.add(new Astros2020());
        armasDisponiveis.add(new L118());
        armasDisponiveis.add(new Guarani());
        armasDisponiveis.add(new M15());
    }

    public void addJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public List<Arma> getArmasDisponiveis() {
        return armasDisponiveis;
    }

    public void persistirJogos() throws Exception {
        FileOutputStream arquivo = new FileOutputStream("Jogos.dat");
        ObjectOutputStream gravar = new ObjectOutputStream(arquivo);
        gravar.writeObject(jogos);
        gravar.close();
        arquivo.flush();
        arquivo.close();
    }

    public void recuperarJogos() throws Exception {
        FileInputStream arquivo = new FileInputStream("Jogos.dat");
        ObjectInputStream ler = new ObjectInputStream(arquivo);
        this.jogos = (ArrayList<Jogo>) ler.readObject();
        ler.close();
        arquivo.close();
    }
}
