/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.Black;

import GameEngine.Pieces.Rook;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;


/**
 * Klasa BlackRook reprezentuje czarnej wieży na szachownicy.
 */
public class BlackRook extends Rook {

    /**
     * Konstruktor BlackRook.
     *
     * @param x Współrzędna x wieży na szachownicy.
     * @param y Współrzędna y wieży na szachownicy.
     */
    @SneakyThrows
    public BlackRook(int x, int y) {
        super(x, y, ChessColor.BLACK);
    }
}