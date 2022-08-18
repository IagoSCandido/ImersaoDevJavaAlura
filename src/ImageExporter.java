import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageExporter {
    public void create(InputStream inputStream, String fileName) throws IOException {
        // Leitura da imagem
        BufferedImage image = ImageIO.read(inputStream);
        // definição do formato da imagem
        ImageIO.write(image, "png", new File(fileName));
    }
}