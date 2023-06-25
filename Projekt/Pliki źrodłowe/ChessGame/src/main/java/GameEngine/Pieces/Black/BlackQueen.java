/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces.Black;

import GameEngine.Pieces.Queen;
import GameEngine.Utils.ChessColor;
import lombok.SneakyThrows;


/**
 * Klasa BlackQueen reprezentuje czarnego hetmana na szachownicy.
 */
public class BlackQueen extends Queen {

    /**
     * Konstruktor BlackQueen.
     *
     * @param x Współrzędna x hetmana na szachownicy.
     * @param y Współrzędna y hetmana na szachownicy.
     */
    @SneakyThrows
    public BlackQueen(int x, int y) {
        super(x, y, ChessColor.BLACK);
    }
}