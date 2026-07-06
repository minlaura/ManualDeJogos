package manualdejogos;

import manualdejogos.model.Usuario;
import manualdejogos.model.Jogo;
import manualdejogos.model.DLC;
import manualdejogos.model.Genero;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Usuario usuarioComum = new Usuario("Minus", 19, 500.0);
        Usuario usuarioMenor = new Usuario("Pedro", 10, 500.0);
        Usuario usuarioSemSaldo = new Usuario("Paulo", 18, 0.0);
        Usuario usuarioNovo = new Usuario("Ana", 20, 500.0);

        Jogo minecraft = new Jogo(
                "Minecraft", 100.0, 1, 10, Genero.AVENTURA, true, 20
        );

        Jogo cyberpunk = new Jogo(
                "Cyberpunk 2077", 200.0, 2, 18, Genero.ACAO, false, 25
        );

        DLC liberty = new DLC(
                "Phantom Liberty", 70.0, 3, cyberpunk, 70.0, 20
        );

        System.out.println("PRODUTOS \n");
        System.out.println(minecraft);
        System.out.println();
        System.out.println(cyberpunk);
        System.out.println();
        System.out.println(liberty);

        System.out.println("\nUSUÁRIO \n");
        System.out.println(usuarioComum);

        System.out.println("\nCOMPRA \n");
        try {
            usuarioComum.comprar(cyberpunk);
            System.out.println("Cyberpunk comprado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            usuarioComum.comprar(liberty);
            System.out.println("DLC comprada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nDADOS DO USUÁRIO \n");
        System.out.println(usuarioComum);

        System.out.println("\nBIBLIOTECA \n");
        usuarioComum.mostrarBiblioteca();

        System.out.println("\nTESTE: IDADE \n");
        try {
            usuarioMenor.comprar(cyberpunk);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTESTE: SALDO\n");
        try {
            usuarioSemSaldo.comprar(minecraft);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTESTE: DLC SEM JOGO BASE\n");
        try {
            usuarioNovo.comprar(liberty);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}