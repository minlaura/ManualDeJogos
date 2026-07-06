package manualdejogos.model;

import manualdejogos.exception.IdadeInsuficienteException;
import manualdejogos.exception.JogoBaseNaoEncontradoException;
import manualdejogos.exception.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private int idade;
    private double saldo;
    private List<ProdutoDigital> biblioteca = new ArrayList<>();

    public Usuario(String nome, int idade, double saldo) {
        this.nome = nome;
        this.idade = idade;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<ProdutoDigital> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(List<ProdutoDigital> biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void adicionarSaldo(double valor) {
        saldo = saldo + valor;

    }

    public boolean possuiProduto(ProdutoDigital produto) {
        return biblioteca.contains(produto);
    }

    public void comprar(ProdutoDigital produto) throws IdadeInsuficienteException, JogoBaseNaoEncontradoException, SaldoInsuficienteException {

        if (produto instanceof Jogo jogo) {
            if (getIdade() < jogo.getIdadeRecomendada()) {
                throw new IdadeInsuficienteException();
            }

        }
        if (produto instanceof DLC dlc) {
            if (!possuiProduto(dlc.getJogoBase())) {
                throw new JogoBaseNaoEncontradoException();

            }

        }
        double precoFinal = produto.calcularPrecoFinal();

        if (saldo < precoFinal) {

            throw new SaldoInsuficienteException();

        }
        saldo = saldo - precoFinal;
        biblioteca.add(produto);
    }

    public void mostrarBiblioteca() {
        for (ProdutoDigital produto : biblioteca) {
            System.out.println(produto);
        }

    }

    @Override
    public String toString() {
        return "Usuario {" + " \nNome ='" + nome + '\'' + ", \nIdade =" + idade + ",\nSaldo da Conta =" + String.format("%.2f", saldo) + '}';
    }
}


