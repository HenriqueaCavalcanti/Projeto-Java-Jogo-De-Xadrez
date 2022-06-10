package camadadexadrez.pecas;

import camadadexadrez.Color;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Tabuleiro;

public class Rei extends PecaDeXadrez {

    public Rei(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "R";
    }
}
