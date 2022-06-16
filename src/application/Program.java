package application;

import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;
import camadadexadrez.XadrezPosicao;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;
import camadadotabuleiro.TabuleiroException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        List<PecaDeXadrez> capturadas = new ArrayList<>();


        while (true) {
            try {
                UI.clearScreen();
                UI.printJogo(partidaDeXadrez, capturadas);
                System.out.println();
                System.out.print("Inicial: ");
                XadrezPosicao inicial = UI.lerPosicaoXadrez(sc);
             boolean[][] possibilidadesMovimento = partidaDeXadrez.possiveisMovimentos(inicial);
             UI.clearScreen();
             UI.printTabuleiro(partidaDeXadrez.getPecas(), possibilidadesMovimento);

                System.out.println();
                System.out.print("Destino: ");
                XadrezPosicao destino = UI.lerPosicaoXadrez(sc);

                PecaDeXadrez capturaPeca = partidaDeXadrez.movimentoDaPeca(inicial, destino);
                if (capturaPeca != null){
                    capturadas.add(capturaPeca);
                }

            } catch (TabuleiroException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
