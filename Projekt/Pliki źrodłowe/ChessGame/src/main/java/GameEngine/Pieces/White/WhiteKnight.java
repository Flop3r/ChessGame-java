/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.White;

import GameEngine.Pieces.Knight;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa WhiteKnight reprezentuje czarnego skoczka na szachownicy.
 */
public class WhiteKnight extends Knight {

    /**
     * Konstruktor WhiteKnight.
     *
     * @param x Współrzędna x skoczka na szachownicy.
     * @param y Współrzędna y skoczka na szachownicy.
     */
    @SneakyThrows
    public WhiteKnight(int x, int y) {
        super(x, y, ChessColor.WHITE);
    }
}