package manualdejogos.model;

public class Jogo extends ProdutoDigital {
    private int idadeRecomendada;
    private Genero genero;
    private boolean multiplayer;
    private double taxaDesconto;

    public Jogo(String nome, double precoBase, int id, int idadeRecomendada, Genero genero, boolean multiplayer, double taxaDesconto) {
        super(nome, precoBase, id);
        this.idadeRecomendada = idadeRecomendada;
        this.genero = genero;
        this.multiplayer = multiplayer;
        this.taxaDesconto = taxaDesconto;


    }

    public int getIdadeRecomendada() {
        return idadeRecomendada;
    }

    public void setIdadeRecomendada(int idadeRecomendada) {
        this.idadeRecomendada = idadeRecomendada;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public double getTaxaDesconto() {
        return taxaDesconto;
    }

    public void setTaxaDesconto(double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    @Override

    public double calcularPrecoFinal() {
        double desconto = (getTaxaDesconto() / 100) * getPrecoBase();
        return getPrecoBase() - desconto;
    }

    @Override

    public String toString() {
        return super.toString() + "\nIdade: " + idadeRecomendada + "\nGênero: " + getGenero() + "\nMultiplayer: " + (isMultiplayer() ? "Sim" : "Não") + "\nTaxa de Desconto: " + getTaxaDesconto() + "%";
    }
}
