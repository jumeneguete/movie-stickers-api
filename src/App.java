import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // conexão HTTP
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair dados (parse) - título, imagem, nota
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        //exibir dados
        for(int i = 0; i < movieList.size(); i++){
             int position = i + 1;

            System.out.println("\u001b[35m" + position + ": " + "\u001b[1m \u001b[45m" + 
                                movieList.get(i).get("title") + "\u001b[m");

            for ( int j = 1; j <= (int) Double.parseDouble(movieList.get(i).get("imDbRating")); j++){
                System.out.print("⭐");
            }                    

            System.out.println();

            System.out.println("\u001b[35m Nota: " + "\u001b[1m" + movieList.get(i).get("imDbRating") + "\u001b[m");
            System.out.println("\u001b[34m Link da imagem: " + "\u001b[1m"  + movieList.get(i).get("image") + "\u001b[m");
            
            System.out.println();
        }
        
    }
}
