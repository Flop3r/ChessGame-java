/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces;

import GameEngine.Board.Board;
import GameEngine.Moves.Move;
import GameEngine.Utils.ChessColor;
import GameEngine.Utils.GameAssets;

import java.util.LinkedList;

/**
 * Klasa reprezentująca skoczka.
 */
public class Knight extends Piece {

    /**
     * Konstruktor klasy Knight.
     *
     * @param x     Pozycja x skoczka na planszy.
     * @param y     Pozycja y skoczka na planszy.
     * @param color Kolor skoczka.
     */
    public Knight(int x, int y, ChessColor color) {
        super(x, y, color, (color == ChessColor.WHITE) ?
                GameAssets.white_knight :
                GameAssets.black_knight);
    }

    /**
     * Generuje listę potencjalnych ruchów dla skoczka.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return Lista potencjalnych ruchów skoczka.
     */
    @Override
    public LinkedList<Move> generatePotentialMoves(Board board) {
        LinkedList<Move> potentialMoves = new LinkedList<>();

        // Osiem możliwych "ruchów L".
        int[][] directions = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        for (int[] direction : directions) {
            int newX = this.x + direction[0];
            int newY = this.y + direction[1];
            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                continue;
            }
            Piece targetPiece = board.getPiece(newX, newY);
            if (targetPiece == null || targetPiece.getColor() != this.getColor()) {
                potentialMoves.add(new Move(newX, newY));
            }
        }

        return potentialMoves;
    }
}


