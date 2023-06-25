/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.White;

import GameEngine.Pieces.Pawn;
import GameEngine.Utils.ChessColor;

/**
 * Klasa reprezentująca pionka białego.
 */
public class WhitePawn extends Pawn {

    /**
     * Konstruktor klasy WhitePawn.
     *
     * @param x Pozycja x pionka na planszy.
     * @param y Pozycja y pionka na planszy.
     */
    public WhitePawn(int x, int y) {
        super(x, y, ChessColor.WHITE);
    }
}
