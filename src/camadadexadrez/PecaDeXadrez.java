package camadadexadrez;

import camadadotabuleiro.Peca;
import camadadotabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
    private Color color;

    public PecaDeXadrez(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
