/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine;

import GameEngine.Pieces.*;
import GameEngine.Utils.ChessColor;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa reprezentująca grę w szachy.
 */
public class ChessGame {
    private static boolean gameOver;
    private static ChessBoardPanel chessBoard;

    /**
     * Konstruktor klasy ChessGame.
     */
    public ChessGame() {
        chessBoard = new ChessBoardPanel();
    }

    /**
     * Metoda sprawdzająca, czy gra się zakończyła.
     *
     * @return true, jeśli gra się zakończyła, false w przeciwnym razie.
     */
    public static boolean isGameOver() {
        return gameOver;
    }

    /**
     * Metoda rozpoczynająca grę w szachy.
     */
    public static void start() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);

        chessBoard = new ChessBoardPanel();
        frame.add(chessBoard);

        setPromotionListener();

        frame.revalidate();
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metoda kończąca grę.
     *
     * @param winner Zwycięzca gry.
     */
    public static void endGame(String winner) {
        JFrame endFrame = new JFrame();
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setLayout(new FlowLayout());
        endFrame.setResizable(false);

        JLabel label = createEndGameLabel(winner);
        JButton playAgainButton = createPlayAgainButton(endFrame);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(playAgainButton);

        endFrame.add(label);
        endFrame.add(buttonPanel);
        endFrame.setSize(400, 200);
        endFrame.setVisible(true);
        gameOver = true;
    }

    /**
     * Metoda rozpoczynająca nową grę.
     */
    public static void startNewGame() {
        gameOver = false;
        closeAllWindows();
        start();
    }

    /**
     * Metoda obsługująca promocję pionka.
     *
     * @param selectedPiece Wybrana figura (pionek).
     */
    private static void handlePromotion(Piece selectedPiece) {
        int choice = showPromotionDialog();

        Piece newPiece = createPromotedPiece(selectedPiece, choice);

        chessBoard.getBoard().setPiece(selectedPiece.getX(), selectedPiece.getY(), newPiece);
        chessBoard.repaint();
    }

    /**
     * Metoda ustawiająca PromotionListener dla szachownicy.
     */
    private static void setPromotionListener() {
        chessBoard.getBoard().setPromotionListener(ChessGame::handlePromotion);
    }

    /**
     * Metoda tworząca etykietę informującą o zakończeniu gry.
     *
     * @param winner Zwycięzca gry.
     * @return Etykieta z informacją o zakończeniu gry.
     */
    private static JLabel createEndGameLabel(String winner) {
        JLabel label;
        if (winner == null) {
            label = new JLabel("REMIS!");
        } else {
            label = new JLabel("SZACH MAT! Wygrał " + winner);
        }
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }

    /**
     * Metoda tworząca przycisk "Zagraj ponownie".
     *
     * @param endFrame Okno zakończenia gry.
     * @return Przycisk "Zagraj ponownie".
     */
    private static JButton createPlayAgainButton(JFrame endFrame) {
        JButton playAgainButton = new JButton("Zagraj ponownie");
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 18));
        playAgainButton.addActionListener(e -> {
            endFrame.dispose();
            startNewGame();
        });
        return playAgainButton;
    }

    /**
     * Metoda wyświetlająca okno dialogowe z wyborem promocji pionka.
     *
     * @return Wybór użytkownika.
     */
    private static int showPromotionDialog() {
        Object[] options = {"Hetman", "Wieża", "Goniec", "Skoczek"};
        return JOptionPane.showOptionDialog(
                chessBoard,
                "Promocja piona. Wybierz figurę:",
                "Promocja pionka",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * Metoda tworząca nową figurę na podstawie wyboru użytkownika.
     *
     * @param selectedPiece Wybrana figura (pionek).
     * @param choice        Wybór użytkownika.
     * @return Nowa figura (promowana figura).
     */
    private static Piece createPromotedPiece(Piece selectedPiece, int choice) {
        Piece newPiece;
        ChessColor color = selectedPiece.getColor();

        newPiece = switch (choice) {
            case 0 -> new Queen(selectedPiece.getX(), selectedPiece.getY(), color);
            case 1 -> new Rook(selectedPiece.getX(), selectedPiece.getY(), color);
            case 2 -> new Bishop(selectedPiece.getX(), selectedPiece.getY(), color);
            case 3 -> new Knight(selectedPiece.getX(), selectedPiece.getY(), color);
            default -> new Queen(selectedPiece.getX(), selectedPiece.getY(), color);
        };

        return newPiece;
    }

    /**
     * Metoda zamykająca wszystkie otwarte okna.
     */
    private static void closeAllWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                window.dispose();
            }
        }
    }
}
