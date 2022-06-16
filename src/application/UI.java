package application;

import camadadexadrez.Color;
import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;
import camadadexadrez.XadrezPosicao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static XadrezPosicao lerPosicaoXadrez(Scanner sc) {
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new XadrezPosicao(coluna, linha);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Erro lendo a posisão de xadrez.");
        }
    }

    public static void printJogo(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> capturadas) {
        printTabuleiro(partidaDeXadrez.getPecas());
        System.out.println();
        printcapturaPecas(capturadas);
        System.out.println();
        System.out.println("Turno: " + partidaDeXadrez.getTurno());
        System.out.println("Esperando o jogador: " + partidaDeXadrez.getJogadorAtual());
    }

    public static void printTabuleiro(PecaDeXadrez[][] peca) {
        for (int i = 0; i < peca.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < peca.length; j++) {
                printPecas(peca[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printTabuleiro(PecaDeXadrez[][] peca, boolean[][] possibilidadesMovimento) {
        for (int i = 0; i < peca.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < peca.length; j++) {
                printPecas(peca[i][j], possibilidadesMovimento[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPecas(PecaDeXadrez peca, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (peca.getColor() == Color.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printcapturaPecas(List<PecaDeXadrez> capturada) {
        List<PecaDeXadrez> braco = capturada.stream().filter(x -> x.getColor() == Color.BRANCO).collect(Collectors.toList());
        List<PecaDeXadrez> preto = capturada.stream().filter(x -> x.getColor() == Color.PRETO).collect(Collectors.toList());
        System.out.println("Captura de pecas: ");
        System.out.print("Pecas Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(braco.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Pecas Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(preto.toArray()));
        System.out.print(ANSI_RESET);


    }
}

