package camadadexadrez;

import camadadexadrez.pecas.Rei;
import camadadexadrez.pecas.Torre;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class PartidaDeXadrez {
    private Tabuleiro tabuleiro;

    public PartidaDeXadrez(){
        tabuleiro = new Tabuleiro(8, 8);
        iniciandoJogo();
    }

    public PecaDeXadrez[][] getPecas(){
        PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i=0;i<tabuleiro.getLinhas(); i++) {
            for(int j=0; j<tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
    private void iniciandoJogo(){
        tabuleiro.localPeca(new Rei(tabuleiro, Color.WHITE), new Posicao(2, 1));
        tabuleiro.localPeca(new Torre(tabuleiro, Color.BLACK), new Posicao(3, 4));
    }

}
