package manualdejogos.model;

import manualdejogos.DadosIniciais;
import manualdejogos.exception.IdadeInsuficienteException;
import manualdejogos.exception.JogoBaseNaoEncontradoException;
import manualdejogos.exception.ProdutoNaoEncontradoException;
import manualdejogos.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;
    private Loja loja;

    @BeforeEach
    void prepararDados() {
        usuario = new Usuario("Teste", 20, 500.0);
        loja = DadosIniciais.criarLoja();

    }

    @Test
    void deveComprarProduto()
            throws SaldoInsuficienteException,
            IdadeInsuficienteException,
            JogoBaseNaoEncontradoException {

        ProdutoDigital produtoDigital = loja.buscarPorNome("Minecraft");

        usuario.comprar(produtoDigital);

        assertTrue(usuario.possuiProduto(produtoDigital));
        assertEquals(420.0, usuario.getSaldo());
    }

    @Test
    void deveFalharCompraPorSaldo() {
        Usuario usuarioSemSaldo = new Usuario("TesteSaldo", 20, 0.0);
        ProdutoDigital produtoDigital = loja.buscarPorNome("Minecraft");
        assertThrows(
                SaldoInsuficienteException.class,
                () -> usuarioSemSaldo.comprar(produtoDigital)
        );
    }


    @Test
    void deveFalharCompraDeDlcSemJogoBase() {
        Usuario usuarioSemProdutoBase = new Usuario("Teste", 20, 500.0);

        ProdutoDigital produto = loja.buscarPorNome("Phantom Liberty");

        assertThrows(
                JogoBaseNaoEncontradoException.class,
                () -> usuarioSemProdutoBase.comprar(produto)
        );
    }

    @Test
    void deveComprarDlcQuandoPossuirJogoBase()
            throws SaldoInsuficienteException,
            IdadeInsuficienteException,
            JogoBaseNaoEncontradoException {


        ProdutoDigital produtoJogo = loja.buscarPorNome("Cyberpunk 2077");
        ProdutoDigital produtoDlc = loja.buscarPorNome("Phantom Liberty");

        usuario.comprar(produtoJogo);
        usuario.comprar(produtoDlc);

        assertTrue(usuario.possuiProduto(produtoDlc));
    }

    @Test
    void deveAdicionarSaldo() {
        usuario.adicionarSaldo(100.0);
        assertEquals(600.0, usuario.getSaldo());
    }

    @Test
    void deveFavoritarProduto() {
        ProdutoDigital produto = loja.buscarPorNome("Minecraft");
        usuario.favoritarProduto(produto);
        assertTrue(usuario.estaFavoritado(produto));
    }

    @Test
    void deveDesfavoritarProduto() {
        ProdutoDigital produto = loja.buscarPorNome("Minecraft");

        usuario.favoritarProduto(produto);
        usuario.desfavoritarProduto(produto);

        assertFalse(usuario.estaFavoritado(produto));
    }

    @Test
    void deveVerificarSeUsuarioPossuiProduto() throws SaldoInsuficienteException, IdadeInsuficienteException, JogoBaseNaoEncontradoException {
        ProdutoDigital produto = loja.buscarPorNome("Minecraft");
        assertFalse(usuario.possuiProduto(produto));
        usuario.comprar(produto);
        assertTrue(usuario.possuiProduto(produto));

    }

    @Test
    void deveFalharCompraPorIdade() {
        Usuario usuarioIdade = new Usuario("TesteIdade", 8, 100.0);
        ProdutoDigital produto = loja.buscarPorNome("Minecraft");
        assertThrows(
                IdadeInsuficienteException.class,
                () -> usuarioIdade.comprar(produto)
        );
        assertAll(
                () -> assertFalse(usuarioIdade.possuiProduto(produto)),
                () -> assertEquals(100.0, usuarioIdade.getSaldo())
        );

    }

    @Test
    void devePermitirCompraComIdadeIgualARecomendada() throws JogoBaseNaoEncontradoException, IdadeInsuficienteException, SaldoInsuficienteException {
        Usuario usuarioIdade = new Usuario("TesteIdade", 10, 100.0);
        ProdutoDigital produto = loja.buscarPorNome("Minecraft");
        usuarioIdade.comprar(produto);
        assertAll(
                () -> assertTrue(usuarioIdade.possuiProduto(produto)),
                () -> assertEquals(20.0, usuarioIdade.getSaldo())
        );

    }

    @Test
    void deveCalcularTotalDoCarrinho() {

        Usuario usuario1 = new Usuario("User Teste", 18, 500);
        ProdutoDigital jogo1 = loja.buscarPorNome("Minecraft");
        ProdutoDigital jogo2 = loja.buscarPorNome("Cyberpunk 2077");
        usuario1.adicionarProdutoAoCarrinho(jogo1);
        usuario1.adicionarProdutoAoCarrinho(jogo2);

        double total = usuario1.calcularTotalDoCarrinho();

        assertEquals(230.0, total);
    }

    @Test
    void deveFinalizarCompraDoCarrinho() throws SaldoInsuficienteException, IdadeInsuficienteException, JogoBaseNaoEncontradoException {
        Usuario usuario1 = new Usuario("User Teste", 18, 500);
        ProdutoDigital jogo1 = loja.buscarPorNome("Minecraft");
        ProdutoDigital jogo2 = loja.buscarPorNome("Cyberpunk 2077");
        usuario1.adicionarProdutoAoCarrinho(jogo1);
        usuario1.adicionarProdutoAoCarrinho(jogo2);
        usuario1.finalizarCompra();

        assertEquals(270.0, usuario1.getSaldo());
        assertTrue(usuario1.possuiProduto(jogo1));
        assertTrue(usuario1.possuiProduto(jogo2));


    }

    @Test
    void deveFalharAoFinalizarCarrinhoPorSaldoInsuficiente() {
        Usuario usuario1 = new Usuario("User Sem Saldo", 18, 100);
        ProdutoDigital jogo1 = loja.buscarPorNome("Minecraft");
        ProdutoDigital jogo2 = loja.buscarPorNome("Cyberpunk 2077");
        usuario1.adicionarProdutoAoCarrinho(jogo1);
        usuario1.adicionarProdutoAoCarrinho(jogo2);

        assertThrows(SaldoInsuficienteException.class,
                () -> usuario1.finalizarCompra()
        );
        assertEquals(100.0, usuario1.getSaldo());
        assertFalse(usuario1.possuiProduto(jogo1));
        assertFalse(usuario1.possuiProduto(jogo2));

    }


}

