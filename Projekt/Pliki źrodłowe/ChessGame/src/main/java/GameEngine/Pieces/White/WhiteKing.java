/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.White;

import GameEngine.Pieces.King;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa WhiteKing reprezentuje czarnego króla na szachownicy.
 */
public class WhiteKing extends King {

    /**
     * Konstruktor WhiteKing.
     *
     * @param x Współrzędna x króla na szachownicy.
     * @param y Współrzędna y króla na szachownicy.
     */
    @SneakyThrows
    public WhiteKing(int x, int y) {
        super(x, y, ChessColor.WHITE);
    }
}