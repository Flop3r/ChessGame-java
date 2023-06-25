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
 * Klasa reprezentująca wieżę.
 */
public class Rook extends Piece {

    private boolean firstMove = true;

    /**
     * Konstruktor klasy Rook.
     *
     * @param x     Pozycja x wieży na planszy.
     * @param y     Pozycja y wieży na planszy.
     * @param color Kolor wieży.
     */
    public Rook(int x, int y, ChessColor color) {
        super(x, y, color, (color == ChessColor.WHITE) ?
                GameAssets.white_rook :
                GameAssets.black_rook);
    }

    /**
     * Przesuwa wieżę na nową pozycję i ustawia flagę firstMove na false.
     *
     * @param move Obiekt klasy Move zawierający informacje o ruchu.
     */
    public void movePiece(Move move) {
        super.movePiece(move);
        firstMove = false;
    }

    /**
     * Sprawdza, czy figura wykonuje pierwszy ruch.
     *
     * @return true, jeśli figura wykonuje pierwszy ruch; false w przeciwnym razie.
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /**
     * Generuje listę prawidłowych ruchów dla wieży.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return Lista potencjalnych ruchów wieży.
     */
    @Override
    public LinkedList<Move> generatePotentialMoves(Board board) {
        LinkedList<Move> potentialMoves = new LinkedList<>();

        // Cztery kierunki: góra, dół, lewo, prawo.
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
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
