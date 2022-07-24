import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements ContentExtractor {
    public List<Content> extractContent(String json) {

        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> atribute : attributesList) {
            String imageUrl = atribute.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String title = atribute.get("title");
            Double imDbRating = Double.parseDouble(atribute.get("imDbRating"));

            Content content = new Content(title, imageUrl, imDbRating);

            contentList.add(content);

        }

        return contentList;
    }
}
