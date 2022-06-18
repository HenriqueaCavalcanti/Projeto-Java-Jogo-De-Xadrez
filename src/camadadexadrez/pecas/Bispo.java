package camadadexadrez.pecas;

import camadadexadrez.Color;
import camadadexadrez.PecaDeXadrez;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class Bispo extends PecaDeXadrez{


        public Bispo(Tabuleiro tabuleiro, Color color) {
            super(tabuleiro, color);
        }

        @Override
        public String toString() {
            return "B";
        }

        @Override
        public boolean[][] possibilidadesMovimentos() {
            boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
            Posicao p = new Posicao(0, 0);
            //Para Noroeste
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
               p.setValores(p.getLinha() - 1, p.getColuna() - 1);
            }
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
//        Nordeste
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
               p.setValores(p.getLinha() - 1, p.getColuna() + 1);
            }
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
//        Sudeste
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
                p.setValores(p.getLinha() + 1, p.getColuna() + 1);
            }
            if (getTabuleiro().posicaoExiste(p) && adversario(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
//         Sudoeste
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
                p.setValores(p.getLinha() + 1, p.getColuna() - 1);
            }
            if (getTabuleiro().posicaoExiste(p) && adversario(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            return mat;
        }
    }
