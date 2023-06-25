/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package Menu;

import GameEngine.ChessGame;
import GameEngine.Utils.GameAssets;
import lombok.SneakyThrows;

import javax.swing.*;

/**
 * Klasa Menu służy do wyświetlania menu gry.
 */
public class Menu {

    /**
     * Wyświetla okno dialogowe startowe.
     * Użytkownik ma dwie opcje: "Start", który rozpoczyna grę, lub "Wyjście", który kończy działanie programu.
     */
    @SneakyThrows
    public static void showStartDialog() {
        // Używamy czarnego rycerza jako ikony dla naszego okna dialogowego
        ImageIcon icon = new ImageIcon(GameAssets.black_knight);
        String message = "Witajcie w grze szachy!";
        String[] options = {"Start", "Wyjście"};

        // Tworzymy i wyświetlamy okno dialogowe
        int choice = JOptionPane.showOptionDialog(null, message, "Start Gry", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, icon, options, options[0]);

        // Zależnie od wyboru użytkownika, zaczynamy grę lub kończymy działanie programu
        if (choice == 0) {
            ChessGame.start();
        } else {
            System.exit(0);
        }
    }
}
