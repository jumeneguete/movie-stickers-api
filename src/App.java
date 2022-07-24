import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // conexão HTTP
        // String apiUrl = "https://alura-imdb-api.herokuapp.com/movies";
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + System.getenv("NASA_API_KEY") + "&start_date=2022-07-12&end_date=2022-07-21";
        
        HTTPClient httpClient = new HTTPClient();
        String json = httpClient.getData(apiUrl);

        // extrair dados (parse) - título, imagem, nota
        JsonParser parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(json);

        // exibir dados
        StickerGenerator generateSticker = new StickerGenerator();
  
        for (int i = 0; i < 2; i++) {
            // InputStream lendo de uma URL
            String imageUrl = contentList.get(i).get("url")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            InputStream inputStream = new URL(imageUrl).openStream(); //openStream, tipo "abra essa conexão e me retorne os bytes que estão lá"
            String title = contentList.get(i).get("title");
            // Double imDbRating = Double.parseDouble(contentList.get(i).get("imDbRating"));

            generateSticker.generate(inputStream, title);

            System.out.println(i + 1 + ": " + title);
            System.out.println();
        }

    }
}

