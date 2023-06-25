/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.White;

import GameEngine.Pieces.Rook;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa WhiteRook reprezentuje czarnej wieży na szachownicy.
 */
public class WhiteRook extends Rook {

    /**
     * Konstruktor WhiteRook.
     *
     * @param x Współrzędna x wieży na szachownicy.
     * @param y Współrzędna y wieży na szachownicy.
     */
    @SneakyThrows
    public WhiteRook(int x, int y) {
        super(x, y, ChessColor.WHITE);
    }
}