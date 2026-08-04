package manualdejogos.model;

import manualdejogos.exception.IdadeInsuficienteException;
import manualdejogos.exception.JogoBaseNaoEncontradoException;
import manualdejogos.exception.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um usuário da plataforma.
 *
 * Um usuário possui informações pessoais,
 * saldo disponível e uma biblioteca de
 * produtos adquiridos.
 *
 * Também é responsável por realizar compras
 * e gerenciar sua biblioteca.
 */
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

    public int getIdade() {
        return idade;
    }

    public double getSaldo() {
        return saldo;
    }

    public void alterarNome(String novoNome) {
        this.nome = novoNome;
    }

    public void adicionarSaldo(double valor) {
        saldo += valor;
    }

    /**
     * Verifica se o usuário possui um produto em sua biblioteca.
     *
     * @param produto produto a ser verificado
     * @return true se o produto estiver na biblioteca
     */
    public boolean possuiProduto(ProdutoDigital produto) {
        return biblioteca.contains(produto);
    }

    /**
     * Realiza a compra de um produto digital.
     *
     * @param produto produto a ser adquirido
     * @throws IdadeInsuficienteException se o usuário não possuir idade suficiente
     * @throws JogoBaseNaoEncontradoException se a DLC for adquirida sem o jogo base
     * @throws SaldoInsuficienteException se o saldo for insuficiente
     */
    public void comprar(ProdutoDigital produto)
            throws IdadeInsuficienteException,
            JogoBaseNaoEncontradoException,
            SaldoInsuficienteException {

        if (produto instanceof Jogo jogo) {
            if (idade < jogo.getIdadeRecomendada()) {
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

        saldo -= precoFinal;
        biblioteca.add(produto);
    }

    public void mostrarBiblioteca() {
        for (ProdutoDigital produto : biblioteca) {
            System.out.println(produto);
        }
    }

    @Override
    public String toString() {
        return "Usuario {"
                + "\nNome: " + nome
                + "\nIdade: " + idade
                + "\nSaldo da conta: R$ " + String.format("%.2f", saldo)
                + "\n}";
    }
}