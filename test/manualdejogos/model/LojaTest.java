package manualdejogos.model;

import manualdejogos.DadosIniciais;
import manualdejogos.exception.ProdutoNaoEncontradoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LojaTest {
    private Loja loja;


    @BeforeEach
    void prepararDadosLoja() {
        loja = DadosIniciais.criarLoja();
    }

    @Test
    void deveBuscarProdutoPorId() {
        ProdutoDigital produtoPorId = loja.buscarPorId(1);

        assertEquals(1, produtoPorId.getId());
    }

    @Test
    void deveLancarExcecaoAoBuscarProdutoInexistente() {

        assertThrows(
                ProdutoNaoEncontradoException.class,
                () -> loja.buscarPorId(999)
        );

    }

    @Test
    void deveBuscarProdutoPorNome() {
        ProdutoDigital produtoPorNome = loja.buscarPorNome("Minecraft");
        assertEquals("Minecraft", produtoPorNome.getNome());
    }

    @Test
    void deveBuscarProdutosPorFaixaDePreco() {
        List<ProdutoDigital> produtosPorFaixa = loja.buscarProdutoPorFaixaDePreco(10, 80);
        assertEquals(3, produtosPorFaixa.size());

    }

    @Test
    void deveRemoverProduto() {
        loja.removerProduto("Minecraft");

        assertThrows(
                ProdutoNaoEncontradoException.class,
                () -> loja.buscarPorNome("Minecraft")
        );
    }
    @Test
    void deveBuscarJogosPorGenero() {
        List <ProdutoDigital> produtosPorGenero = loja.buscarPorGenero(Genero.AVENTURA);
        assertEquals(2, produtosPorGenero.size());

        ProdutoDigital produto = loja.buscarPorNome("Minecraft");
        assertTrue (produtosPorGenero.contains(produto));
    }



}




