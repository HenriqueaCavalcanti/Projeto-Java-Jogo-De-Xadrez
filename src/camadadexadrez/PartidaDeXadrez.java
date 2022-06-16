package camadadexadrez;

import camadadexadrez.pecas.Rei;
import camadadexadrez.pecas.Torre;
import camadadotabuleiro.Peca;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

public class PartidaDeXadrez {
    private int turno;
    private Color jogadorAtual;
    private Tabuleiro tabuleiro;

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Color.WHITE;
        iniciandoJogo();
    }
    public int getTurno(){
        return turno;
    }
    public Color getJogadorAtual(){
        return jogadorAtual;
    }

    public PecaDeXadrez[][] getPecas() {
        PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possiveisMovimentos(XadrezPosicao posicaoInicial) {
        Posicao posicao = posicaoInicial.toPosition();
        validateInicialPosicao(posicao);
        return tabuleiro.peca(posicao).possibilidadesMovimentos();
    }

    public PecaDeXadrez movimentoDaPeca(XadrezPosicao posicaoInicial, XadrezPosicao posicaoDestino) {
        Posicao inicial = posicaoInicial.toPosition();
        Posicao destino = posicaoDestino.toPosition();
        validateInicialPosicao(inicial);
        validateFinalPosicao(destino, inicial);
        Peca capturaPeca = makeMove(inicial, destino);
        proximoTurno();
        return (PecaDeXadrez) capturaPeca;
    }

    private Peca makeMove(Posicao inicial, Posicao destino) {
        Peca p = tabuleiro.removerPeca(inicial);
        Peca capturadaPeca = tabuleiro.removerPeca(destino);
        tabuleiro.localPeca(p, destino);
        return capturadaPeca;
    }

    private void validateInicialPosicao(Posicao posicao) {
        if (!tabuleiro.temUmaPeca(posicao)) {
            throw new XadrezException("Nao existe peca na posicao de origem.");
        }

         if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getColor()) {
             throw new XadrezException("A peca escolhida nao e sua.");
         }
        if (!tabuleiro.peca(posicao).impossibilidadeMovimento()) {
            throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida.");
        }
    }

    private void validateFinalPosicao(Posicao destino, Posicao inicial) {
        if (!tabuleiro.peca(inicial).possibilidadeMovimeto(destino)) {
            throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino.");
        }
    }
private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
}

    private void coordenadasDoXadrez(char coluna, int linha, PecaDeXadrez peca) {
        tabuleiro.localPeca(peca, new XadrezPosicao(coluna, linha).toPosition());
    }

    private void iniciandoJogo() {
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
