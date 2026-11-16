/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackhole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author anash
 */
public class BoardGUI {

    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private JLabel statusLabel;

    private int boardSize;
    private Point selectedShip;

    public BoardGUI(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Board(boardSize);
        this.selectedShip = null;

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];

        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(80, 50));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        refresh();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void refresh() {
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                Field field = board.get(i, j);
                JButton button = buttons[i][j];

                if (field.isHole()) {
                    button.setBackground(Color.BLACK);
                    button.setForeground(Color.WHITE);
                    button.setText("O");
                } else if (field.getColor() != null) {
                    button.setBackground(field.getColor());
                    button.setForeground(Color.WHITE);
                    button.setText("S");
                } else {
                    button.setBackground(null);
                    button.setForeground(Color.BLACK);
                    button.setText("");
                }

                if (selectedShip != null && selectedShip.x == i && selectedShip.y == j) {
                    button.setText("[S]");
                }
            }
        }

        String playerText = (board.getCurrentPlayer() == 1) ? "Player 1 (Red)" : "Player 2 (Blue)";
        statusLabel.setText(playerText + "'s turn | In hole: P1 = "
                + board.getPlayer1InHole() + "/" + board.getRequiredShipsToWin()
                + ", P2 = " + board.getPlayer2InHole() + "/" + board.getRequiredShipsToWin());

        if (board.isOver()) {
            int winner = board.getWinner();
            String winnerText = (winner == 1) ? "Player 1 (Red)" : "Player 2 (Blue)";
            JOptionPane.showMessageDialog(boardPanel, winnerText + " has won!", "Game Over", JOptionPane.PLAIN_MESSAGE);
            
            board = new Board(boardSize);
            selectedShip = null;
            refresh();
        }
    }

    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Field clickedField = board.get(x, y);

            if (selectedShip == null) {
                if (clickedField.getColor() != null && clickedField.getColor().equals(board.getCurrentPlayerColor())) {
                    selectedShip = new Point(x, y);
                    refresh();
                }
                return;
            }

            if (selectedShip.x == x && selectedShip.y == y) {
                selectedShip = null;
                refresh();
                return;
            }

            if (clickedField.getColor() != null && clickedField.getColor().equals(board.getCurrentPlayerColor())) {
                selectedShip = new Point(x, y);
                refresh();
                return;
            }

            boolean moved = board.makeMove(selectedShip.x, selectedShip.y, x, y);
            if (moved) {
                selectedShip = null;
                refresh();
            }
        }
    }
}
