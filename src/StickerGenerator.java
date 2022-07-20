import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void generate() throws IOException {
        
        // leitura da imagem original
        BufferedImage originalImage = ImageIO.read(new File("./entrada/filme.jpg"));

        // cria nova imagem com transparencia em memoria
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 300;
        BufferedImage transparentImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copia a imagem original para a nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) transparentImage.getGraphics(); //criar o graphics a partir da imagem transparente
        graphics.drawImage(originalImage, 0, 0, null); // vai desenhar a imagem original sobre a transparente

        //configurar fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 200);
        graphics.setColor(Color.MAGENTA);
        graphics.setFont(font);

        // escreve frase na nova imagem
        String text = "TOPZERA";
        graphics.drawString(text,  width/2 - text.length() * 50, newHeight - 90);

        // escreve a nova imagem em um arquivo
        File outputDirectory = new File("./saida");
        if ( !outputDirectory.exists() ){
            outputDirectory.mkdirs();
        }
        File sticker = new File(outputDirectory + "/figurinha.png");
        ImageIO.write(transparentImage, "png", sticker);

    }

    public static void main(String[] args) throws IOException {
        StickerGenerator stickerGenerator = new StickerGenerator();
        stickerGenerator.generate();
    }
    
}
