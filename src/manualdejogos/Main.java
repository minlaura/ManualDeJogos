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

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        // Usuários usados nos testes
        Usuario usuarioComum = new Usuario("Minus", 19, 500.0);
        Usuario usuarioMenor = new Usuario("Pedro", 10, 500.0);
        Usuario usuarioSemSaldo = new Usuario("Paulo", 18, 0.0);
        Usuario usuarioNovo = new Usuario("Ana", 20, 500.0);

        // Loja e produtos criados pela classe DadosIniciais
        Loja loja = DadosIniciais.criarLoja();

        // Cadastro dos usuários na loja
        loja.adicionarUsuario(usuarioComum);
        loja.adicionarUsuario(usuarioMenor);
        loja.adicionarUsuario(usuarioSemSaldo);
        loja.adicionarUsuario(usuarioNovo);

        titulo("Catálogo de Jogos");
        loja.mostrarCatalogo();

        titulo("Busca por ID");

        try {
            System.out.println(loja.buscarPorId(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Dados do Usuário");
        System.out.println(usuarioComum);

        titulo("Compra de Jogo");

        try {
            loja.vender(usuarioComum, 2);
            System.out.println("Cyberpunk comprado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Compra de DLC");

        try {
            loja.vender(usuarioComum, 3);
            System.out.println("DLC comprada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Dados Atualizados do Usuário");
        System.out.println(usuarioComum);

        titulo("Biblioteca do Usuário");
        usuarioComum.mostrarBiblioteca();

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

        titulo("Produtos do Gênero Aventura");

        List<ProdutoDigital> produtosAventura =
                loja.buscarPorGenero(Genero.AVENTURA);

        for (ProdutoDigital produto : produtosAventura) {
            System.out.println(produto);
        }

        titulo("Busca por Nome");

        try {
            System.out.println(loja.buscarPorNome("GTA VI"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Jogos com Maior Idade Recomendada");

        List<Jogo> jogosMaiorIdade =
                loja.buscarJogosMaiorIdade();

        for (Jogo jogo : jogosMaiorIdade) {
            System.out.println(jogo);
        }

        titulo("Remoção de Produto");

        try {
            loja.removerProduto("Mario Party");
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        titulo("Jogos entre R$ 0,00 e R$ 5,00");

        List<Jogo> jogosAteCincoReais =
                loja.buscarJogosBaratos();

        for (Jogo jogo : jogosAteCincoReais) {
            System.out.println(jogo);
        }

        titulo("Jogos com o Menor Preço Disponível");

        List<Jogo> jogosMenorPreco =
                loja.buscarJogosMaisBaratos();

        for (Jogo jogo : jogosMenorPreco) {
            System.out.println(jogo);
        }

        titulo("Catálogo Atualizado");
        loja.mostrarCatalogo();
    }
}