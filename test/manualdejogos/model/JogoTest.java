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
    void deveAvaliarJogoUsandoInterface() {
        Avaliavel avaliavel = jogo;

        assertTrue(avaliavel.avaliar(4));
    }

    @Test
    void eveFalharComNotaInvalida() {

        assertFalse(jogo.avaliar(10));
    }
}