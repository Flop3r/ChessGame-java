/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Utils;

import java.awt.*;

/**
 * Klasa PaintUtils zawiera metody pomocnicze do rysowania kształtów i ustawiania kolorów.
 */
public class PaintUtils {

    /**
     * Rysuje kwadrat na danym obszarze.
     *
     * @param g2d Obiekt grafiki do rysowania.
     * @param x Współrzędna x kwadratu.
     * @param y Współrzędna y kwadratu.
     * @param color Kolor kwadratu.
     * @param alpha Wartość alfa dla koloru.
     */
    public static void drawSquare(Graphics2D g2d, int x, int y, Color color, float alpha) {
        g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 * alpha)));
        g2d.fillRect(x * 64, y * 64, 64, 64);
    }

    /**
     * Rysuje owal na danym obszarze.
     *
     * @param g2d Obiekt grafiki do rysowania.
     * @param x Współrzędna x owalu.
     * @param y Współrzędna y owalu.
     * @param ovalSize Rozmiar owalu.
     * @param color Kolor owalu.
     * @param alpha Wartość alfa dla koloru.
     */
    public static void drawOval(Graphics2D g2d, int x, int y, int ovalSize, Color color, float alpha) {
        int squareSize = 64;
        int ovalX = x * squareSize + (squareSize - ovalSize) / 2;
        int ovalY = y * squareSize + (squareSize - ovalSize) / 2;
        int borderThickness = 4;
        Stroke previousStroke = g2d.getStroke();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawOval(ovalX, ovalY, ovalSize, ovalSize);

        g2d.setStroke(previousStroke);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    }

    /**
     * Wypełnia owal danym kolorem.
     *
     * @param g2d Obiekt grafiki do rysowania.
     * @param x Współrzędna x owalu.
     * @param y Współrzędna y owalu.
     * @param ovalSize Rozmiar owalu.
     * @param color Kolor owalu.
     * @param alpha Wartość alfa dla koloru.
     */
    public static void fillOval(Graphics2D g2d, int x, int y, int ovalSize, Color color, float alpha) {
        int ovalX = x * 64 + (64 - ovalSize) / 2;
        int ovalY = y * 64 + (64 - ovalSize) / 2;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.setColor(color);
        g2d.fillOval(ovalX, ovalY, ovalSize, ovalSize);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    }
}
