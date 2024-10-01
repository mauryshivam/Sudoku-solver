
public class SudokuSolver {

  // Size of the grid
  private static final int GRID_SIZE = 9;

  public static void main(String[] args) {
      // Example Sudoku puzzle (0 represents an empty cell)
      int[][] board = {
          {7, 0, 2, 0, 5, 0, 6, 0, 0},
          {0, 0, 0, 0, 0, 3, 0, 0, 0},
          {1, 0, 0, 0, 0, 9, 5, 0, 0},
          {8, 0, 0, 0, 0, 0, 0, 9, 0},
          {0, 4, 3, 0, 0, 0, 7, 5, 0},
          {0, 9, 0, 0, 0, 0, 0, 0, 8},
          {0, 0, 9, 7, 0, 0, 0, 0, 5},
          {0, 0, 0, 2, 0, 0, 0, 0, 0},
          {0, 0, 7, 0, 4, 0, 2, 0, 3}
      };

      if (solveBoard(board)) {
          System.out.println("Sudoku solved successfully!");
      } else {
          System.out.println("Unsolvable board!");
      }

      printBoard(board);
  }

  // Function to solve the board using backtracking
  private static boolean solveBoard(int[][] board) {
      for (int row = 0; row < GRID_SIZE; row++) {
          for (int col = 0; col < GRID_SIZE; col++) {
              if (board[row][col] == 0) {
                  for (int num = 1; num <= 9; num++) {
                      if (isValidPlacement(board, num, row, col)) {
                          board[row][col] = num;

                          if (solveBoard(board)) {
                              return true;
                          } else {
                              board[row][col] = 0; // Backtrack
                          }
                      }
                  }
                  return false; // No valid number found, trigger backtrack
              }
          }
      }
      return true; // Board is solved
  }

  // Function to check if placing num in board[row][col] is valid
  private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
      // Check row
      for (int i = 0; i < GRID_SIZE; i++) {
          if (board[row][i] == num) {
              return false;
          }
      }

      // Check column
      for (int i = 0; i < GRID_SIZE; i++) {
          if (board[i][col] == num) {
              return false;
          }
      }

      // Check 3x3 subgrid
      int subgridRowStart = (row / 3) * 3;
      int subgridColStart = (col / 3) * 3;
      for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
          for (int j = subgridColStart; j < subgridColStart + 3; j++) {
              if (board[i][j] == num) {
                  return false;
              }
          }
      }

      return true; // Valid placement
  }

  // Function to print the Sudoku board
  private static void printBoard(int[][] board) {
      for (int row = 0; row < GRID_SIZE; row++) {
          if (row % 3 == 0 && row != 0) {
              System.out.println("-----------");
          }
          for (int col = 0; col < GRID_SIZE; col++) {
              if (col % 3 == 0 && col != 0) {
                  System.out.print("|");
              }
              System.out.print(board[row][col] == 0 ? "." : board[row][col]);
          }
          System.out.println();
       }
    }
}