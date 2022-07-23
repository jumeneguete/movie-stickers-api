import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        // conexão HTTP
        String ApiUrl = "https://alura-imdb-api.herokuapp.com/movies";
        URI uri = URI.create(ApiUrl);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair dados (parse) - título, imagem, nota
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // exibir dados
        StickerGenerator generateSticker = new StickerGenerator();
  
        for (int i = 0; i < 40; i++) {
            // InputStream lendo de uma URL
            String imageUrl = movieList.get(i).get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            InputStream inputStream = new URL(imageUrl).openStream(); //openStream, tipo "abra essa conexão e me retorne os bytes que estão lá"
            String title = movieList.get(i).get("title");
            Double imDbRating = Double.parseDouble(movieList.get(i).get("imDbRating"));

            generateSticker.generate(inputStream, title, imDbRating);

            System.out.println(i + 1 + ": " + title);
            System.out.println();
        }

    }
}

