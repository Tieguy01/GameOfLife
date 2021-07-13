public class Board {
    
    private Cell[][] board;

    public Board() {
        board = new Cell[18][11];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Cell();
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                makeBag(row, col);
            }
        }
    }

    public void makeBag(int row, int col) {
        boolean rowTop = false;
        boolean rowBottom = false;

        if (row - 1 >= 0) {
            board[row][col].addAdjCell(board[row-1][col]);
            rowTop = true;
        }
        if (row + 1 <= board.length - 1) {
            board[row][col].addAdjCell(board[row+1][col]);
            rowBottom = true;
        }
        if (col - 1 >= 0) {
            board[row][col].addAdjCell(board[row][col-1]);
            if (rowTop) board[row][col].addAdjCell(board[row-1][col-1]);
            if (rowBottom) board[row][col].addAdjCell(board[row+1][col-1]);
        }
        if (col + 1 <= board[0].length - 1) {
            board[row][col].addAdjCell(board[row][col+1]);
            if (rowTop) board[row][col].addAdjCell(board[row-1][col+1]);
            if (rowBottom) board[row][col].addAdjCell(board[row+1][col+1]);
        }
    }

    public void tick() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col].check();
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col].tick();
            }
        }
    }

    public void setAlive(int[][] coordList) {
        for (int[] coord : coordList) {
            board[coord[0]][coord[1]].setState(CellStates.ALIVE);
        }
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].getState().equals(CellStates.DEAD)) System.out.print("  ");
                else if (board[row][col].getState().equals(CellStates.ALIVE)) System.out.print("1 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Board board = new Board();

        int[][] blinker = {
            {2, 1},
            {2, 2},
            {2, 3}
        };

        int[][] toad = {
            {2, 2},
            {2, 3},
            {2, 4},
            {3, 1},
            {3, 2},
            {3, 3}
        };

        int[][] beacon = {
            {1, 1},
            {1, 2},
            {2, 1},
            {3, 4},
            {4, 3},
            {4, 4}
        };

        int[][] glider = {
            {0, 1},
            {1, 2},
            {2, 0},
            {2, 1},
            {2, 2}
        };
        
        int [][] pentadecathlon = {
            {5, 5},
            {6, 5},
            {7, 4},
            {7, 6},
            {8, 5},
            {9, 5},
            {10, 5},
            {11, 5},
            {12, 4},
            {12, 6},
            {13, 5},
            {14, 5}
        };
        board.setAlive(pentadecathlon);
        board.printBoard();

        for (int i = 0; i < 37; i++) {
            board.tick();
            board.printBoard();
        }
    }

}
