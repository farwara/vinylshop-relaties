package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.StockResponseDTO;
import nl.novi.vinylshop.entities.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockDTOMapper {

    public StockResponseDTO mapToDto(StockEntity entity) {
        if (entity == null) {
            return null;
        }

        StockResponseDTO dto = new StockResponseDTO();
        dto.setId(entity.getId());
        dto.setCondition(entity.getCondition());
        dto.setPrice(entity.getPrice());

        return dto;
    }
}