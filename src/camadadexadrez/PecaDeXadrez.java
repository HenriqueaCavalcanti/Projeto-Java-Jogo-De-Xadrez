package camadadexadrez;

import camadadotabuleiro.Peca;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
    private Color color;
    private int contagemMovimentos;

    public PecaDeXadrez(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getContagemMovimentos() {
        return contagemMovimentos;
    }

    public void incrementarMovimento() {
        contagemMovimentos++;
    }

    public void decrementarMovimento() {
        contagemMovimentos--;
    }

    public XadrezPosicao getXadrezPosicao() {
        return XadrezPosicao.fromPosition(posicao);

    }


    protected boolean adversario(Posicao posicao) {
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getColor() != color;
    }
}
