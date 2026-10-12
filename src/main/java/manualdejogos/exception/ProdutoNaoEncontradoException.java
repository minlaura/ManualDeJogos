package manualdejogos.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("O produto não foi encontrado no catálogo.");

    }
}
