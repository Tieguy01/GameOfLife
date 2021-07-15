import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Hashtable;

import javax.swing.*;

import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener{

    private Graphics2D g2D;
    
    private Cell[][] board;
    private Hashtable<Point, Cell> cells;
    private int[][] aliveCoords;

    public Board() {
        setBackground(Color.BLACK);
        this.addMouseListener(this);

        board = new Cell[41][73];
        cells = new Hashtable<>();

        int x = 0;
        int y = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Cell();
                board[row][col].setX(x);
                board[row][col].setY(y);
                cells.put(new Point(x, y), board[row][col]);
                x += 21;
            }
            x = 0;
            y += 21;
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

    boolean aliveSet = false;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g;

        // if (!aliveSet) setAlive();
        drawBoard();
    }

    public void drawBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].getState().equals(CellStates.ALIVE)) {
                    g2D.setColor(Color.BLACK);
                } else {
                    g2D.setColor(Color.WHITE);
                }
                g2D.fill(new Rectangle2D.Double(board[row][col].getX(), board[row][col].getY(), 20, 20));
            }
        }
    }

    public void setAlive() {
        g2D.setColor(Color.BLACK);
        if (aliveCoords.length > 0) {
            for (int[] coord : aliveCoords) {
                board[coord[0]][coord[1]].setState(CellStates.ALIVE);
                g2D.fill(new Rectangle2D.Double(board[coord[0]][coord[1]].getX(), board[coord[0]][coord[1]].getY(), 20, 20));
            }
            aliveSet = true;
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

        repaint();
    }

    public void setAliveCoords(int[][] aliveCoords) {
        this.aliveCoords = aliveCoords;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = new Point();
        clickPoint.x = e.getX() - (e.getX() % 21);
        clickPoint.y = e.getY() - (e.getY() % 21);

        cells.get(clickPoint).switchState();

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}

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
        board.setAliveCoords(pentadecathlon);
        board.printBoard();

        for (int i = 0; i < 37; i++) {
            board.tick();
            board.printBoard();
        }
    }

}
