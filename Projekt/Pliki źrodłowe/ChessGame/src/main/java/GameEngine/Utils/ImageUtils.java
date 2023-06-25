/*
 * Franciszek Przeliorz
 * Uniwerystet Wrocławski
 * Informatyka
 */
package GameEngine.Utils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Klasa narzędziowa zawierająca przydatne funkcje pomocnicze.
 */
public class ImageUtils {

    /**
     * Wczytuje obraz z danego strumienia wejściowego.
     *
     * @param inputStream strumień wejściowy, z którego obraz ma być wczytany
     * @return BufferedImage reprezentujący wczytany obraz, lub null, jeśli nie można wczytać obrazu
     */
    public static BufferedImage readBufferedImage(InputStream inputStream) {
        try {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
