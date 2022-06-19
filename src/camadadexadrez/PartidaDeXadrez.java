package camadadexadrez;

import camadadexadrez.pecas.*;
import camadadotabuleiro.Peca;
import camadadotabuleiro.Posicao;
import camadadotabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaDeXadrez {
    private int turno;
    private Color jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;

    private List<Peca> pecaNoTabuleiro = new ArrayList<>();
    private List<Peca> pecaCapturada = new ArrayList<>();

    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Color.BRANCO;
        iniciandoJogo();
    }

    public int getTurno() {
        return turno;
    }

    public Color getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
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

        if (testeCheck(jogadorAtual)) {
            desfazerMovimento(inicial, destino, capturaPeca);
            throw new XadrezException("Voce nao pode se colocar em check");
        }
        check = (testeCheck(oponente(jogadorAtual))) ? true : false;
        if (testeCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }
        return (PecaDeXadrez) capturaPeca;
    }

    private Peca makeMove(Posicao inicial, Posicao destino) {
        PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(inicial);
        p.incrementarMovimento();
        Peca capturadaPeca = tabuleiro.removerPeca(destino);
        tabuleiro.localPeca(p, destino);

        if (capturadaPeca != null) {
            pecaNoTabuleiro.remove(capturadaPeca);
            pecaCapturada.add(capturadaPeca);
        }
        return capturadaPeca;
    }

    private void desfazerMovimento(Posicao inicial, Posicao destino, Peca capturadaPeca) {
        PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removerPeca(destino);
        p.decrementarMovimento();
        tabuleiro.localPeca(p, inicial);

        if (capturadaPeca != null) {
            tabuleiro.localPeca(capturadaPeca, destino);
            pecaCapturada.remove(capturadaPeca);
            pecaNoTabuleiro.add(capturadaPeca);
        }
    }

    private void validateInicialPosicao(Posicao posicao) {
        if (!tabuleiro.temUmaPeca(posicao)) {
            throw new XadrezException("Nao existe peca na posicao de origem.");
        }

        if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getColor()) {
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

    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
    }

    private Color oponente(Color color) {
        return (color == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
    }

    private PecaDeXadrez rei(Color color) {
        List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getColor() == color).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof Rei) {
                return (PecaDeXadrez) p;
            }
        }
        throw new IllegalStateException("Não existe o rei da cor " + color + " no tabuleiro");
    }

    private boolean testeCheck(Color color) {
        Posicao posicaoRei = rei(color).getXadrezPosicao().toPosition();
        List<Peca> pecasOponente = pecaNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getColor() == oponente(color)).collect(Collectors.toList());
        for (Peca p : pecasOponente) {
            boolean[][] mat = p.possibilidadesMovimentos();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {

                return true;
            }
        }
        return false;
    }

    private boolean testeCheckMate(Color color) {
        if (!testeCheck(color)) {
            return false;
        }
        List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getColor() == color).collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] mat = p.possibilidadesMovimentos();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao inicial = ((PecaDeXadrez) p).getXadrezPosicao().toPosition();
                        Posicao destino = new Posicao(i, j);
                        Peca capturaPeca = makeMove(inicial, destino);
                        boolean testandoCheck = testeCheck(color);
                        desfazerMovimento(inicial, destino, capturaPeca);
                        if (!testandoCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    private void coordenadasDoXadrez(char coluna, int linha, PecaDeXadrez peca) {
        tabuleiro.localPeca(peca, new XadrezPosicao(coluna, linha).toPosition());
        pecaNoTabuleiro.add(peca);
    }

    private void iniciandoJogo() {
        coordenadasDoXadrez('a', 1, new Torre(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('b', 1, new Cavalo(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('g', 1, new Cavalo(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('c', 1, new Bispo(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('f', 1, new Bispo(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('e', 1, new Rei(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('h', 1, new Torre(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('a', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('b', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('c', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('d', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('e', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('f', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('g', 2, new Peao(tabuleiro, Color.BRANCO));
        coordenadasDoXadrez('h', 2, new Peao(tabuleiro, Color.BRANCO));

        coordenadasDoXadrez('a', 8, new Torre(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('c', 8, new Bispo(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('f', 8, new Bispo(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('e', 8, new Rei(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('h', 8, new Torre(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('a', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('b', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('c', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('d', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('e', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('f', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('g', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('h', 7, new Peao(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('b', 8, new Cavalo(tabuleiro, Color.PRETO));
        coordenadasDoXadrez('g', 8, new Cavalo(tabuleiro, Color.PRETO));

    }
}
