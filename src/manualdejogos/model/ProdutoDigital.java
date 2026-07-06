package manualdejogos.model;

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

    public abstract double calcularPrecoFinal();


    @Override
    public String toString() {
        return " Produto Digital = " +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nPreço Base: R$ " + String.format("%.2f", precoBase);

    }
}
