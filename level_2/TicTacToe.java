import java.util.Scanner;

public class TicTacToe {

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
        char currentPlayer = 'X';
        boolean gameWon = false;

        while (true) {
            printBoard(board);

            int row, col;
            while (true) {
                System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            }

            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");
                gameWon = true;
                break;
            }
            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("The game is a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next();
        if (playAgain.equalsIgnoreCase("yes")) {
            main(null);  
        } else {
            System.out.println("Thanks for playing!");
        }
        scanner.close();
    }
}
