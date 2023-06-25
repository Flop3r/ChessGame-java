/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.Black;

import GameEngine.Pieces.King;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa BlackKing reprezentuje czarnego króla na szachownicy.
 */
public class BlackKing extends King {

    /**
     * Konstruktor BlackKing.
     *
     * @param x Współrzędna x króla na szachownicy.
     * @param y Współrzędna y króla na szachownicy.
     */
    @SneakyThrows
    public BlackKing(int x, int y) {
        super(x, y, ChessColor.BLACK);
    }
}