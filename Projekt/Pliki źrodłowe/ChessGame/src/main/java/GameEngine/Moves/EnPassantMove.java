/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Moves;

import GameEngine.Pieces.Pawn;
import lombok.Getter;

/**
 * Klasa reprezentująca ruch bicia w przelocie w szachach.
 */
public class EnPassantMove extends Move {

    @Getter
    private Pawn capturedPawn;

    /**
     * Konstruktor klasy EnPassantMove.
     *
     * @param x            Współrzędna x docelowego pola.
     * @param y            Współrzędna y docelowego pola.
     * @param capturedPawn Wskaźnik na pionka, który ma zostać zbity w ruchu.
     */
    public EnPassantMove(int x, int y, Pawn capturedPawn) {
        super(x, y);
        this.capturedPawn = capturedPawn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EnPassantMove other = (EnPassantMove) obj;
        return this.getX() == other.getX() && this.getY() == other.getY() && this.getCapturedPawn() == other.getCapturedPawn();
    }
}
