package nl.novi.vinylshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
public class PublisherEntity extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<AlbumEntity> albums = new ArrayList<>();

    public PublisherEntity() {
    }

    public PublisherEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumEntity> albums) {
        this.albums = albums;
    }
}