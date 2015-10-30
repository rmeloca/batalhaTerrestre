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

    private List<Campo> camposArma;
    private boolean vivo;
    private int tamanho;

    public List<Campo> getCamposArma() {
        return camposArma;
    }

    public void setCamposArma(List<Campo> camposArma) {
        this.camposArma = camposArma;
    }

    
    public boolean rotacionar() {
        if (tamanho == 3) {
            Campo campoInf = camposObjeto.get(0);
            Campo campoMei = camposObjeto.get(1);
            Campo campoSup = camposObjeto.get(2);
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

            List<Campo> novaLista = new ArrayList<Campo>();
            novaLista.add(novoCampoInf);
            novaLista.add(campoMei);
            novaLista.add(novoCampoSup);
            camposObjeto = novaLista;

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

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean isVivo() {
        return vivo;
    }

}
