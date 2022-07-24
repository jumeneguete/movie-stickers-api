import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // conexão HTTP
        // String apiUrl = "https://alura-imdb-api.herokuapp.com/movies";
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" +
                        System.getenv("NASA_API_KEY") + "&start_date=2022-07-12&end_date=2022-07-21";

        HTTPClient httpClient = new HTTPClient();
        String json = httpClient.getData(apiUrl);

        // extrair dados (parse) - título, imagem, nota
        ContentExtractor extractor = new NasaContentExtractor();
        // ContentExtractor extractor = new IMDBContentExtractor();
        List<Content> contentList = extractor.extractContent(json);

        // exibir dados
        StickerGenerator generateSticker = new StickerGenerator();

        for (int i = 0; i < 3; i++) {
            Content content = contentList.get(i);

            InputStream inputStream = new URL(content.getImageURL()).openStream(); // openStream, tipo "abra essa conexão e me retorne os bytes que estão lá"

            if (content.getImDbRating() != null) {
                generateSticker.generate(inputStream, content.getTitle(), content.getImDbRating());
            } else {
                generateSticker.generate(inputStream, content.getTitle());
            }      

            System.out.println(i + 1 + ": " + content.getTitle());
            System.out.println();
        }

    }
}
