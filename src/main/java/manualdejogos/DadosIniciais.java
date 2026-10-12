package manualdejogos;

import manualdejogos.model.DLC;
import manualdejogos.model.Genero;
import manualdejogos.model.Jogo;
import manualdejogos.model.Loja;

public class DadosIniciais {

    public static Loja criarLoja() {

        Jogo minecraft = new Jogo(
                "Minecraft", 100.0, 1, 10,
                Genero.AVENTURA, true, 20
        );

        Jogo cyberpunk = new Jogo(
                "Cyberpunk 2077", 200.0, 2, 18,
                Genero.ACAO, false, 25
        );

        Jogo mario = new Jogo(
                "Mario Party", 50.0, 5, 5,
                Genero.AVENTURA, true, 5
        );

        DLC liberty = new DLC(
                "Phantom Liberty", 70.0, 3,
                cyberpunk, 70.0
        );

        Jogo deadByDaylight = new Jogo(
                "Dead By Daylight", 2.0, 9, 18,
                Genero.TERROR, true, 10
        );

        Loja loja = new Loja("Petech", "000000.0");

        loja.adicionarProduto(minecraft);
        loja.adicionarProduto(cyberpunk);
        loja.adicionarProduto(liberty);
        loja.adicionarProduto(mario);
        loja.adicionarProduto(deadByDaylight);

        return loja;
    }
}