package manualdejogos;

import manualdejogos.model.Genero;
import manualdejogos.model.Jogo;
import manualdejogos.model.Loja;
import manualdejogos.model.ProdutoDigital;
import manualdejogos.model.Usuario;

import java.util.List;
import java.util.Locale;

public class Main {

    private static void titulo(String texto) {
        System.out.println("\n -- " + texto);
    }


    private static void testarCatalogo(Loja loja) {

        titulo("Catálogo de Jogos");
        loja.mostrarCatalogo();
    }


    private static void testarBuscaPorId(Loja loja) {

        titulo("Busca por ID");

        try {
            System.out.println(loja.buscarPorId(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarDadosUsuario(Usuario usuario) {

        titulo("Dados do Usuário");
        System.out.println(usuario);
    }


    private static void testarCompraJogo(Loja loja, Usuario usuario) {

        titulo("Compra de Jogo");

        try {
            loja.vender(usuario, 2);
            System.out.println("Cyberpunk comprado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarCompraDlc(Loja loja, Usuario usuario) {

        titulo("Compra de DLC");

        try {
            loja.vender(usuario, 3);
            System.out.println("DLC comprada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarBiblioteca(Usuario usuario) {

        titulo("Biblioteca do Usuário");
        usuario.mostrarBiblioteca();
    }


    private static void testarIdadeInsuficiente(
            Loja loja,
            Usuario usuarioMenor) {

        titulo("Teste - Idade Insuficiente");

        try {
            loja.vender(usuarioMenor, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarSaldoInsuficiente(
            Loja loja,
            Usuario usuarioSemSaldo) {

        titulo("Teste - Saldo Insuficiente");

        try {
            loja.vender(usuarioSemSaldo, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarDlcSemJogoBase(
            Loja loja,
            Usuario usuario) {

        titulo("Teste - DLC sem Jogo Base");

        try {
            loja.vender(usuario, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarProdutoNaoEncontrado(
            Loja loja,
            Usuario usuario) {

        titulo("Teste - Produto Não Encontrado");

        try {
            loja.vender(usuario, 444);
            System.out.println("Compra realizada!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarBuscaPorGenero(Loja loja) {

        titulo("Produtos do Gênero Aventura");

        List<ProdutoDigital> produtosAventura =
                loja.buscarPorGenero(Genero.AVENTURA);

        for (ProdutoDigital produto : produtosAventura) {
            System.out.println(produto);
        }
    }


    private static void testarBuscaPorNome(Loja loja) {

        titulo("Busca por Nome");

        try {
            System.out.println(loja.buscarPorNome("GTA VI"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarJogosMaiorIdade(Loja loja) {

        titulo("Jogos com Maior Idade Recomendada");

        List<Jogo> jogosMaiorIdade =
                loja.buscarJogosMaiorIdade();

        for (Jogo jogo : jogosMaiorIdade) {
            System.out.println(jogo);
        }
    }


    private static void testarRemocaoProduto(Loja loja) {

        titulo("Remoção de Produto");

        try {
            loja.removerProduto("Mario Party");
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarJogosBaratos(Loja loja) {

        titulo("Jogos entre R$ 0,00 e R$ 5,00");

        List<Jogo> jogosAteCincoReais =
                loja.buscarJogosBaratos();

        for (Jogo jogo : jogosAteCincoReais) {
            System.out.println(jogo);
        }
    }


    private static void testarJogosMaisBaratos(Loja loja) {

        titulo("Jogos com o Menor Preço Disponível");

        List<Jogo> jogosMenorPreco =
                loja.buscarJogosMaisBaratos();

        for (Jogo jogo : jogosMenorPreco) {
            System.out.println(jogo);
        }
    }


    private static void testarFavoritos(
            Loja loja,
            Usuario usuario) {

        titulo("Favoritar Produto");

        try {
            ProdutoDigital produto =
                    loja.buscarPorNome("Minecraft");

            usuario.favoritarProduto(produto);

            System.out.println("Produto favoritado com sucesso!");

            titulo("Produtos Favoritados");
            usuario.mostrarProdutosFavoritos();

            titulo("Desfavoritar Produto");

            usuario.desfavoritarProduto(produto);

            System.out.println("Produto removido dos favoritos!");

            titulo("Produtos Favoritados após remover");
            usuario.mostrarProdutosFavoritos();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testarFaixaDePreco(Loja loja) {

        titulo("Buscar produtos por faixa de preço");

        List<ProdutoDigital> produtosPorFaixaDePreco =
                loja.buscarProdutoPorFaixaDePreco(10, 90);

        for (ProdutoDigital produto : produtosPorFaixaDePreco) {
            System.out.println(produto);
        }
    }


    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Usuario usuarioComum =
                new Usuario("Minus", 19, 500.0);

        Usuario usuarioMenor =
                new Usuario("Pedro", 10, 500.0);

        Usuario usuarioSemSaldo =
                new Usuario("Paulo", 18, 0.0);

        Usuario usuarioNovo =
                new Usuario("Ana", 20, 500.0);


        Loja loja = DadosIniciais.criarLoja();


        loja.adicionarUsuario(usuarioComum);
        loja.adicionarUsuario(usuarioMenor);
        loja.adicionarUsuario(usuarioSemSaldo);
        loja.adicionarUsuario(usuarioNovo);


        testarCatalogo(loja);

        testarBuscaPorId(loja);

        testarDadosUsuario(usuarioComum);

        testarCompraJogo(loja, usuarioComum);

        testarCompraDlc(loja, usuarioComum);

        titulo("Dados Atualizados do Usuário");
        System.out.println(usuarioComum);

        testarBiblioteca(usuarioComum);

        testarIdadeInsuficiente(loja, usuarioMenor);

        testarSaldoInsuficiente(loja, usuarioSemSaldo);

        testarDlcSemJogoBase(loja, usuarioNovo);

        testarProdutoNaoEncontrado(loja, usuarioComum);

        testarBuscaPorGenero(loja);

        testarBuscaPorNome(loja);

        testarJogosMaiorIdade(loja);

        testarRemocaoProduto(loja);

        testarJogosBaratos(loja);

        testarJogosMaisBaratos(loja);

        titulo("Catálogo Atualizado");
        loja.mostrarCatalogo();

        testarFavoritos(loja, usuarioComum);

        testarFaixaDePreco(loja);
    }
}