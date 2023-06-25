/*
 * Franciszek Przeliorz
 * Uniwersytet Wrocławski
 * Informatyka
 */
package GameEngine.Utils;

import java.awt.image.BufferedImage;

/**
 * Klasa GameAssets zawiera stałe obiekty BufferedImage reprezentujące różne elementy gry.
 * Wszystkie obrazy są odczytywane z plików PNG znajdujących się w folderze "assets".
 */
public class GameAssets {

    /**
     * Obiekt BufferedImage reprezentujący białą pionek.
     */
    public final static BufferedImage white_pawn = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/white-pawn.png"));

    /**
     * Obiekt BufferedImage reprezentujący czarną pionek.
     */
    public final static BufferedImage black_pawn = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/black-pawn.png"));

    /**
     * Obiekt BufferedImage reprezentujący białego króla.
     */
    public final static BufferedImage white_king = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/white-king.png"));

    /**
     * Obiekt BufferedImage reprezentujący czarnego króla.
     */
    public final static BufferedImage black_king = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/black-king.png"));

    /**
     * Obiekt BufferedImage reprezentujący białą królową.
     */
    public final static BufferedImage white_queen = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/white-queen.png"));

    /**
     * Obiekt BufferedImage reprezentujący czarną królową.
     */
    public final static BufferedImage black_queen = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/black-queen.png"));

    /**
     * Obiekt BufferedImage reprezentujący białego gońca.
     */
    public final static BufferedImage white_bishop = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/white-bishop.png"));

    /**
     * Obiekt BufferedImage reprezentujący czarnego gońca.
     */
    public final static BufferedImage black_bishop = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/black-bishop.png"));

    /**
     * Obiekt BufferedImage reprezentujący białego skoczka.
     */
    public final static BufferedImage white_knight = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/white-knight.png"));

    /**
     * Obiekt BufferedImage reprezentujący czarnego skoczka.
     */
    public final static BufferedImage black_knight = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/black-knight.png"));

    /**
     * Obiekt BufferedImage reprezentujący białą wieżę.
     */
    public final static BufferedImage white_rook = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/white-rook.png"));

    /**
     * Obiekt BufferedImage reprezentujący czarną wieżę.
     */
    public final static BufferedImage black_rook = ImageUtils.readBufferedImage(
            GameAssets.class.getResourceAsStream("/assets/black-rook.png"));
}
