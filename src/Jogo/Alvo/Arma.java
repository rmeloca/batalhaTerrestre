/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.Alvo;

import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Coordenada;
import Jogo.Tabuleiro.Grelha;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a1137131
 */
public abstract class Arma extends Objeto {

    private boolean ativa;
    private int tamanho;

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public int getTamanho() {
        return tamanho;
    }

    protected void setTamanho(int tamanho) {
        if (tamanho > 0) {
            this.tamanho = tamanho;
        }
    }

    public boolean rotacionar() {
        if (tamanho == 3) {
            Campo campoInf = campos.get(0);
            Campo campoMei = campos.get(1);
            Campo campoSup = campos.get(2);
            Grelha grelha = campoMei.getGrelha();

            Coordenada coordenada = campoMei.getCoordenada();
            Campo novoCampoInf;
            Campo novoCampoSup;
            Campo todosCampos[][];

            if ((campoSup.getCoordenada().getX() == campoMei.getCoordenada().getX())
                    && (campoInf.getCoordenada().getX() == campoSup.getCoordenada().getX())) {
                todosCampos = grelha.getCampos();
                novoCampoSup = todosCampos[(coordenada.getX() - 1)][(coordenada.getY() + 1)];
                novoCampoInf = todosCampos[(coordenada.getX() - 1)][(coordenada.getY() - 1)];
            } else {
                todosCampos = grelha.getCampos();
                novoCampoSup = todosCampos[(coordenada.getX() + 1)][(coordenada.getY() + 1)];
                novoCampoInf = todosCampos[(coordenada.getX() - 1)][(coordenada.getY() - 1)];
            }
            if (!(novoCampoSup.getObjeto() instanceof Terra)) {
                return false;
            }
            if (!(novoCampoInf.getObjeto() instanceof Terra)) {
                return false;
            }
            trocarCampos(campoSup, novoCampoSup);
            trocarCampos(campoInf, novoCampoInf);

            List<Campo> novaLista = new ArrayList<>();
            novaLista.add(novoCampoInf);
            novaLista.add(campoMei);
            novaLista.add(novoCampoSup);
            campos = novaLista;

            return true;
        } else if (tamanho == 2) {
            Campo campoInf = campos.get(0);
            Campo campoSup = campos.get(1);

            Grelha grelha = campoInf.getGrelha();

            Coordenada coordenada = campoInf.getCoordenada();
            Campo novoCampoSup;
            Campo todosCampos[][];

            if ((campoSup.getCoordenada().getX() == campoInf.getCoordenada().getX())) {
                todosCampos = grelha.getCampos();
                novoCampoSup = todosCampos[(coordenada.getX() - 1)][(coordenada.getY() + 1)];

                Objeto obj1 = todosCampos[novoCampoSup.getCoordenada().getX() + 1][novoCampoSup.getCoordenada().getY() + 1].getObjeto();
                Objeto obj2 = todosCampos[novoCampoSup.getCoordenada().getX()][novoCampoSup.getCoordenada().getY() + 1].getObjeto();
                Objeto obj3 = todosCampos[novoCampoSup.getCoordenada().getX() - 1][novoCampoSup.getCoordenada().getY() + 1].getObjeto();

            } else {
                todosCampos = grelha.getCampos();
                novoCampoSup = todosCampos[(coordenada.getX() + 1)][(coordenada.getY() - 1)];

                Objeto obj1 = todosCampos[novoCampoSup.getCoordenada().getX() + 1][novoCampoSup.getCoordenada().getY() + 1].getObjeto();
                Objeto obj2 = todosCampos[novoCampoSup.getCoordenada().getX() + 1][novoCampoSup.getCoordenada().getY()].getObjeto();
                Objeto obj3 = todosCampos[novoCampoSup.getCoordenada().getX() + 1][novoCampoSup.getCoordenada().getY() - 1].getObjeto();
            }
            if (!(novoCampoSup.getObjeto() instanceof Terra)) {
                return false;
            } else if (true) {

            }

            trocarCampos(campoSup, novoCampoSup);
            List<Campo> novaLista = new ArrayList<>();
            novaLista.add(campoInf);
            novaLista.add(novoCampoSup);
            campos = novaLista;

            return true;
        }

        return true;
    }

    public void trocarCampos(Campo campo1, Campo campo2) {
        Objeto objeto1 = campo1.getObjeto();
        Objeto objeto2 = campo2.getObjeto();

        campo1.setObjeto(objeto2);
        campo2.setObjeto(objeto1);

        boolean atirado1 = campo1.foiAtirado();
        boolean atirado2 = campo2.foiAtirado();

        campo1.setAtirado(atirado2);
        campo2.setAtirado(atirado1);

        boolean atiravel1 = campo1.isAtiravel();
        boolean atiravel2 = campo2.isAtiravel();

        campo1.setAtiravel(atiravel2);
        campo2.setAtiravel(atiravel1);
    }
}
