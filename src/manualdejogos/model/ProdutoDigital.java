package manualdejogos.model;

/**
 * Representa um produto disponível pra compra.
 *
 * Classe base para todos os produtos do sistema,
 * armazena as informações comuns: nomes, preço base e identificador.
 *
 * Cada produto deve implementar sua própria regra de cálcylo do preço final.
 *
 */


public abstract class ProdutoDigital {
    private String nome;
    private double precoBase;
    private int id;

    public ProdutoDigital(String nome, double precoBase, int id) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public int getId() {
        return id;
    }

    /**
     * Cada classe derivada aplica sua própria regra de cálculo, considerando descontos/valores específicos.
     *
     * @return  preço final de jogo
     */
    public abstract double calcularPrecoFinal();


    @Override
    public String toString() {
        return " Produto Digital = " + "\nID: " + id + "\nNome: " + nome + "\nPreço Base: R$ " + String.format("%.2f", precoBase);

    }
}
