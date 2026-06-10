package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.ArtistEntity;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.mappers.AlbumDTOMapper;
import nl.novi.vinylshop.mappers.AlbumEntityMapper;
import nl.novi.vinylshop.repositories.AlbumRepository;
import nl.novi.vinylshop.repositories.ArtistRepository;
import nl.novi.vinylshop.repositories.GenreRepository;
import nl.novi.vinylshop.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final ArtistRepository artistRepository;
    private final AlbumDTOMapper albumDTOMapper;
    private final AlbumEntityMapper albumEntityMapper;

    public AlbumService(AlbumRepository albumRepository,
                        GenreRepository genreRepository,
                        PublisherRepository publisherRepository,
                        ArtistRepository artistRepository,
                        AlbumDTOMapper albumDTOMapper,
                        AlbumEntityMapper albumEntityMapper) {
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.artistRepository = artistRepository;
        this.albumDTOMapper = albumDTOMapper;
        this.albumEntityMapper = albumEntityMapper;
    }

    public List<AlbumResponseDTO> getAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(albumDTOMapper::mapToDto)
                .toList();
    }

    public AlbumResponseDTO getAlbumById(Long id) {
        AlbumEntity album = getAlbumEntity(id);
        return albumDTOMapper.mapToDto(album);
    }

    public AlbumResponseDTO createAlbum(AlbumRequestDTO dto) {
        AlbumEntity album = albumEntityMapper.mapToEntity(dto);

        if (dto.getGenreId() != null) {
            GenreEntity genre = getGenreEntity(dto.getGenreId());
            album.setGenre(genre);
        }

        if (dto.getPublisherId() != null) {
            PublisherEntity publisher = getPublisherEntity(dto.getPublisherId());
            album.setPublisher(publisher);
        }

        AlbumEntity savedAlbum = albumRepository.save(album);

        return albumDTOMapper.mapToDto(savedAlbum);
    }

    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO dto) {
        AlbumEntity album = getAlbumEntity(id);

        albumEntityMapper.updateEntity(album, dto);

        if (dto.getGenreId() != null) {
            GenreEntity genre = getGenreEntity(dto.getGenreId());
            album.setGenre(genre);
        } else {
            album.setGenre(null);
        }

        if (dto.getPublisherId() != null) {
            PublisherEntity publisher = getPublisherEntity(dto.getPublisherId());
            album.setPublisher(publisher);
        } else {
            album.setPublisher(null);
        }

        AlbumEntity savedAlbum = albumRepository.save(album);

        return albumDTOMapper.mapToDto(savedAlbum);
    }

    public void deleteAlbum(Long id) {
        AlbumEntity album = getAlbumEntity(id);

        if (!album.getStockItems().isEmpty()) {
            throw new RuntimeException("Cannot delete album because it still has stock");
        }

        albumRepository.delete(album);
    }

    public void linkArtist(Long albumId, Long artistId) {
        AlbumEntity album = getAlbumEntity(albumId);

        ArtistEntity artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + artistId));

        if (!album.getArtists().contains(artist)) {
            album.getArtists().add(artist);
        }

        albumRepository.save(album);
    }

    public void unlinkArtist(Long albumId, Long artistId) {
        AlbumEntity album = getAlbumEntity(albumId);

        ArtistEntity artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + artistId));

        album.getArtists().remove(artist);

        albumRepository.save(album);
    }

    public List<AlbumResponseDTO> getAlbumsWithStock(Boolean stock) {
        List<AlbumEntity> albums;

        if (stock) {
            albums = albumRepository.findByStockItemsNotEmpty();
        } else {
            albums = albumRepository.findByStockItemsEmpty();
        }

        return albums.stream()
                .map(albumDTOMapper::mapToDto)
                .toList();
    }

    private AlbumEntity getAlbumEntity(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + id));
    }

    private GenreEntity getGenreEntity(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }

    private PublisherEntity getPublisherEntity(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
    }
}