/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces;

import GameEngine.Board.Board;
import GameEngine.Moves.EnPassantMove;
import GameEngine.Moves.Move;
import GameEngine.Utils.ChessColor;
import GameEngine.Utils.GameAssets;
import lombok.Setter;

import java.util.LinkedList;

/**
 * Klasa reprezentująca pionka.
 */
public class Pawn extends Piece {

    private boolean firstMove = true;
    @Setter
    private boolean avaliableEnPassant = false;
    private boolean readyForPromotion = false;

    /**
     * Konstruktor klasy Pawn.
     *
     * @param x     Pozycja x pionka na planszy.
     * @param y     Pozycja y pionka na planszy.
     * @param color Kolor pionka.
     */
    public Pawn(int x, int y, ChessColor color) {
        super(x, y, color, (color == ChessColor.WHITE) ?
                GameAssets.white_pawn :
                GameAssets.black_pawn);
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
     * Sprawdza, czy pionek jest gotowy do promocji.
     *
     * @return true, jeśli pionek jest gotowy do promocji; false w przeciwnym razie.
     */
    public boolean isReadyForPromotion() {
        return readyForPromotion;
    }

    /**
     * Sprawdza, czy bicie w przelocie jest dostępne dla pionka.
     *
     * @return true, jeśli bicie w przelocie jest dostępne; false w przeciwnym razie.
     */
    public boolean isAvaliableEnPassant() {
        return avaliableEnPassant;
    }

    /**
     * Przesuwa pionka na nową pozycję, ustawia flagę firstMove na false i sprawdza możliwość en passant.
     *
     * @param move Obiekt klasy Move zawierający informacje o ruchu.
     */
    @Override
    public void movePiece(Move move) {
        int moveDist = Math.abs(move.getY() - getY());
        super.movePiece(move);

        if (firstMove && moveDist == 2) {
            avaliableEnPassant = true;
        }
        firstMove = false;

        if ((getColor() == ChessColor.WHITE && move.getY() == 0)
                || (getColor() == ChessColor.BLACK && move.getY() == 7)) {
            readyForPromotion = true;
        }
    }

    /**
     * Generuje listę potencjalnych ruchów dla pionka.
     *
     * @param board Obiekt klasy Board reprezentujący szachownicę.
     * @return Lista potencjalnych ruchów pionka.
     */
    @Override
    public LinkedList<Move> generatePotentialMoves(Board board) {
        LinkedList<Move> potentialMoves = new LinkedList<>();
        int direction = (this.getColor() == ChessColor.WHITE) ? -1 : 1;

        // Sprawdź ruch o jedno pole do przodu.
        int nextX = this.x;
        int nextY = this.y + direction;
        if (board.getPiece(nextX, nextY) == null) {
            potentialMoves.add(new Move(nextX, nextY));

            // Jeśli to pierwszy ruch piona, sprawdź ruch o dwa pola do przodu.
            if (isFirstMove() && board.getPiece(nextX, nextY + direction) == null) {
                potentialMoves.add(new Move(nextX, nextY + direction));
            }
        }

        // Sprawdź ruchy bicia.
        if (board.getPiece(nextX - 1, nextY) != null &&
                board.getPiece(nextX - 1, nextY).getColor() != this.getColor()) {
            potentialMoves.add(new Move(nextX - 1, nextY));
        }
        if (board.getPiece(nextX + 1, nextY) != null &&
                board.getPiece(nextX + 1, nextY).getColor() != this.getColor()) {
            potentialMoves.add(new Move(nextX + 1, nextY));
        }

        // Sprawdź ruchy bicia w przelocie.
        Piece piece = board.getPiece(this.x - 1, this.y);
        if (piece instanceof Pawn && piece.getColor() != this.getColor()) {
            if (((Pawn) piece).isAvaliableEnPassant()) {
                potentialMoves.add(new EnPassantMove(nextX - 1, nextY, (Pawn) piece));
            }
        }

        piece = board.getPiece(this.x + 1, this.y);
        if (piece instanceof Pawn && piece.getColor() != this.getColor()) {
            if (((Pawn) piece).isAvaliableEnPassant()) {
                potentialMoves.add(new EnPassantMove(nextX + 1, nextY, (Pawn) piece));
            }
        }

        return potentialMoves;
    }
}
