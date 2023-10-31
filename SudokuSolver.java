public class SudokuSolver {
    public static boolean solveSudoku(int[][] board) {
        int N = board.length;

        // Find an empty cell
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            return true; // No empty cell, puzzle is solved
        }

        for (int num = 1; num <= N; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true; // Recursively solve the rest of the puzzle
                }
                board[row][col] = 0; // If no solution is found, backtrack
            }
        }
        return false; // No solution exists
    }

    public static boolean isSafe(int[][] board, int row, int col, int num) {
        int N = board.length;

        // Check the row
        for (int x = 0; x < N; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check the column
        for (int x = 0; x < N; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int sqrtN = (int) Math.sqrt(N);
        int subgridRowStart = row - row % sqrtN;
        int subgridColStart = col - col % sqrtN;
        for (int i = 0; i < sqrtN; i++) {
            for (int j = 0; j < sqrtN; j++) {
                if (board[subgridRowStart + i][subgridColStart + j] == num) {
                    return false;
                }
            }
        }

        return true; // Number is safe to place in the cell
    }

    public static void printSudoku(int[][] board) {
        int N = board.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku Puzzle:");
            printSudoku(board);
        } else {
            System.out.println("No solution exists for the given puzzle.");
        }
    }
}
