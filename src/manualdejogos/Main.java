package manualdejogos;

import manualdejogos.model.*;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        // USUÁRIOS
        Usuario usuarioComum = new Usuario("Minus", 19, 500.0);
        Usuario usuarioMenor = new Usuario("Pedro", 10, 500.0);
        Usuario usuarioSemSaldo = new Usuario("Paulo", 18, 0.0);
        Usuario usuarioNovo = new Usuario("Ana", 20, 500.0);

        // PRODUTOS
        Jogo minecraft = new Jogo(
                "Minecraft", 100.0, 1, 10, Genero.AVENTURA, true, 20
        );

        Jogo cyberpunk = new Jogo(
                "Cyberpunk 2077", 200.0, 2, 18, Genero.ACAO, false, 25
        );

        Jogo mario = new Jogo("Mario Party", 50.0, 5, 5, Genero.AVENTURA, true, 5);

        DLC liberty = new DLC(
                "Phantom Liberty", 70.0, 3, cyberpunk, 70.0
        );

        // BUSCAR POR ID - CLASSE LOJA
        Loja loja = new Loja("Petech", "000000.0");
        loja.adicionarProduto(minecraft);
        loja.adicionarProduto(cyberpunk);
        loja.adicionarProduto(liberty);
        loja.adicionarProduto(mario);


        System.out.println("- JOGOS DISPONÍVEIS: CATÁLOGO -");
        loja.mostrarCatalogo();



        System.out.println("\nBUSCA POR ID!\n");
        System.out.println(loja.buscarPorId(1));

        System.out.println("\nUSUÁRIO\n");
        System.out.println(usuarioComum);

        System.out.println("\nCOMPRA\n");

        try {
            loja.vender(usuarioComum, 2);
            System.out.println("Cyberpunk comprado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            loja.vender(usuarioComum, 3);
            System.out.println("DLC comprada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nDADOS DO USUÁRIO\n");
        System.out.println(usuarioComum);

        System.out.println("\nBIBLIOTECA\n");
        usuarioComum.mostrarBiblioteca();

        System.out.println("\nTESTE: IDADE\n");
        try {
            loja.vender(usuarioMenor, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTESTE: SALDO\n");
        try {
            loja.vender(usuarioSemSaldo, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTESTE: DLC SEM JOGO BASE\n");
        try {
            loja.vender(usuarioNovo, 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTESTE: ID NÃO ENCONTRADO\n");
        try {
            loja.vender(usuarioComum, 44);
            System.out.println("Compra realizada!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPRODUTOS DE AVENTURA\n");

        List<ProdutoDigital> produtos = loja.buscarPorGenero(Genero.AVENTURA);

        for (ProdutoDigital produto : produtos) {
            System.out.println(produto);
        }


        // NOME DE PRODUTO
        System.out.println("\nBUSCA POR NOME");

        //PRODUTO NÃO ENCONTRADO

        try {
            System.out.println(loja.buscarPorNome("GTA VI"));
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }


        // REMOVER PRODUTO DO CATALOGO
        System.out.println("\nREMOÇÃO DE PRODUTOS");
        try {
            loja.removerProduto("Mario Party");
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCATÁLOGO ATUALIZADO");
        loja.mostrarCatalogo();

    }
}