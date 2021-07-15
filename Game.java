import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements ActionListener{
    
    private JFrame frame;
    private Board board;
    private JButton nextButton;

    public Game() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);

        frame.getContentPane().setBackground(Color.red);
        frame.setSize(700, 700);//246, 416

        board = new Board();
        frame.add(board, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        // nextButton.setBounds(100, 100, 120, 30);
        nextButton.addActionListener(this);
        frame.add(nextButton, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    public void setAliveCoords(int[][] aliveCoords) {
        board.setAliveCoords(aliveCoords);
        board.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.tick();
    }

    // public void start() {
    //     while (true) {
    //         board.repaint();
    //         for (int i = 0; i < 1000000000; i++);
    //     }
    // }

    public static void main(String[] args) {
        Game game = new Game();

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
            {4, 5},
            {5, 5},
            {6, 4},
            {6, 6},
            {7, 5},
            {8, 5},
            {9, 5},
            {10, 5},
            {11, 4},
            {11, 6},
            {12, 5},
            {13, 5}
        };

        game.setAliveCoords(glider);
        // game.start();
    }

}
