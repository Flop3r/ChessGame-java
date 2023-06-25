/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine;

import GameEngine.Pieces.Pawn;

/**
 * Interfejs reprezentujący nasłuchiwacz zdarzeń promocji piona.
 */
public interface PromotionListener {

    /**
     * Metoda wywoływana, gdy zachodzi sytuacja, w której pion może być promowany.
     *
     * @param pawn pion do promocji.
     */
    void onPromotionRequested(Pawn pawn);
}
