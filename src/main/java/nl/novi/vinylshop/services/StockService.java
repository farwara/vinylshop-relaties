package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.StockRequestDTO;
import nl.novi.vinylshop.dtos.StockResponseDTO;
import nl.novi.vinylshop.entities.AlbumEntity;
import nl.novi.vinylshop.entities.StockEntity;
import nl.novi.vinylshop.mappers.StockDTOMapper;
import nl.novi.vinylshop.mappers.StockEntityMapper;
import nl.novi.vinylshop.repositories.AlbumRepository;
import nl.novi.vinylshop.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final AlbumRepository albumRepository;
    private final StockDTOMapper stockDTOMapper;
    private final StockEntityMapper stockEntityMapper;

    public StockService(StockRepository stockRepository,
                        AlbumRepository albumRepository,
                        StockDTOMapper stockDTOMapper,
                        StockEntityMapper stockEntityMapper) {
        this.stockRepository = stockRepository;
        this.albumRepository = albumRepository;
        this.stockDTOMapper = stockDTOMapper;
        this.stockEntityMapper = stockEntityMapper;
    }

    public List<StockResponseDTO> getAllStockForAlbum(Long albumId) {
        return stockRepository.findByAlbumId(albumId)
                .stream()
                .map(stockDTOMapper::mapToDto)
                .toList();
    }

    public StockResponseDTO getStockById(Long albumId, Long stockId) {
        StockEntity stock = stockRepository.findByIdAndAlbumId(stockId, albumId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + stockId));

        return stockDTOMapper.mapToDto(stock);
    }

    public StockResponseDTO createStock(Long albumId, StockRequestDTO dto) {
        AlbumEntity album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + albumId));

        StockEntity stock = stockEntityMapper.mapToEntity(dto);
        stock.setAlbum(album);

        StockEntity savedStock = stockRepository.save(stock);

        return stockDTOMapper.mapToDto(savedStock);
    }

    public StockResponseDTO updateStock(Long albumId, Long stockId, StockRequestDTO dto) {
        StockEntity stock = stockRepository.findByIdAndAlbumId(stockId, albumId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + stockId));

        stockEntityMapper.updateEntity(stock, dto);

        StockEntity savedStock = stockRepository.save(stock);

        return stockDTOMapper.mapToDto(savedStock);
    }

    public void deleteStock(Long albumId, Long stockId) {
        StockEntity stock = stockRepository.findByIdAndAlbumId(stockId, albumId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + stockId));

        stockRepository.delete(stock);
    }
}