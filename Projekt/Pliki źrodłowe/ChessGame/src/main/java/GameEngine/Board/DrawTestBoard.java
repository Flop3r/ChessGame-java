/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Board;

import GameEngine.Pieces.Black.BlackKing;
import GameEngine.Pieces.Piece;
import GameEngine.Pieces.White.WhiteKing;
import GameEngine.Pieces.White.WhitePawn;

import java.util.LinkedList;

/**
 * Klasa reprezentująca początkową konfigurację szachownicy.
 */
public class DrawTestBoard extends Board {

    /**
     * Konstruktor klasy DrawTestBoard. Tworzy szachownicę do testowania remisów.
     */
    public DrawTestBoard() {
        super(initializePieces());
    }

    private static LinkedList<Piece> initializePieces() {
        LinkedList<Piece> pieces = new LinkedList<>();

        pieces.add(new WhitePawn(0, 1));
        pieces.add(new WhiteKing(7, 7));

        pieces.add(new BlackKing(0, 0));


        return pieces;
    }
}
