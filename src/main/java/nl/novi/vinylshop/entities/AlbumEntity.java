package nl.novi.vinylshop.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    private String title;
    private int releaseYear;

    @ManyToOne
    private GenreEntity genre;

    @ManyToOne
    private PublisherEntity publisher;

    @OneToMany(mappedBy = "album")
    private List<StockEntity> stockItems = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<ArtistEntity> artists = new ArrayList<>();

    public AlbumEntity() {
    }

    public AlbumEntity(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public List<StockEntity> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockEntity> stockItems) {
        this.stockItems = stockItems;
    }

    public List<ArtistEntity> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistEntity> artists) {
        this.artists = artists;
    }
}