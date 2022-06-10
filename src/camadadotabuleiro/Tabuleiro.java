package camadadotabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
       if (linhas < 1 || colunas < 1) {
           throw new TabuleiroException("Erro criando tabuleiro: É necessário que tenha ao menos uma linha e uma coluna");
       }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }


    public int getColunas() {
        return colunas;
    }


    public Peca peca(int linhas, int colunas) {
        if (!posicaoExiste(linhas, colunas)) {
            throw new TabuleiroException("Posição inválida no tabuleir");
        }
        return pecas[linhas][colunas];
    }

    public Peca peca(Posicao posicao) {
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posição do tabuleiro inválida");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void localPeca(Peca peca, Posicao posicao) {
        if(temUmaPeca(posicao)) {
            throw new TabuleiroException("Já existe uma peça na posição " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    public boolean posicaoExiste(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExiste(Posicao posicao) {
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }
    public boolean temUmaPeca(Posicao posicao){
        if (!posicaoExiste(posicao)) {
            throw new TabuleiroException("Posição inválida no tabuleiro");
        }
       return peca(posicao) != null;
    }
}
