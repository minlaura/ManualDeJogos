package manualdejogos.model;

import manualdejogos.exception.IdadeInsuficienteException;
import manualdejogos.exception.JogoBaseNaoEncontradoException;
import manualdejogos.exception.ProdutoNaoEncontradoException;
import manualdejogos.exception.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nome;
    private String cnpj;
    private List<ProdutoDigital> catalogo = new ArrayList<>();

    public Loja(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.catalogo = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutoDigital> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<ProdutoDigital> catalogo) {
        this.catalogo = catalogo;
    }


    public void adicionarProduto(ProdutoDigital produto) {
        catalogo.add(produto);
    }

    public void removerProduto(String nome){
        ProdutoDigital produto = buscarPorNome(nome);
        catalogo.remove(produto);
    }


    public ProdutoDigital buscarPorId(int id) throws ProdutoNaoEncontradoException {
        for (ProdutoDigital produtoPorID : catalogo) {
            if (produtoPorID.getId() == id) {
                return produtoPorID;
            }
        }
        throw new ProdutoNaoEncontradoException();

    }

    public ProdutoDigital buscarPorNome(String nome) throws ProdutoNaoEncontradoException {
        for (ProdutoDigital produto : catalogo) {

            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }

        }

        throw new ProdutoNaoEncontradoException();

    }

    public void vender(Usuario usuario, int id) throws SaldoInsuficienteException, IdadeInsuficienteException, JogoBaseNaoEncontradoException {
        ProdutoDigital produto = buscarPorId(id);
        usuario.comprar(produto);
    }

    public void mostrarCatalogo() {
        for (ProdutoDigital produto : catalogo) {
            System.out.println(produto);

        }
    }

    public List<ProdutoDigital> buscarPorGenero(Genero genero) {

        List<ProdutoDigital> produtosPorGenero = new ArrayList<>();

        for (ProdutoDigital produto : catalogo) {
            if (produto instanceof Jogo jogo) {
                if (jogo.getGenero() == genero) {
                    produtosPorGenero.add(produto);
                }

            }
        }
        return produtosPorGenero;

    }
}
