/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Moves;

import lombok.Getter;

import java.util.Objects;

/**
 * Klasa reprezentująca ruch na współrzędne na planszy.
 */
public class Move {
    @Getter
    private int x, y; // Koordynaty

    /**
     * Konstruktor klasy Move.
     *
     * @param x Pozycja x ruchu.
     * @param y Pozycja y ruchu.
     */
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sprawdza, czy dany obiekt jest równy bieżącemu ruchowi.
     *
     * @param obj Obiekt do porównania.
     * @return True, jeśli obiekty są równe. False w przeciwnym razie.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Move move = (Move) obj;
        return x == move.x && y == move.y;
    }

    /**
     * Oblicza hashcode dla ruchu.
     *
     * @return Hashcode ruchu.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
