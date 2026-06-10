package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.AlbumRequestDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumEntityMapper {

    public AlbumEntity mapToEntity(AlbumRequestDTO dto) {
        AlbumEntity entity = new AlbumEntity();
        entity.setTitle(dto.getTitle());
        entity.setReleaseYear(dto.getReleaseYear());

        return entity;
    }

    public void updateEntity(AlbumEntity entity, AlbumRequestDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setReleaseYear(dto.getReleaseYear());
    }
}