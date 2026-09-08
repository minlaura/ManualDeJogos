package manualdejogos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JogoTest {
    private Jogo jogo;

    @BeforeEach
    void prepararDados() {

        jogo = new Jogo("Dead By Daylight", 2.0, 9, 18, Genero.TERROR, true, 10);
    }

    @Test
    void deveAvaliarJogoComNotaValida() {
        jogo.avaliar(4);

        assertEquals(4, jogo.getnotaAvalicao());
    }

    @Test
    void eveFalharComNotaInvalida() {
        jogo.avaliar(4);

        assertFalse(jogo.avaliar(10));
    }
}