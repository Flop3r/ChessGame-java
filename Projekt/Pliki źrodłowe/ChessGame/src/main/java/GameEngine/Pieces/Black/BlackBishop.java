/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.Black;

import GameEngine.Pieces.Bishop;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa BlackBishop reprezentuje czarnego gońca na szachownicy.
 */
public class BlackBishop extends Bishop {

    /**
     * Konstruktor BlackBishop.
     *
     * @param x Współrzędna x gońca na szachownicy.
     * @param y Współrzędna y gońca na szachownicy.
     */
    @SneakyThrows
    public BlackBishop(int x, int y) {
        super(x, y, ChessColor.BLACK);
    }
}