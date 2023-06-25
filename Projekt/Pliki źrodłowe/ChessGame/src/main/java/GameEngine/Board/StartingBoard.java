/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Board;

import GameEngine.Pieces.Black.*;
import GameEngine.Pieces.Piece;
import GameEngine.Pieces.White.*;

import java.util.LinkedList;

/**
 * Klasa reprezentująca początkową konfigurację szachownicy.
 */
public class StartingBoard extends Board {

    /**
     * Konstruktor klasy StartingBoard. Tworzy szachownicę w początkowym stanie.
     */
    public StartingBoard() {
        super(initializePieces());
    }

    /**
     * Inicjalizuje i ustawia wszystkie figury na szachownicy w początkowym stanie.
     *
     * @return Lista figur ustawionych na szachownicy.
     */
    private static LinkedList<Piece> initializePieces() {
        LinkedList<Piece> pieces = new LinkedList<>();

        // Ustawienie białych pionów na szachownicy
        for (int n = 0; n < 8; n++) {
            pieces.add(new WhitePawn(n, 6));
        }

        // Ustawienie reszty białych figur na szachownicy
        pieces.add(new WhiteKing(4, 7));
        pieces.add(new WhiteQueen(3, 7));
        pieces.add(new WhiteBishop(2, 7));
        pieces.add(new WhiteBishop(5, 7));
        pieces.add(new WhiteKnight(1, 7));
        pieces.add(new WhiteKnight(6, 7));
        pieces.add(new WhiteRook(0, 7));
        pieces.add(new WhiteRook(7, 7));

        // Ustawienie czarnych pionów na szachownicy
        for (int n = 0; n < 8; n++) {
            pieces.add(new BlackPawn(n, 1));
        }

        // Ustawienie reszty czarnych figur na szachownicy
        pieces.add(new BlackKing(4, 0));
        pieces.add(new BlackQueen(3, 0));
        pieces.add(new BlackBishop(2, 0));
        pieces.add(new BlackBishop(5, 0));
        pieces.add(new BlackKnight(1, 0));
        pieces.add(new BlackKnight(6, 0));
        pieces.add(new BlackRook(0, 0));
        pieces.add(new BlackRook(7, 0));

        return pieces;
    }
}
