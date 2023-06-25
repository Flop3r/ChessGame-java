/*
 * Franciszek Przeliorz
 * Uniwersytet Wrocławski
 * Informatyka
 */
package GameEngine.Pieces;

import GameEngine.Board.Board;
import GameEngine.Moves.Move;
import GameEngine.Utils.ChessColor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Klasa abstrakcyjna reprezentująca figurę na szachownicy.
 */
public abstract class Piece implements Cloneable {

    @Getter
    @Setter
    protected int x, y;
    @Getter
    protected ChessColor color;
    @Getter
    protected LinkedList<Move> potentialMoves;
    @Getter
    protected BufferedImage image;

    /**
     * Konstruktor klasy Piece.
     *
     * @param x          współrzędna x figury na szachownicy.
     * @param y          współrzędna y figury na szachownicy.
     * @param color      kolor figury.
     * @param img_source obraz reprezentujący figurę.
     */
    public Piece(int x, int y, ChessColor color, BufferedImage img_source) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.image = img_source;
    }

    /**
     * Metoda służąca do przesunięcia figury.
     *
     * @param move obiekt klasy Move, zawierający dane o ruchu do wykonania.
     */
    public void movePiece(Move move) {
        this.x = move.getX();
        this.y = move.getY();
    }

    /**
     * Metoda abstrakcyjna, generująca listę potencjalnych ruchów dla danej figury.
     *
     * @param board aktualny stan szachownicy.
     * @return lista potencjalnych ruchów.
     */
    public abstract LinkedList<Move> generatePotentialMoves(Board board);

    /**
     * Przesłonięta metoda z interfejsu Cloneable, umożliwiająca kopiowanie obiektów klasy Piece.
     *
     * @return nowy obiekt będący kopią bieżącego.
     */
    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }
}
