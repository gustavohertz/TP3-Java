import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {
        char[][] tabuleiro = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';

        while (jogoEmAndamento) {
            // Exibir o tabuleiro
            exibirTabuleiro(tabuleiro);

            // Obter a entrada do jogador
            fazerJogada(tabuleiro, jogadorAtual);

            // Verificar se há um vencedor ou empate
            if (verificarVencedor(tabuleiro, jogadorAtual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Jogador " + jogadorAtual + " venceu!");
                jogoEmAndamento = false;
            } else if (verificarEmpate(tabuleiro)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("O jogo empatou!");
                jogoEmAndamento = false;
            }

            // Alternar o jogador
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }
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
                // A jogada é válida, atualize o tabuleiro
                tabuleiro[linha][coluna] = jogador;
                break;
            } else {
                System.out.println("Jogada inválida. Tente novamente.");
            }
        }
    }


    public static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
            // Verifique as linhas
            for (int linha = 0; linha < 3; linha++) {
                if (tabuleiro[linha][0] == jogador && tabuleiro[linha][1] == jogador && tabuleiro[linha][2] == jogador) {
                    return true; // O jogador venceu na linha
                }
            }

            // Verifique as colunas
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[0][coluna] == jogador && tabuleiro[1][coluna] == jogador && tabuleiro[2][coluna] == jogador) {
                    return true; // O jogador venceu na coluna
                }
            }

            // Verifique as diagonais
            if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
                return true; // O jogador venceu na diagonal principal
            }

            if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
                return true;
            }

            return false;
        }

        public static boolean verificarEmpate(char[][] tabuleiro) {
            for (int linha = 0; linha < 3; linha++) {
                for (int coluna = 0; coluna < 3; coluna++) {
                    // Se encontrar qualquer posição vazia, o jogo ainda não empatou
                    if (tabuleiro[linha][coluna] == ' ') {
                        return false;
                    }
                }
            }
            // Se chegou até aqui, o jogo empatou
            return true;
        }
    }
