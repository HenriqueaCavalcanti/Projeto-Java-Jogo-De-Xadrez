package camadadexadrez;

import camadadexadrez.pecas.Rei;
import camadadexadrez.pecas.Torre;
import camadadotabuleiro.Peca;
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
    public PecaDeXadrez movimentoDaPeca(XadrezPosicao posicaoInicial, XadrezPosicao posicaoDestino){
        Posicao inicial = posicaoInicial.toPosition();
        Posicao destino = posicaoDestino.toPosition();
        validateInicialPosicao(inicial);
        Peca capturaPeca = makeMove(inicial, destino);
        return (PecaDeXadrez) capturaPeca;
    }
    private Peca makeMove(Posicao inicial, Posicao destino){
        Peca p = tabuleiro.removerPeca(inicial);
        Peca capturadaPeca = tabuleiro.removerPeca(destino);
        tabuleiro.localPeca(p, destino);
        return capturadaPeca;
    }
    private void validateInicialPosicao(Posicao posicao){
        if(!tabuleiro.temUmaPeca(posicao)){
            throw new XadrezException("Não existe peça na posição de origem.");
        }
    }

    private void coordenadasDoXadrez(char coluna, int linha, PecaDeXadrez peca){
        tabuleiro.localPeca(peca, new XadrezPosicao(coluna, linha).toPosition());
    }
    private void iniciandoJogo(){
        coordenadasDoXadrez('b', 6, new Torre(tabuleiro, Color.WHITE));
        coordenadasDoXadrez('a', 5, new Torre(tabuleiro, Color.BLACK));
        coordenadasDoXadrez('c', 2, new Torre(tabuleiro, Color.WHITE));
        coordenadasDoXadrez('d', 2, new Torre(tabuleiro, Color.WHITE));
        coordenadasDoXadrez('e', 2, new Torre(tabuleiro, Color.WHITE));
        coordenadasDoXadrez('e', 1, new Torre(tabuleiro, Color.WHITE));
        coordenadasDoXadrez('d', 1, new Rei(tabuleiro, Color.WHITE));

        coordenadasDoXadrez('c', 7, new Torre(tabuleiro, Color.BLACK));
        coordenadasDoXadrez('c', 8, new Torre(tabuleiro, Color.BLACK));
        coordenadasDoXadrez('d', 7, new Torre(tabuleiro, Color.BLACK));
        coordenadasDoXadrez('e', 7, new Torre(tabuleiro, Color.BLACK));
        coordenadasDoXadrez('e', 8, new Torre(tabuleiro, Color.BLACK));
        coordenadasDoXadrez('d', 8, new Rei(tabuleiro, Color.BLACK));
    }

}
