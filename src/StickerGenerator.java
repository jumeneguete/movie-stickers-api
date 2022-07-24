import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerGenerator {

    public void generate(InputStream inputStream, String title) throws IOException {

        BufferedImage originalImage = readImageFromInputstream(inputStream);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 300;
        BufferedImage transparentImage = generateTransparentImage(originalImage, width, newHeight);

        Graphics2D graphics = addOriginalImagetoTransparentImage(originalImage, transparentImage);

        String text = "SHOW";
        writeTextToImage(graphics, text, width, newHeight);

        addImageToOutputFile(title, transparentImage);

    }

    private BufferedImage readImageFromInputstream(InputStream inputStream) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputStream);
        return originalImage;
    }

    private BufferedImage generateTransparentImage(BufferedImage originalImage, int width, int height) {
        BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        return transparentImage;
    }

    private Graphics2D addOriginalImagetoTransparentImage(BufferedImage originalImage, BufferedImage transparentImage) {
        Graphics2D graphics = (Graphics2D) transparentImage.getGraphics(); // criar o graphics a partir da imagem
                                                                           // transparente
        graphics.drawImage(originalImage, 0, 0, null); // vai desenhar a imagem original sobre a transparente
        return graphics;
    }

    private void writeTextToImage(Graphics2D graphics, String text, int width, int height) {
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 120);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(font);

        int textWidth = graphics.getFontMetrics().stringWidth(text);
        int center = width / 2 - textWidth / 2;
        graphics.drawString(text, center, height - 90);
    }

    private void addImageToOutputFile(String title, BufferedImage transparentImage) throws IOException {
        File outputDirectory = new File("./saida");
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        String imgTitle = title.replaceAll("\\s+", "");
        File sticker = new File(outputDirectory + "/" + imgTitle + ".png");
        ImageIO.write(transparentImage, "png", sticker);
    }

}
