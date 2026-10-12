package manualdejogos.exception;

public class JogoBaseNaoEncontradoException extends Exception {

    public JogoBaseNaoEncontradoException() {
        super("Você precisa possuir o jogo base para comprar esta dlc.");
    }
}