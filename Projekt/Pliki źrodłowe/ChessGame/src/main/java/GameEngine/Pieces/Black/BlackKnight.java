/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.Black;

import GameEngine.Pieces.Knight;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa BlackKnight reprezentuje czarnego skoczka na szachownicy.
 */
public class BlackKnight extends Knight {

    /**
     * Konstruktor BlackKnight.
     *
     * @param x Współrzędna x skoczka na szachownicy.
     * @param y Współrzędna y skoczka na szachownicy.
     */
    @SneakyThrows
    public BlackKnight(int x, int y) {
        super(x, y, ChessColor.BLACK);
    }
}