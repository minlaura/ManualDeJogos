package manualdejogos.model;
import manualdejogos.DadosIniciais;
import manualdejogos.exception.IdadeInsuficienteException;
import manualdejogos.exception.JogoBaseNaoEncontradoException;
import manualdejogos.exception.ProdutoNaoEncontradoException;
import manualdejogos.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LojaTest {
    private Loja loja;
    private Usuario usuario;

    @BeforeEach
    void prepararDadosLoja(){
        loja = DadosIniciais.criarLoja();
        usuario = new Usuario("Teste", 20, 500.0);
    }

    @Test
    void deveBuscarProdutoPorId() {
        ProdutoDigital produtoPorId = loja.buscarPorId(1);

        assertEquals(1, produtoPorId.getId());
    }
    @Test
    void deveLancarExcecaoAoBuscarProdutoInexistente () {

        assertThrows (
                ProdutoNaoEncontradoException.class,
                () -> loja.buscarPorId(999)
        );

    }
    @Test
    void deveBuscarProdutoPorNome(){
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
    void deveComprarProduto ()
    throws SaldoInsuficienteException, IdadeInsuficienteException, JogoBaseNaoEncontradoException {
        ProdutoDigital produtoDigital = loja.buscarPorNome("Minecraft");
        usuario.comprar(produtoDigital);
        assertTrue (usuario.possuiProduto(produtoDigital));
        assertEquals(420.0, usuario.getSaldo());

    }
    @Test
    void deveFalharCompraPorSaldo (){
        Usuario usuarioSemSaldo = new Usuario("TesteSaldo", 20, 0.0);
        ProdutoDigital produtoDigital = loja.buscarPorNome("Minecraft");
        assertThrows(
            SaldoInsuficienteException.class,
                () -> usuarioSemSaldo.comprar(produtoDigital)
        );
        }


     @Test
    void deveFalharCompraPorIdade (){
        Usuario usuarioIdade = new Usuario("TesteIdade", 8, 100.0 );
        ProdutoDigital produtoDigital = loja.buscarPorNome("Minecraft");
        assertThrows(
                IdadeInsuficienteException.class,
                () -> usuarioIdade.comprar(produtoDigital)
        );
        assertFalse(usuarioIdade.possuiProduto(produtoDigital));
        assertEquals(100.0, usuarioIdade.getSaldo());

    }
    }




