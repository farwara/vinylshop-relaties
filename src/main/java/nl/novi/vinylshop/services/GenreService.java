package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.GenreRequestDTO;
import nl.novi.vinylshop.dtos.GenreResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.mappers.GenreDTOMapper;
import nl.novi.vinylshop.mappers.GenreEntityMapper;
import nl.novi.vinylshop.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreDTOMapper genreDTOMapper;
    private final GenreEntityMapper genreEntityMapper;

    public GenreService(GenreRepository genreRepository,
                        GenreDTOMapper genreDTOMapper,
                        GenreEntityMapper genreEntityMapper) {
        this.genreRepository = genreRepository;
        this.genreDTOMapper = genreDTOMapper;
        this.genreEntityMapper = genreEntityMapper;
    }

    public List<GenreResponseDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genreDTOMapper::mapToDto)
                .toList();
    }

    public GenreResponseDTO getGenreById(Long id) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));

        return genreDTOMapper.mapToDto(genre);
    }

    public GenreResponseDTO createGenre(GenreRequestDTO dto) {
        GenreEntity genre = genreEntityMapper.mapToEntity(dto);
        GenreEntity savedGenre = genreRepository.save(genre);

        return genreDTOMapper.mapToDto(savedGenre);
    }

    public GenreResponseDTO updateGenre(Long id, GenreRequestDTO dto) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));

        genreEntityMapper.updateEntity(genre, dto);

        GenreEntity savedGenre = genreRepository.save(genre);

        return genreDTOMapper.mapToDto(savedGenre);
    }

    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Genre not found with id: " + id);
        }

        genreRepository.deleteById(id);
    }
}