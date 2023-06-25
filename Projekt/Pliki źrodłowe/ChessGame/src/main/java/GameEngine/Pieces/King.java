/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces;

import GameEngine.Board.Board;
import GameEngine.Moves.CastlingMove;
import GameEngine.Moves.Move;
import GameEngine.Utils.ChessColor;
import GameEngine.Utils.GameAssets;

import java.util.LinkedList;

/**
 * Klasa reprezentująca króla w grze w szachy.
 */
public class King extends Piece {

    private boolean firstMove = true;

    /**
     * Konstruktor klasy King.
     *
     * @param x     Współrzędna x króla na planszy.
     * @param y     Współrzędna y króla na planszy.
     * @param color Kolor króla.
     */
    public King(int x, int y, ChessColor color) {
        super(x, y, color, (color == ChessColor.WHITE) ?
                GameAssets.white_king :
                GameAssets.black_king);
    }

    /**
     * Sprawdza, czy król wykonuje swój pierwszy ruch.
     *
     * @return True, jeśli król wykonuje swój pierwszy ruch; false w przeciwnym razie.
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /**
     * Przesuwa króla na nową pozycję, ustawia flagę firstMove na false.
     *
     * @param move Obiekt klasy Move zawierający informacje o ruchu.
     */
    @Override
    public void movePiece(Move move) {
        super.movePiece(move);
        firstMove = false;
    }

    /**
     * Generuje listę potencjalnych ruchów dla króla, uwzględniając ruchy roszadowe.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return Lista potencjalnych ruchów króla.
     */
    @Override
    public LinkedList<Move> generatePotentialMoves(Board board) {
        LinkedList<Move> potentialMoves = new LinkedList<>();

        // Osiem kierunków: góra, dół, lewo, prawo, góra-lewo, góra-prawo, dół-lewo, dół-prawo.
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions) {
            int newX = this.x + direction[0];
            int newY = this.y + direction[1];
            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                Piece targetPiece = board.getPiece(newX, newY);
                if (targetPiece == null || targetPiece.getColor() != this.getColor()) {
                    potentialMoves.add(new Move(newX, newY));
                }
            }
        }

        if (canLongCastle(board)) {
            Rook rook = (Rook) board.getPiece(x - 4, y);
            potentialMoves.add(new CastlingMove(this.x - 3, this.y, rook, rook.getX() + 2, rook.getY()));
        }

        if (canShortCastle(board)) {
            Rook rook = (Rook) board.getPiece(x + 3, y);
            potentialMoves.add(new CastlingMove(this.x + 2, this.y, rook, rook.getX() - 2, rook.getY()));
        }

        return potentialMoves;
    }

    /**
     * Sprawdza, czy możliwe jest wykonanie długiej roszady.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return True, jeśli długa roszada jest możliwa; false w przeciwnym razie.
     */
    private boolean canLongCastle(Board board) {
        if (!this.isFirstMove() || !(board.getPiece(this.x - 4, this.y) instanceof Rook)) {
            return false;
        }

        Rook rook = (Rook) board.getPiece(this.x - 4, this.y);
        if (!rook.isFirstMove()) {
            return false;
        }

        for (int i = 1; i < 4; i++) {
            if (board.getPiece(this.x - i, this.y) != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Sprawdza, czy możliwe jest wykonanie krótkiej roszady.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return True, jeśli krótka roszada jest możliwa; false w przeciwnym razie.
     */
    private boolean canShortCastle(Board board) {
        if (!this.isFirstMove() || !(board.getPiece(this.x + 3, this.y) instanceof Rook)) {
            return false;
        }

        Rook rook = (Rook) board.getPiece(this.x + 3, this.y);
        if (!rook.isFirstMove()) {
            return false;
        }

        for (int i = 1; i < 3; i++) {
            if (board.getPiece(this.x + i, this.y) != null) {
                return false;
            }
        }

        return true;
    }
}
