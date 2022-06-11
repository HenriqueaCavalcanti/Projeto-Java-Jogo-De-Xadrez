package application;

import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;
import camadadexadrez.XadrezPosicao;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        while (true) {
            UI.printTabuleiro(partidaDeXadrez.getPecas());
            System.out.println();
            System.out.print("Inicial: ");
            XadrezPosicao inicial = UI.lerPosicaoXadrez(sc);

            System.out.println();
            System.out.print("Destino: ");
            XadrezPosicao destino = UI.lerPosicaoXadrez(sc);

            PecaDeXadrez CapturaPeca = partidaDeXadrez.movimentoDaPeca(inicial, destino);
        }
    }
}
