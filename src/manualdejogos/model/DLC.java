package manualdejogos.model;

/**
 * Representa um conteúdo adicional (DLC)
 * pertencente a um jogo base.
 *
 * A DLC depende da existência do jogo base
 * e utiliza metade da taxa de desconto dele
 * para calcular seu preço final.
 */
public class DLC extends ProdutoDigital {
    private Jogo jogoBase;
    private double tamanhoGB;


    public DLC(String nome, double precoBase, int id, Jogo jogoBase, double tamanhoGB, double descontoSePossuiJogo) {
        super(nome, precoBase, id);
        this.jogoBase = jogoBase;
        this.tamanhoGB = tamanhoGB;

    }

    public Jogo getJogoBase() {
        return jogoBase;
    }

    public void setJogoBase(Jogo jogoBase) {
        this.jogoBase = jogoBase;
    }

    public double getTamanhoGB() {
        return tamanhoGB;
    }

    public void setTamanhoGB(double tamanhoGB) {
        this.tamanhoGB = tamanhoGB;
    }


    /**
     * Calcula o preço final da DLC utilizando
     * metade da taxa de desconto do jogo base.
     *
     * @return preço final da DLC.
     */
    @Override
    public double calcularPrecoFinal() {
        double descontoDlc = jogoBase.getTaxaDesconto() / 2;
        double valorDesconto = (descontoDlc / 100) * getPrecoBase();

        return getPrecoBase() - valorDesconto;
    }

    @Override
    public String toString() {
        return super.toString() + "\nJogo Base: " + getJogoBase().getNome() + "\nTamanho: " + getTamanhoGB();
    }
}