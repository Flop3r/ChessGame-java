/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package Main;

import GameEngine.Utils.Settings;
import Menu.Menu;

import javax.swing.*;

/**
 * Główna klasa aplikacji szachowej.
 */
public class Main {

    /**
     * Główna metoda uruchamiająca aplikację.
     *
     * @param args argumenty wiersza poleceń.
     */
    public static void main(String[] args) {
        // Aby zapewnić bezpieczne wywołanie metod związanych z interfejsem użytkownika,
        // używamy metody SwingUtilities.invokeLater
        SwingUtilities.invokeLater(() -> {
            Settings.setupFonts();
            Menu.showStartDialog();
        });
    }
}
