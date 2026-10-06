package manualdejogos.model;

import manualdejogos.exception.IdadeInsuficienteException;
import manualdejogos.exception.JogoBaseNaoEncontradoException;
import manualdejogos.exception.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private List<ProdutoDigital> carrinho = new ArrayList<>();
    private List<ProdutoDigital> biblioteca = new ArrayList<>();
    private Set<ProdutoDigital> produtosFavoritos = new HashSet<>();

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

    public boolean estaFavoritado(ProdutoDigital produto) {
        return produtosFavoritos.contains(produto);
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
     * Valida as regras necessárias para a compra de um produto.
     */
    private void validarProdutoParaCompra(
            ProdutoDigital produto,
            List<ProdutoDigital> produtosDaCompra)
            throws IdadeInsuficienteException,
            JogoBaseNaoEncontradoException {

        if (produto instanceof RestricaoEtaria restricao) {
            if (idade < restricao.getIdadeRecomendada()) {
                throw new IdadeInsuficienteException();
            }
        }

        if (produto instanceof DLC dlc) {
            if (!possuiProduto(dlc.getJogoBase())
                    && !produtosDaCompra.contains(dlc.getJogoBase())) {

                throw new JogoBaseNaoEncontradoException();
            }
        }
    }

    /**
     * Realiza a compra de um único produto digital.
     */
    public void comprar(ProdutoDigital produto)
            throws IdadeInsuficienteException,
            JogoBaseNaoEncontradoException,
            SaldoInsuficienteException {

        validarProdutoParaCompra(produto, List.of());

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

    public void favoritarProduto(ProdutoDigital produto) {
        produtosFavoritos.add(produto);
    }

    public void desfavoritarProduto(ProdutoDigital produto) {
        produtosFavoritos.remove(produto);
    }

    public void mostrarProdutosFavoritos() {
        for (ProdutoDigital produto : produtosFavoritos) {
            System.out.println(produto);
        }
    }

    // CARRINHO DE COMPRAS

    public void adicionarProdutoAoCarrinho(ProdutoDigital produto) {
        carrinho.add(produto);
    }

    public void removerProdutoDoCarrinho(ProdutoDigital produto) {
        carrinho.remove(produto);
    }

    public double calcularTotalDoCarrinho() {

        double total = carrinho.stream()
                .map(produto -> produto.calcularPrecoFinal())
                .reduce(0.0, (soma, valor) -> soma + valor);

        return total;
    }

    public void finalizarCompra()
            throws SaldoInsuficienteException,
            IdadeInsuficienteException,
            JogoBaseNaoEncontradoException {

        for (ProdutoDigital produto : carrinho) {
            validarProdutoParaCompra(produto, carrinho);
        }

        double total = calcularTotalDoCarrinho();

        if (saldo < total) {
            throw new SaldoInsuficienteException();
        }
        // Só altera o usuário depois que todas as validações passaram.
        saldo -= total;
        biblioteca.addAll(carrinho);
        carrinho.clear();
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