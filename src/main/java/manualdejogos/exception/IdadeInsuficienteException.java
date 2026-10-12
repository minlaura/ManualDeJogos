package manualdejogos.exception;

public class IdadeInsuficienteException extends Exception{


    public IdadeInsuficienteException() {
        super("Você não possui idade suficiente para adquirir este jogo.");
    }
}

