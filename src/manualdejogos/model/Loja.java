package manualdejogos.model;

import manualdejogos.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loja {

    private String nome;
    private String cnpj;

    private Map<Integer, ProdutoDigital> catalogo = new HashMap<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public Loja(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarProduto(ProdutoDigital produto) {
        catalogo.put(produto.getId(), produto);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerProduto(String nome)
            throws ProdutoNaoEncontradoException {

        ProdutoDigital produto = buscarPorNome(nome);
        catalogo.remove(produto.getId());
    }

    public ProdutoDigital buscarPorId(int id)
            throws ProdutoNaoEncontradoException {

        ProdutoDigital produto = catalogo.get(id);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }

        return produto;
    }

    public ProdutoDigital buscarPorNome(String nome)
            throws ProdutoNaoEncontradoException {

        for (ProdutoDigital produto : catalogo.values()) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }

        throw new ProdutoNaoEncontradoException();
    }

    public void vender(Usuario usuario, int id)
            throws SaldoInsuficienteException,
            IdadeInsuficienteException,
            JogoBaseNaoEncontradoException,
            ProdutoNaoEncontradoException {

        ProdutoDigital produto = buscarPorId(id);
        usuario.comprar(produto);
    }

    public void mostrarCatalogo() {
        for (ProdutoDigital produto : catalogo.values()) {
            System.out.println(produto);
        }
    }

    public List<ProdutoDigital> buscarPorGenero(Genero genero) {
        List<ProdutoDigital> produtosPorGenero = new ArrayList<>();

        for (ProdutoDigital produto : catalogo.values()) {

            if (produto instanceof Jogo jogo) {
                if (jogo.getGenero() == genero) {
                    produtosPorGenero.add(jogo);
                }
            }

            if (produto instanceof DLC dlc) {
                if (dlc.getJogoBase().getGenero() == genero) {
                    produtosPorGenero.add(dlc);
                }
            }
        }

        return produtosPorGenero;
    }

    public List<Jogo> buscarJogosMaiorIdade() {
        List<Jogo> jogosMaiorIdade = new ArrayList<>();
        int maiorIdade = -1;

        for (ProdutoDigital produto : catalogo.values()) {
            if (produto instanceof Jogo jogo) {
                if (jogo.getIdadeRecomendada() > maiorIdade) {
                    maiorIdade = jogo.getIdadeRecomendada();
                    jogosMaiorIdade.clear();
                    jogosMaiorIdade.add(jogo);

                } else if (jogo.getIdadeRecomendada() == maiorIdade) {
                    jogosMaiorIdade.add(jogo);
                }
            }
        }

        return jogosMaiorIdade;
    }

    public List<Jogo> buscarJogosBaratos(
            double menorLimite,
            double maiorLimite) {

        List<Jogo> jogosBaratos = new ArrayList<>();

        for (ProdutoDigital produto : catalogo.values()) {
            if (produto instanceof Jogo jogo) {

                double precoFinal = jogo.calcularPrecoFinal();

                if (precoFinal > menorLimite &&
                        precoFinal <= maiorLimite) {

                    jogosBaratos.add(jogo);
                }
            }
        }

        return jogosBaratos;
    }



    public List<Jogo> buscarJogosMaisBaratos() {
        List<Jogo> jogosMaisBaratos = new ArrayList<>();
        double menorPreco = Double.MAX_VALUE;

        for (ProdutoDigital produto : catalogo.values()) {
            if (produto instanceof Jogo jogo) {

                double precoFinal = jogo.calcularPrecoFinal();

                if (precoFinal < menorPreco) {
                    menorPreco = precoFinal;
                    jogosMaisBaratos.clear();
                    jogosMaisBaratos.add(jogo);

                } else if (precoFinal == menorPreco) {
                    jogosMaisBaratos.add(jogo);
                }
            }
        }

        return jogosMaisBaratos;
    }

    public List<ProdutoDigital> buscarProdutoPorFaixaDePreco(
            double precoMinimo,
            double precoMaximo) {

        List<ProdutoDigital> produtoPorFaixa = new ArrayList<>();

        for (ProdutoDigital produto : catalogo.values()) {

            double precoFinal = produto.calcularPrecoFinal();

            if (precoFinal >= precoMinimo && precoFinal <= precoMaximo) {
                produtoPorFaixa.add(produto);
            }
        }

        return produtoPorFaixa;
    }

    public void mostrarCatalogoComId() {
        for (Map.Entry<Integer, ProdutoDigital> entrada : catalogo.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue().getNome());

        }

    }
}