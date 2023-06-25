/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.Black;

import GameEngine.Pieces.Pawn;
import GameEngine.Utils.ChessColor;

/**
 * Klasa BlackPawn reprezentuje czarnego piona na szachownicy.
 */
public class BlackPawn extends Pawn {

    /**
     * Konstruktor BlackPawn.
     *
     * @param x Współrzędna x piona na szachownicy.
     * @param y Współrzędna y piona na szachownicy.
     */
    public BlackPawn(int x, int y) {
        super(x, y, ChessColor.BLACK);
    }
}