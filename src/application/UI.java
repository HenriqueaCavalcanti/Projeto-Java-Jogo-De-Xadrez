package application;

import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;

public class UI {
    public static void printTabuleiro(PecaDeXadrez[][] peca) {
        for (int i = 0; i < peca.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < peca.length; j++) {
                printPecas(peca[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPecas(PecaDeXadrez peca) {
        if (peca == null) {
            System.out.print("-");
        } else {
            System.out.print(peca);
        }
        System.out.print(" ");
    }
}

