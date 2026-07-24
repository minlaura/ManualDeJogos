package manualdejogos;

import manualdejogos.model.*;

import java.util.List;
import java.util.Locale;

public class Main {

    private static void titulo(String texto) {
        System.out.println("\n• " + texto);
    }

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        // USUÁRIOS
        Usuario usuarioComum = new Usuario("Minus", 19, 500.0);
        Usuario usuarioMenor = new Usuario("Pedro", 10, 500.0);
        Usuario usuarioSemSaldo = new Usuario("Paulo", 18, 0.0);
        Usuario usuarioNovo = new Usuario("Ana", 20, 500.0);

        // PRODUTOS
        Jogo minecraft = new Jogo(
                "Minecraft", 100.0, 1, 10,
                Genero.AVENTURA, true, 20
        );

        Jogo cyberpunk = new Jogo(
                "Cyberpunk 2077", 200.0, 2, 18,
                Genero.ACAO, false, 25
        );

        Jogo mario = new Jogo(
                "Mario Party", 50.0, 5, 5,
                Genero.AVENTURA, true, 5
        );

        DLC liberty = new DLC(
                "Phantom Liberty", 70.0, 3,
                cyberpunk, 70.0
        );

        // LOJA
        Loja loja = new Loja("Petech", "000000.0");

        loja.adicionarProduto(minecraft);
        loja.adicionarProduto(cyberpunk);
        loja.adicionarProduto(liberty);
        loja.adicionarProduto(mario);

        // CATÁLOGO
        titulo("Catálogo de Jogos");
        loja.mostrarCatalogo();

        // BUSCA POR ID
        titulo("Busca por ID");

        try {
            System.out.println(loja.buscarPorId(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // DADOS DO USUÁRIO
        titulo("Dados do Usuário");
        System.out.println(usuarioComum);

        // COMPRA DE JOGO
        titulo("Compra de Jogo");

        try {
            loja.vender(usuarioComum, 2);
            System.out.println("Cyberpunk comprado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // COMPRA DE DLC
        titulo("Compra de DLC");

        try {
            loja.vender(usuarioComum, 3);
            System.out.println("DLC comprada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // DADOS APÓS AS COMPRAS
        titulo("Dados Atualizados do Usuário");
        System.out.println(usuarioComum);

        // BIBLIOTECA
        titulo("Biblioteca do Usuário");
        usuarioComum.mostrarBiblioteca();

        // TESTES
        titulo("Teste - Idade Insuficiente");

        try {
            loja.vender(usuarioMenor, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Teste - Saldo Insuficiente");

        try {
            loja.vender(usuarioSemSaldo, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Teste - DLC sem Jogo Base");

        try {
            loja.vender(usuarioNovo, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Teste - Produto Não Encontrado");

        try {
            loja.vender(usuarioComum, 444);
            System.out.println("Compra realizada!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // BUSCA POR GÊNERO
        titulo("Produtos do Gênero Aventura");

        List<ProdutoDigital> produtosAventura = loja.buscarPorGenero(Genero.AVENTURA);

        for (ProdutoDigital produto : produtosAventura) {
            System.out.println(produto);
        }

        // BUSCA POR NOME
        titulo("Busca por Nome");

        try {
            System.out.println(loja.buscarPorNome("GTA VI"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // MAIOR IDADE
        titulo("Jogos com Maior Idade Recomendada");

        List<Jogo> jogosMaiorIdade = loja.buscarJogosMaiorIdade();

        for (Jogo jogo : jogosMaiorIdade) {
            System.out.println(jogo);
        }

        // REMOÇÃO
        titulo("Remoção de Produto");

        try {
            loja.removerProduto("Mario Party");
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // CATÁLOGO FINAL
        titulo("Catálogo Atualizado");
        loja.mostrarCatalogo();
    }
}