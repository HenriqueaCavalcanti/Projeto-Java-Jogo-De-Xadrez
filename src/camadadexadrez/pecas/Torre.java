package camadadexadrez.pecas;

import camadadexadrez.Color;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Tabuleiro;

public class Torre extends PecaDeXadrez {

    public Torre(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "T";
    }
}