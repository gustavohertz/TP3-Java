package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class JogoDaVelha {
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoDaVelha.class);

    public static void main(String[] args) {
        LOGGER.info("Iniciando o jogo...");

        char[][] tabuleiro = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';

        while (jogoEmAndamento) {
            exibirTabuleiro(tabuleiro);

            fazerJogada(tabuleiro, jogadorAtual);

            if (verificarVencedor(tabuleiro, jogadorAtual)) {
                exibirTabuleiro(tabuleiro);
                LOGGER.info("Jogador {} venceu!", jogadorAtual);
                jogoEmAndamento = false;
            } else if (verificarEmpate(tabuleiro)) {
                exibirTabuleiro(tabuleiro);
                LOGGER.info("O jogo empatou!");
                jogoEmAndamento = false;
            }

            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        LOGGER.info("Encerrando o jogo...");
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    public static void fazerJogada(char[][] tabuleiro, char jogador) {
        Scanner scanner = new Scanner(System.in);
        int linha, coluna;

        while (true) {
            System.out.print("Jogador " + jogador + ", digite a linha (0, 1 ou 2) e a coluna (0, 1 ou 2) da sua jogada, separadas por um espaço: ");
            linha = scanner.nextInt();
            coluna = scanner.nextInt();

            if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
                tabuleiro[linha][coluna] = jogador;
                break;
            } else {
                LOGGER.error("Jogada inválida. Tente novamente.");
            }
        }
    }

    public static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
        for (int linha = 0; linha < 3; linha++) {
            if (tabuleiro[linha][0] == jogador && tabuleiro[linha][1] == jogador && tabuleiro[linha][2] == jogador) {
                return true;
            }
        }

        for (int coluna = 0; coluna < 3; coluna++) {
            if (tabuleiro[0][coluna] == jogador && tabuleiro[1][coluna] == jogador && tabuleiro[2][coluna] == jogador) {
                return true;
            }
        }

        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }

        return tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador;
    }

    public static boolean verificarEmpate(char[][] tabuleiro) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[linha][coluna] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
