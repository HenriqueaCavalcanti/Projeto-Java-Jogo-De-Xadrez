package camadadexadrez.pecas;

import camadadexadrez.Color;
import camadadexadrez.PartidaDeXadrez;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class Peao extends PecaDeXadrez {

    private PartidaDeXadrez partidaDeXadrez;
    public Peao(Tabuleiro tabuleiro, Color color, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, color);
        this.partidaDeXadrez = partidaDeXadrez;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibilidadesMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);
        if (getColor() == Color.BRANCO) {
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
//            Especial Movimento en passant para as peças brancas
            if (posicao.getLinha() == 3){
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExiste(esquerda) && adversario(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulneravel()){
                    mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExiste(direita) && adversario(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulneravel()){
                    mat[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        }
        else {
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContagemMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

//            Especial Movimento en passant para as peças pretas
            if (posicao.getLinha() == 4){
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExiste(esquerda) && adversario(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulneravel()){
                    mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExiste(direita) && adversario(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulneravel()){
                    mat[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }
        return mat;
    }
}
