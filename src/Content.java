public class Content {

    private final String title; //final = nao pode mais mudar depois de criar pelo construtor
    private final String ImageURL;
    private Double imDbRating;

    public Content(String title, String imageURL, Double imDbRating) {
        this.title = title;
        this.ImageURL = imageURL;
        this.imDbRating = imDbRating;
    }

    public Content(String title, String imageURL) {
        this.title = title;
        this.ImageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }
    public String getImageURL() {
        return ImageURL;
    }

    public Double getImDbRating() {
        return imDbRating;
    }

}
