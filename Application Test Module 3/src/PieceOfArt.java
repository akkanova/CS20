public class PieceOfArt {
    private String[] media;
    private String artist;
    private String title;
    private double price;
    private int year;

    public PieceOfArt(
        String artist,
        String title,
        int year,
        double price,
        String[] media
    ) {
        // Prevent the field from getting set to a negative
        this.price = Math.abs(price);
        this.artist = artist;
        this.title = title;
        this.media = media;
        this.year = year;
    }

    // Setters
    public void setPrice(double price) { this.price = Math.abs(price); }
    public void setArtistName(String artist) { this.artist = artist; }
    public void setMedia(String[] media) { this.media = media; }
    public void setYearCreated(int year) { this.year = year; }
    public void setTitle(String title) { this.title = title; }

    // Getters
    public String getArtistName() { return artist; }
    public int getYearCreated() { return year; }
    public String[] getMedia() { return media; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format(
            "%s by %s (%d) $%.2f\n%s",
            title, artist,
            year, price,
            String.join(",", media)
        );
    }
}
