/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Utils;

import java.awt.*;

/**
 * Klasa zawierająca stałe używanych w grze.
 */
public class GameConst {
    // Wartości kolorów
    public final static Color FST_COLOR = new Color(229, 227, 189); // Jasnożółty
    public final static Color SND_COLOR = new Color(139, 155, 91); // Zielony
    public final static Color HIGHLIGHT_COLOR = new Color(255, 252, 0, 255); // Żółty
    public final static Color CHECK_COLOR = new Color(255, 88, 119, 255); // Czerwony
    public final static Color CHECKMATE_COLOR = new Color(218, 0, 33, 255); // Czerwony
    public final static Color MOVES_COLOR = new Color(75, 75, 75, 255); // Ciemnoszary

    // Wartości przezroczystości
    public final static float HIGHLIGHT_ALPHA = 0.5f;
    public final static float MOVES_ALPHA = 0.5f;
}
