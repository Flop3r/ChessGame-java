/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */

package GameEngine.Board;

import GameEngine.Moves.CastlingMove;
import GameEngine.Moves.EnPassantMove;
import GameEngine.Moves.Move;
import GameEngine.Pieces.King;
import GameEngine.Pieces.Pawn;
import GameEngine.Pieces.Piece;
import GameEngine.PromotionListener;
import GameEngine.Utils.ChessColor;
import lombok.Getter;

import java.util.LinkedList;

/**
 * Klasa reprezentująca szachownicę.
 */
public class Board {
    @Getter
    private final LinkedList<Piece> pieces;
    @Getter
    private ChessColor round = ChessColor.WHITE;
    private PromotionListener promotionListener;

    /**
     * Konstruktor klasy Board.
     * Tworzy pustą szachownicę.
     */
    public Board() {
        pieces = new LinkedList<>();
    }

    /**
     * Konstruktor klasy Board.
     *
     * @param pieces Lista figur na szachownicy.
     */
    public Board(LinkedList<Piece> pieces) {
        this.pieces = new LinkedList<>();
        for (Piece piece : pieces) {
            this.pieces.add(piece.clone());
        }
    }

    /**
     * Pobiera figurę na danej pozycji na szachownicy.
     *
     * @param x Pozycja x na szachownicy.
     * @param y Pozycja y na szachownicy.
     * @return Obiekt Piece reprezentujący figurę na danej pozycji.
     */
    public Piece getPiece(int x, int y) {
        for (Piece p : pieces) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }

    /**
     * Wykonuje ruch na szachownicy.
     *
     * @param piece Figura, która wykonuje ruch.
     * @param move  Ruch do wykonania.
     */
    public void executeMove(Piece piece, Move move) {
        resetEnPassant();
        if (move instanceof CastlingMove) {
            executeCastlingMove(piece, (CastlingMove) move);
            return;
        }

        if (move instanceof EnPassantMove) {
            executeEnPassantMove(piece, (EnPassantMove) move);
            return;
        }

        executeStandardMove(piece, move);
    }

    /**
     * Wykonuje standardowy ruch figury.
     *
     * @param piece Figura, która wykonuje ruch.
     * @param move  Ruch do wykonania.
     */
    private void executeStandardMove(Piece piece, Move move) {
        Piece targetPiece = getPiece(move.getX(), move.getY());
        if (targetPiece != null) {
            kill(targetPiece);
        }

        Piece newPiece = piece.clone();
        newPiece.movePiece(move);

        if (newPiece instanceof Pawn && ((Pawn) newPiece).isReadyForPromotion()) {
            processPawnPromotion(piece, (Pawn) newPiece);
            return;
        }

        pieces.remove(piece);
        pieces.add(newPiece);
        changeRound();
    }

    /**
     * Przetwarza promocję pionka na inny typ figury.
     *
     * @param originalPiece Oryginalny pionek.
     * @param newPiece      Nowa figura po promocji.
     */
    private void processPawnPromotion(Piece originalPiece, Pawn newPiece) {
        if (promotionListener != null) {
            promotionListener.onPromotionRequested(newPiece);
            pieces.remove(originalPiece);
            changeRound();
        }
    }

    /**
     * Wykonuje ruch w przelocie.
     *
     * @param pawn Pionek wykonujący ruch.
     * @param move Ruch w przelocie.
     */
    private void executeEnPassantMove(Piece pawn, EnPassantMove move) {
        Move movePawn = new Move(move.getX(), move.getY());
        Piece newPawn = pawn.clone();
        newPawn.movePiece(movePawn);

        kill(move.getCapturedPawn());

        pieces.add(newPawn);
        pieces.remove(pawn);
        changeRound();
    }

    /**
     * Wykonuje roszadę.
     *
     * @param piece Figura króla, który wykonuje roszadę.
     * @param move  Ruch roszady.
     */
    private void executeCastlingMove(Piece piece, CastlingMove move) {
        Move moveKing = new Move(move.getX(), move.getY());
        Piece newKing = piece.clone();
        newKing.movePiece(moveKing);

        Move moveRook = new Move(move.getRook_x(), move.getRook_y());
        Piece newRook = move.getRook().clone();
        newRook.movePiece(moveRook);

        pieces.remove(piece);
        pieces.remove(move.getRook());
        pieces.add(newKing);
        pieces.add(newRook);
        changeRound();
    }

    /**
     * Resetuje flagi przelotu dla pionków.
     */
    private void resetEnPassant() {
        for (Piece p : pieces) {
            if (p instanceof Pawn && p.getColor() == round) {
                ((Pawn) p).setAvaliableEnPassant(false);
            }
        }
    }

    /**
     * Filtruje listę potencjalnych ruchów dla danej figury, usuwając te, które prowadzą do szacha.
     *
     * @param piece Figura, dla której filtrujemy ruchy.
     * @return Lista legalnych ruchów.
     */
    public LinkedList<Move> filterPotentialMoves(Piece piece) {
        LinkedList<Move> potentialMoves = piece.generatePotentialMoves(this);

        // Symulacja ruchu, aby sprawdzić, czy nie prowadzi do szacha
        LinkedList<Move> filteredMoves = new LinkedList<>();
        for (Move move : potentialMoves) {
            Board simulatedBoard = new Board(this.pieces);
            Piece simulatedPiece = simulatedBoard.getPiece(piece.getX(), piece.getY());
            simulatedBoard.executeMove(simulatedPiece, move);
            if (!simulatedBoard.isCheck(piece.getColor())) {
                filteredMoves.add(move);
            }
        }

        return filteredMoves;
    }

    /**
     * Sprawdza, czy dany ruch jest legalny dla danej figury.
     *
     * @param piece Figura, dla której sprawdzamy ruch.
     * @param move  Ruch do sprawdzenia.
     * @return True, jeśli ruch jest legalny. False w przeciwnym razie.
     */
    public boolean isMoveLegal(Piece piece, Move move) {
        return filterPotentialMoves(piece).contains(move);
    }

    /**
     * Usuwa daną figurę z szachownicy.
     *
     * @param piece Figura do usunięcia.
     */
    public void kill(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Sprawdza, czy król w określonym kolorze jest w szachu.
     *
     * @param color Kolor króla.
     * @return True, jeśli król jest w szachu. False w przeciwnym razie.
     */
    public boolean isCheck(ChessColor color) {
        for (Piece p : pieces) {
            if (p.getColor() != color) {
                LinkedList<Move> moves = p.generatePotentialMoves(this);
                for (Move move : moves) {
                    Piece pieceAtDestination = getPiece(move.getX(), move.getY());
                    if (pieceAtDestination != null && pieceAtDestination.getColor() == color && pieceAtDestination instanceof King) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Sprawdza, czy król w określonym kolorze jest w matowej pozycji.
     *
     * @param color Kolor króla.
     * @return True, jeśli król jest w matowej pozycji. False w przeciwnym razie.
     */
    public boolean isCheckMate(ChessColor color) {
        if (!isCheck(color)) {
            return false;
        }

        for (Piece p : pieces) {
            if (p.getColor() == color && !isKingTrapped(p)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Sprawdza, czy król jest uwięziony.
     *
     * @param piece Figura króla.
     * @return True, jeśli król jest uwięziony. False w przeciwnym razie.
     */
    private boolean isKingTrapped(Piece piece) {
        LinkedList<Move> moves = filterPotentialMoves(piece);
        for (Move move : moves) {
            Board simulatedBoard = new Board(pieces);
            Piece simulatedPiece = simulatedBoard.getPiece(piece.getX(), piece.getY());
            simulatedBoard.executeMove(simulatedPiece, move);
            if (!simulatedBoard.isCheck(piece.getColor())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sprawdza, czy na szachownicy jest remis.
     *
     * @return True, jeśli jest remis. False w przeciwnym razie.
     */
    public boolean isDraw() {
        if (isPat()) {
            return true;
        } else {
            for (Piece p : pieces) {
                if (!(p instanceof King)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sprawdza, czy na szachownicy jest pat.
     *
     * @return True, jeśli jest pat. False w przeciwnym razie.
     */
    private boolean isPat() {
        // Sprawdzamy, czy król jest atakowany
        if (isCheck(round)) {
            return false;
        }

        // Sprawdzamy, czy jakakolwiek figura koloru gracza ma legalny ruch do wykonania
        for (Piece p : pieces) {
            if (p.getColor() == round && !filterPotentialMoves(p).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Zmienia turę na szachownicy.
     */
    public void changeRound() {
        round = round.opposite();
    }

    /**
     * Ustawia listener promocji figury.
     *
     * @param listener Obiekt nasłuchujący promocji.
     */
    public void setPromotionListener(PromotionListener listener) {
        this.promotionListener = listener;
    }

    /**
     * Ustawia figurę na danej pozycji na szachownicy.
     *
     * @param x     Pozycja x na szachownicy.
     * @param y     Pozycja y na szachownicy.
     * @param piece Figura do ustawienia.
     */
    public void setPiece(int x, int y, Piece piece) {
        // Sprawdzanie, czy figura o danej pozycji już istnieje i usuwanie jej
        for (Piece p : pieces) {
            if (p.getX() == x && p.getY() == y) {
                pieces.remove(p);
                break;
            }
        }

        // Ustawianie nowej figury na danej pozycji
        piece.setX(x);
        piece.setY(y);
        pieces.add(piece);
    }

    /**
     * Zwraca figurę króla o określonym kolorze na szachownicy.
     *
     * @param color Kolor króla.
     * @return Obiekt klasy King reprezentujący króla o określonym kolorze, lub null, jeśli król nie został znaleziony.
     */
    public King getKing(ChessColor color) {
        // Przeszukaj wszystkie figury na szachownicy
        // i zwróć figurę króla o określonym kolorze, jeśli zostanie znaleziona.
        for (Piece piece : pieces) {
            if (piece instanceof King && piece.getColor() == color) {
                return (King) piece;
            }
        }
        // Jeśli król nie zostanie znaleziony, zwróć wartość null.
        return null;
    }
}
