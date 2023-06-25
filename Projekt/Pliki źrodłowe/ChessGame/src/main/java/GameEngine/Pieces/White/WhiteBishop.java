/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.White;

import GameEngine.Pieces.Bishop;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;


/**
 * Klasa WhiteBishop reprezentuje białego gońca na szachownicy.
 */
public class WhiteBishop extends Bishop {

    /**
     * Konstruktor WhiteBishop.
     *
     * @param x Współrzędna x gońca na szachownicy.
     * @param y Współrzędna y gońca na szachownicy.
     */
    @SneakyThrows
    public WhiteBishop(int x, int y) {
        super(x, y, ChessColor.WHITE);
    }
}