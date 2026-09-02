import java.util.*; // Predefined function of Java - imports utility classes like Scanner, List, ArrayList, Arrays

// Created class to solve the N-Queens problem
class Queens {

    // Created function - checks if placing a Queen at board[row][col] is safe
    public boolean isSafe(int row, int col, char[][] board) {
        // Check horizontally
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }

        // Check vertically
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        int r = row;
        for (int c = col; c >= 0 && r >= 0; c--, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        r = row;
        for (int c = col; c < board.length && r >= 0; c++, r--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check lower-left diagonal
        r = row;
        for (int c = col; c >= 0 && r < board.length; c--, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Fix added: reinitialize `r` before lower-right traversal
        r = row;

        // Check lower-right diagonal
        for (int c = col; c < board.length && r < board.length; c++, r++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true; // Safe to place Queen
    }

    // Created function - saves current board configuration to list of strings
    public void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>(); // Predefined function of Java - dynamic string list

        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder(); // Predefined function of Java - improves string building performance
            for (int j = 0; j < board[i].length; j++) {
                row.append(board[i][j] == 'Q' ? 'Q' : '.'); // Add 'Q' or '.' based on board
            }
            newBoard.add(row.toString()); // Convert and add each row to board list
        }

        allBoards.add(newBoard); // Add complete board to result
    }

    // Created function - backtracking helper to place Queens column-wise
    public void helper(char[][] board, List<List<String>> allBoards, int col) {
        if (col == board.length) {
            saveBoard(board, allBoards); // All columns handled, save board
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q'; // Place Queen
                helper(board, allBoards, col + 1); // Recur for next column
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    // Created function - starts N-Queens solution logic
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>(); // Predefined - stores results
        char[][] board = new char[n][n]; // Predefined - creates empty board

        // Predefined function of Java - initialize board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        helper(board, allBoards, 0); // Start solving from column 0
        return allBoards;
    }

    // Created function - main method for user interaction and execution
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Predefined function of Java - reads user input from console

        System.out.print("Enter the value of n (1 to 18): "); // Prompt user for board size
        int n = sc.nextInt(); // Predefined function - reads integer input

        if (n < 1 || n > 18) {
            System.out.println("Invalid input. Please enter a number between 1 and 18."); // Input validation
            return;
        }

        Queens solver = new Queens(); // Instantiate the Queens class
        List<List<String>> solutions = solver.solveNQueens(n); // Get all valid N-Queens boards

        System.out.println("Total Solutions: " + solutions.size()); // Show count of valid solutions

        // Optional: Print each board
        for (List<String> board : solutions) {
            for (String row : board) {
                System.out.println(row); // Print each row
            }
            System.out.println(); // Empty line between boards
        }

        sc.close(); // Predefined function of Java - close scanner to release resources
    }
}
