/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Utils;

/**
 * Wyliczenie reprezentujące kolory szachowe.
 */
public enum ChessColor {
    WHITE,
    BLACK;

    /**
     * Metoda zwracająca przeciwny kolor.
     *
     * @return Przeciwny kolor.
     */

    public ChessColor opposite() {
        if (this == WHITE) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
