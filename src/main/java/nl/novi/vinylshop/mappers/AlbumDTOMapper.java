package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.AlbumResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumDTOMapper {

    private final GenreDTOMapper genreDTOMapper;
    private final PublisherDTOMapper publisherDTOMapper;

    public AlbumDTOMapper(GenreDTOMapper genreDTOMapper,
                          PublisherDTOMapper publisherDTOMapper) {
        this.genreDTOMapper = genreDTOMapper;
        this.publisherDTOMapper = publisherDTOMapper;
    }

    public AlbumResponseDTO mapToDto(AlbumEntity entity) {
        if (entity == null) {
            return null;
        }

        AlbumResponseDTO dto = new AlbumResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setReleaseYear(entity.getReleaseYear());

        dto.setGenre(genreDTOMapper.mapToDto(entity.getGenre()));
        dto.setPublisher(publisherDTOMapper.mapToDto(entity.getPublisher()));

        return dto;
    }
}