/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.White;

import GameEngine.Pieces.Queen;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;

/**
 * Klasa WhiteQueen reprezentuje czarnego hetmana na szachownicy.
 */
public class WhiteQueen extends Queen {

    /**
     * Konstruktor WhiteQueen.
     *
     * @param x Współrzędna x hetmana na szachownicy.
     * @param y Współrzędna y hetmana na szachownicy.
     */
    @SneakyThrows
    public WhiteQueen(int x, int y) {
        super(x, y, ChessColor.WHITE);
    }
}