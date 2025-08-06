import java.util.Scanner;
public class TicTacToeBoard {
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("玩家 " + currentPlayer + " 請輸入行 (0-2)：");
            int row = sc.nextInt();
            System.out.println("玩家 " + currentPlayer + " 請輸入列 (0-2)：");
            int col = sc.nextInt();

            if (!placeMark(row, col)) {
                System.out.println("該位置已被佔用或無效，請重新選擇！");
                continue;
            }

            if (checkWin()) {
                printBoard();
                System.out.println("玩家 " + currentPlayer + " 獲勝！");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("平手！");
                break;
            }

            switchPlayer();
        }

        sc.close();
    }

    // 初始化棋盤
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 列印棋盤
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // 放置棋子
    public static boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    // 切換玩家
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // 檢查是否獲勝
    public static boolean checkWin() {
        return (checkRows() || checkCols() || checkDiagonals());
    }

    // 檢查行是否獲勝
    public static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // 檢查列是否獲勝
    public static boolean checkCols() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // 檢查對角線是否獲勝
    public static boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer &&
                 board[1][1] == currentPlayer &&
                 board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer &&
                 board[1][1] == currentPlayer &&
                 board[2][0] == currentPlayer));
    }

    // 檢查棋盤是否已滿（平手）
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
