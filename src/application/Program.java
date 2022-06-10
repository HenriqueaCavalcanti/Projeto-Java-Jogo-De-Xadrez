package application;

import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class Program {
    public static void main(String[] args) {
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        UI.printTabuleiro(partidaDeXadrez.getPecas());

    }
}
