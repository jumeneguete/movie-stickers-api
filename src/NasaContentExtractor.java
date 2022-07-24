import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> extractContent(String json) {

        // pegar todos os atributos que vem do body numa lista
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        // cria uma lista vazia que vai ser populada com os conteudos que precisamos
        List<Content> contentList = new ArrayList<>();

        // popular a lista de conteudos
        // percorre a lista de atributos para ir criando os conteúdos
        for (Map<String, String> atribute : attributesList) {
            String imageUrl = atribute.get("url");
            String title = atribute.get("title");

            // depois de pegar o titulo e a url da lista de atributos de um atributo, eu crio um conteudo
            Content content = new Content(title, imageUrl);

            // adiciono esse conteudo na lista que começou vazia
            contentList.add(content);

        }

        return contentList;
    }
}
