package manualdejogos;

import manualdejogos.model.Genero;
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

        Loja loja = DadosIniciais.criarLoja();

        Usuario usuario =
                new Usuario("Minus", 19, 500.0);

        loja.adicionarUsuario(usuario);


        titulo("CATÁLOGO");
        loja.mostrarCatalogoComId();


        titulo("DADOS DO USUÁRIO");
        System.out.println(usuario);


        titulo("COMPRA DE JOGO");

        try {
            loja.vender(usuario, 2);
            System.out.println("Jogo comprado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        titulo("COMPRA DE DLC");

        try {
            loja.vender(usuario, 3);
            System.out.println("DLC comprada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        titulo("BIBLIOTECA");
        usuario.mostrarBiblioteca();


        titulo("BUSCA POR GÊNERO");

        List<ProdutoDigital> produtosAcao =
                loja.buscarPorGenero(Genero.ACAO);

        for (ProdutoDigital produto : produtosAcao) {
            System.out.println(produto);
        }


        titulo("FAVORITOS");

        try {
            ProdutoDigital minecraft =
                    loja.buscarPorNome("Minecraft");

            usuario.favoritarProduto(minecraft);

            usuario.mostrarProdutosFavoritos();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        titulo("PRODUTOS ENTRE R$ 10 E R$ 90");

        List<ProdutoDigital> produtosPorPreco =
                loja.buscarProdutoPorFaixaDePreco(10, 90);

        for (ProdutoDigital produto : produtosPorPreco) {
            System.out.println(produto);
        }


        titulo("DADOS ATUALIZADOS DO USUÁRIO");
        System.out.println(usuario);
    }
}