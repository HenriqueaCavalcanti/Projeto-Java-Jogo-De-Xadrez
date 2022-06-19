package camadadexadrez.pecas;

import camadadexadrez.Color;
import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class Rei extends PecaDeXadrez {
    private PartidaDeXadrez partidaDeXadrez;

    public Rei(Tabuleiro tabuleiro, Color color, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, color);
        this.partidaDeXadrez = partidaDeXadrez;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao) {
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getColor() != getColor();

    }

    private boolean testeRookCastling(Posicao posicao) {
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getColor() == getColor() && p.getContagemMovimentos() == 0;
    }

    @Override
    public boolean[][] possibilidadesMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        //Acima
        Posicao p = new Posicao(0, 0);
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Abaixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Noroeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Nordeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Sudoeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //Sudeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
//        Movimento especial
        if (getContagemMovimentos() == 0 && !partidaDeXadrez.getCheck()) {
//            Torre ao lado do rei
            Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testeRookCastling(posicaoTorre1)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
                    mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }
//            Torre ao lado da rainha
            Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testeRookCastling(posicaoTorre2)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);

                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
                    mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }
        return mat;
    }
}
