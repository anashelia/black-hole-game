/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackhole;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author anash
 */
public class Board {

    public static final Color PLAYER1_COLOR = Color.RED;
    public static final Color PLAYER2_COLOR = Color.BLUE;

    private Field[][] board;
    private final int boardSize;
    private final Point hole;

    private int currentPlayer; 
    private int player1InHole;
    private int player2InHole;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Field[boardSize][boardSize];
        int mid = boardSize / 2;
        this.hole = new Point(mid, mid);

        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                board[i][j] = new Field(i == mid && j == mid);
            }
        }

        this.currentPlayer = 1;
        this.player1InHole = 0;
        this.player2InHole = 0;

        placeShips();
    }

    private void placeShips() {
        int mid = boardSize / 2;
        for (int i = 0; i < boardSize; i++) {
            if (i < mid) {
                board[i][i].setColor(PLAYER2_COLOR);
                board[i][boardSize - 1 - i].setColor(PLAYER2_COLOR);
            } else if (i > mid) {
                board[i][i].setColor(PLAYER1_COLOR);
                board[i][boardSize - 1 - i].setColor(PLAYER1_COLOR);
            }
        }
    }

    public Field get(int x, int y) {
        return board[x][y];
    }

    public Field get(Point p) {
        return get((int) p.getX(), (int) p.getY());
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Point getHole() {
        return hole;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Color getCurrentPlayerColor() {
        return (currentPlayer == 1) ? PLAYER1_COLOR : PLAYER2_COLOR;
    }

    public int getPlayer1InHole() {
        return player1InHole;
    }

    public int getPlayer2InHole() {
        return player2InHole;
    }

    public int getRequiredShipsToWin() {
        return (boardSize - 1) / 2;
    }

    public boolean isOver() {
        int req = getRequiredShipsToWin();
        return player1InHole >= req || player2InHole >= req;
    }

    public int getWinner() {
        int req = getRequiredShipsToWin();
        if (player1InHole >= req) {
            return 1;
        } else if (player2InHole >= req) {
            return 2;
        }
        return 0;
    }

    
    
    public Point findDestination(int fromRow, int fromCol, int dRow, int dCol) {
        if (dRow == 0 && dCol == 0) {
            return null;
        }

        int r = fromRow + dRow;
        int c = fromCol + dCol;
        Point lastValid = null;

        while (r >= 0 && r < boardSize && c >= 0 && c < boardSize) {
            Field f = board[r][c];
            if (f.isHole()) {
                return new Point(r, c);
            }
            if (f.getColor() != null) {
                return lastValid;
            }
            lastValid = new Point(r, c);
            r += dRow;
            c += dCol;
        }

        return lastValid;
    }


    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        int dRow = Integer.compare(toRow, fromRow);
        int dCol = Integer.compare(toCol, fromCol);

        if ((dRow != 0 && dCol != 0) || (dRow == 0 && dCol == 0)) {
            return false;
        }

        Point dest = findDestination(fromRow, fromCol, dRow, dCol);
        if (dest == null || (dest.x == fromRow && dest.y == fromCol)) {
            return false;
        }

        Field src = board[fromRow][fromCol];
        Field target = board[dest.x][dest.y];

        Color shipColor = src.getColor();
        src.setColor(null);

        if (target.isHole()) {
            if (currentPlayer == 1) {
                player1InHole++;
            } else {
                player2InHole++;
            }
        } else {
            target.setColor(shipColor);
        }

        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        return true;
    }
}