package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.ArtistResponseDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import nl.novi.vinylshop.mappers.ArtistDTOMapper;
import nl.novi.vinylshop.mappers.ArtistEntityMapper;
import nl.novi.vinylshop.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistDTOMapper artistDTOMapper;
    private final ArtistEntityMapper artistEntityMapper;

    public ArtistService(ArtistRepository artistRepository,
                         ArtistDTOMapper artistDTOMapper,
                         ArtistEntityMapper artistEntityMapper) {
        this.artistRepository = artistRepository;
        this.artistDTOMapper = artistDTOMapper;
        this.artistEntityMapper = artistEntityMapper;
    }

    public List<ArtistResponseDTO> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(artistDTOMapper::mapToDto)
                .toList();
    }

    public ArtistResponseDTO getArtistById(Long id) {
        ArtistEntity artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));

        return artistDTOMapper.mapToDto(artist);
    }

    public ArtistResponseDTO createArtist(ArtistRequestDTO dto) {
        ArtistEntity artist = artistEntityMapper.mapToEntity(dto);
        ArtistEntity savedArtist = artistRepository.save(artist);

        return artistDTOMapper.mapToDto(savedArtist);
    }

    public ArtistResponseDTO updateArtist(Long id, ArtistRequestDTO dto) {
        ArtistEntity artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));

        artistEntityMapper.updateEntity(artist, dto);

        ArtistEntity savedArtist = artistRepository.save(artist);

        return artistDTOMapper.mapToDto(savedArtist);
    }

    public void deleteArtist(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new RuntimeException("Artist not found with id: " + id);
        }

        artistRepository.deleteById(id);
    }

    public List<ArtistResponseDTO> getArtistsForAlbum(Long albumId) {
        return artistRepository.findArtistsByAlbumsId(albumId)
                .stream()
                .map(artistDTOMapper::mapToDto)
                .toList();
    }
}