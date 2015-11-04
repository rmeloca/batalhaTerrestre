/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.JogoController;
import Jogo.Alvo.*;
import Jogo.Jogador;
import Jogo.Jogo;
import Jogo.Tabuleiro.Campo;
import Jogo.Tabuleiro.Grelha;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class textView {

    private static JogoController jogoController;

    public static void main(String[] args) {
        jogoController = new JogoController();

        System.out.println("Batalha Terrestre");
        System.out.print("Colhendo credenciais...");
        Jogador jogador1 = new Jogador(JOptionPane.showInputDialog("Seu nome"));
        System.out.println("Confirmadas");
        System.out.print("Rastreando Inimigo...");
        Jogador jogador2 = new Jogador(JOptionPane.showInputDialog("Inimigo"));
        System.out.println("Encontrado");

        System.out.print("Iniciando solução diplomática...");
        Jogo jogo = new Jogo(10, jogador1, jogador2);
        jogoController.addJogo(jogo);
        System.out.println("Diplomacia esgotada");
        System.out.println("Comandante, defenda o seu território contra as forças inimigas");

        System.out.println(jogador1.getNome() + ", escolha suas armas");

        jogo.getEstrategia1().addArma(new Guarani());
        jogo.getEstrategia1().addArma(new M15());
        jogo.getEstrategia1().addArma(new M15());
        jogo.getEstrategia1().addArma(new Astros2020());
        jogo.getEstrategia1().addArma(new Astros2020());
        jogo.getEstrategia1().addArma(new M60Patton());
        jogo.getEstrategia1().addArma(new M60Patton());
        jogo.getEstrategia1().addArma(new L118());

        System.out.print("Movendo arsenal...");
        jogo.getEstrategia1().dispoeArmas();
        System.out.println("Arsenal implantado");

        System.out.println(jogador1.getNome() + ", comande suas tropas");

        System.out.println(jogador1.getNome() + ", ajuste seus explosivos");

        imprimirGrelha(jogo.getEstrategia1().getGrelha());
        System.out.println("Aguardando embuste inimigo...");

        System.out.println(jogador2.getNome() + ", escolha suas armas");
        jogo.getEstrategia2().addArma(new Guarani());
        jogo.getEstrategia2().addArma(new M15());
        jogo.getEstrategia2().addArma(new M15());
        jogo.getEstrategia2().addArma(new Astros2020());
        jogo.getEstrategia2().addArma(new Astros2020());
        jogo.getEstrategia2().addArma(new M60Patton());
        jogo.getEstrategia2().addArma(new M60Patton());
        jogo.getEstrategia2().addArma(new L118());

        System.out.print("Movendo arsenal...");
        jogo.getEstrategia2().dispoeArmas();
        System.out.println("Arsenal implantado");

        System.out.println(jogador2.getNome() + ", comande suas tropas");

        System.out.println(jogador2.getNome() + ", ajuste seus explosivos");

        imprimirGrelha(jogo.getEstrategia1().getGrelha());

        System.out.println("Confronto avistado");
        System.out.print("Camuflando as tropas...");
        System.out.println("Camufladas");

        jogo.inicializar();
        System.out.println("Você detonou armas inimigas com sua M15");
        System.out.println("Você sofreu baixas");

        imprimirJogo(jogo);

        JOptionPane.showInputDialog("Qual coordenada, comandante " + jogador1.getNome() + "?");
        JOptionPane.showInputDialog("Qual coordenada, comandante " + jogador2.getNome() + "?");
    }

    public static void imprimirGrelha(Grelha grelha) {
        System.out.println();
        System.out.print("#");
        for (int i = 0; i < grelha.getDimensao(); i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (Campo[] linha : grelha.getCampos()) {
            System.out.print(linha[0].getCoordenada().getX());
            for (Campo campo : linha) {
                System.out.print(" " + campo.getObjeto().toString());
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void imprimirJogo(Jogo jogo) {
        Campo campo;
        Campo[] campos;
        Terra terra = new Terra();

        System.out.println();

        System.out.print("#");
        for (int i = 0; i < jogo.getDimensao(); i++) {
            System.out.print(" " + i);
        }
        System.out.print("\t");
        System.out.print("#");
        for (int i = 0; i < jogo.getDimensao(); i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < jogo.getDimensao(); i++) {
            campos = jogo.getEstrategia1().getGrelha().getCampos()[i];
            System.out.print(campos[0].getCoordenada().getX());
            for (int j = 0; j < jogo.getDimensao(); j++) {
                campo = campos[j];
                System.out.print(" ");
                if (campo.foiAtirado()) {
                    System.out.print(campo.getObjeto().toString());
                } else {
                    System.out.print(terra.toString());
                }
            }
            System.out.print("\t");
            campos = jogo.getEstrategia2().getGrelha().getCampos()[i];
            System.out.print(campos[0].getCoordenada().getX());
            for (int j = 0; j < jogo.getDimensao(); j++) {
                campo = campos[j];
                System.out.print(" ");
                if (campo.foiAtirado()) {
                    System.out.print(campo.getObjeto().toString());
                } else {
                    System.out.print(terra.toString());
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
