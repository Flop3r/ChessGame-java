/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Moves;

import GameEngine.Pieces.Rook;
import lombok.Getter;

/**
 * Klasa reprezentująca ruch roszady w szachach.
 */
public class CastlingMove extends Move {

    @Getter
    private Rook rook;

    @Getter
    private int rook_x, rook_y;

    /**
     * Konstruktor klasy CastlingMove.
     *
     * @param king_x Współrzędna x docelowego pola króla.
     * @param king_y Współrzędna y docelowego pola króla.
     * @param rook   Wskaźnik na wieżę, która jest używana do roszady.
     * @param rook_x Współrzędna x docelowego pola wieży.
     * @param rook_y Współrzędna y docelowego pola wieży.
     */
    public CastlingMove(int king_x, int king_y, Rook rook, int rook_x, int rook_y) {
        super(king_x, king_y);
        this.rook = rook;
        this.rook_x = rook_x;
        this.rook_y = rook_y;
    }
}
