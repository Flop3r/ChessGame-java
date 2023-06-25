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
 * Klasa reprezentująca hetmana.
 */
public class Queen extends Piece {

    /**
     * Konstruktor klasy Queen.
     *
     * @param x     Pozycja x hetmana na planszy.
     * @param y     Pozycja y hetmana na planszy.
     * @param color Kolor hetmana.
     */
    public Queen(int x, int y, ChessColor color) {
        super(x, y, color, (color == ChessColor.WHITE) ?
                GameAssets.white_queen :
                GameAssets.black_queen);
    }

    /**
     * Generuje listę potencjalnych ruchów dla hetmana.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return Lista potencjalnych ruchów hetmana.
     */
    @Override
    public LinkedList<Move> generatePotentialMoves(Board board) {
        LinkedList<Move> potentialMoves = new LinkedList<>();

        // Osiem kierunków: góra, dół, lewo, prawo, góra-lewo, góra-prawo, dół-lewo, dół-prawo.
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions) {
            for (int i = 1; i < 8; i++) {
                int newX = this.x + i * direction[0];
                int newY = this.y + i * direction[1];
                if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                    break;
                }
                Piece targetPiece = board.getPiece(newX, newY);
                if (targetPiece == null) {
                    potentialMoves.add(new Move(newX, newY));
                } else {
                    if (targetPiece.getColor() != this.getColor()) {
                        potentialMoves.add(new Move(newX, newY));
                    }
                    break;
                }
            }
        }

        return potentialMoves;
    }
}
