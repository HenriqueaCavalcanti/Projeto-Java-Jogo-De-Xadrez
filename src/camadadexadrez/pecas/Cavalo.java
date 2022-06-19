package camadadexadrez.pecas;

import camadadexadrez.Color;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class Cavalo extends PecaDeXadrez {

    public Cavalo(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "C";
    }
    private boolean podeMover(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getColor() != getColor();

    }
    @Override
    public boolean[][] possibilidadesMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p =new Posicao(0, 0);
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        return mat;
    }
}