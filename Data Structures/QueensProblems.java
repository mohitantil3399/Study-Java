import java.util.*; // Predefined function of Java - provides List, ArrayList, Scanner, etc.

class QueensProblems {

    // Created function - checks if it's safe to place a queen at board[row][col]
    public boolean isSafe(int row, int col, char[][] board) {
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == 'Q') return false;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') return false;
        }
        int r = row;
        for (int c = col; c >= 0 && r >= 0; c--, r--) {
            if (board[r][c] == 'Q') return false;
        }
        r = row;
        for (int c = col; c < board.length && r >= 0; c++, r--) {
            if (board[r][c] == 'Q') return false;
        }
        r = row;
        for (int c = col; c >= 0 && r < board.length; c--, r++) {
            if (board[r][c] == 'Q') return false;
        }
        r = row;
        for (int c = col; c < board.length && r < board.length; c++, r++) {
            if (board[r][c] == 'Q') return false;
        }
        return true;
    }

    // Created function - saves current board configuration
    public void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                row.append(board[i][j] == 'Q' ? 'Q' : '.');
            }
            newBoard.add(row.toString());
        }
        allBoards.add(newBoard);
    }

    // Created function - backtracking recursive method to place queens
    public void helper(char[][] board, List<List<String>> allBoards, int col) {
        if (col == board.length) {
            saveBoard(board, allBoards);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';
                helper(board, allBoards, col + 1);
                board[row][col] = '.';
            }
        }
    }

    // Created function - solves N-Queens and returns all valid boards
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.'); // Predefined function of Java - fills board with '.'
        }
        helper(board, allBoards, 0);
        return allBoards;
    }

    // Created function - driver method with input and output formatting
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Predefined - reads user input
        System.out.print("Enter the value of n (1 to 18): ");
        int n = sc.nextInt();

        if (n < 1 || n > 18) {
            System.out.println("Invalid input. Please enter a number between 1 and 18.");
            return;
        }

        QueensProblems solver = new QueensProblems();
        List<List<String>> solutions = solver.solveNQueens(n);

        System.out.println("\nTotal Solutions: " + solutions.size());

        // Print each board with numbering
        for (int idx = 0; idx < solutions.size(); idx++) {
            System.out.println("\nSolution " + (idx + 1) + ":");
            List<String> board = solutions.get(idx);
            for (String row : board) {
                System.out.println(row); // Print visual board row-wise
            }
        }

        sc.close(); // Predefined - release scanner resource
    }
}
