/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa Settings zawiera metody do konfiguracji ustawień gry.
 */
public class Settings {
    /**
     * Ustawia domyślne czcionki dla różnych komponentów interfejsu użytkownika.
     *
     * Times font jest używany dla przycisków, a Serif dla etykiet, pól tekstowych i obszarów tekstowych.
     */
    public static void setupFonts() {
        // Ustawienie czcionek
        Font timesFont = new Font("Times", Font.PLAIN, 12);
        Font serifFont = new Font("Serif", Font.PLAIN, 12);
        UIManager.put("Button.font", timesFont);
        UIManager.put("Label.font", serifFont);
        UIManager.put("TextField.font", serifFont);
        UIManager.put("TextArea.font", serifFont);
    }
}
