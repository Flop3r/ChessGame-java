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
 * Klasa reprezentująca gońca.
 */
public class Bishop extends Piece {

    /**
     * Konstruktor klasy Bishop.
     *
     * @param x     Pozycja x gońca na planszy.
     * @param y     Pozycja y gońca na planszy.
     * @param color Kolor gońca.
     */
    public Bishop(int x, int y, ChessColor color) {
        super(x, y, color, (color == ChessColor.WHITE) ?
                GameAssets.white_bishop :
                GameAssets.black_bishop);
    }

    /**
     * Generuje listę potencjalnych ruchów dla gońca.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return Lista potencjalnych ruchów gońca.
     */
    @Override
    public LinkedList<Move> generatePotentialMoves(Board board) {
        LinkedList<Move> potentialMoves = new LinkedList<>();

        // Cztery kierunki: góra-lewo, góra-prawo, dół-lewo, dół-prawo.
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
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
