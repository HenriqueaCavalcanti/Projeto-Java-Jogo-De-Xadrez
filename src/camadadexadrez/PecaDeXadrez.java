package camadadexadrez;

import camadadotabuleiro.Peca;
import camadadotabuleiro.Posicao;
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

    protected boolean adversario(Posicao posicao) {
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getColor() != color;
    }
}
