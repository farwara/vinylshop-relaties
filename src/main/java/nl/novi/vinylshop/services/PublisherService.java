package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.PublisherResponseDTO;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.mappers.PublisherDTOMapper;
import nl.novi.vinylshop.mappers.PublisherEntityMapper;
import nl.novi.vinylshop.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherDTOMapper publisherDTOMapper;
    private final PublisherEntityMapper publisherEntityMapper;

    public PublisherService(PublisherRepository publisherRepository,
                            PublisherDTOMapper publisherDTOMapper,
                            PublisherEntityMapper publisherEntityMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherDTOMapper = publisherDTOMapper;
        this.publisherEntityMapper = publisherEntityMapper;
    }

    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisherDTOMapper::mapToDto)
                .toList();
    }

    public PublisherResponseDTO getPublisherById(Long id) {
        PublisherEntity publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));

        return publisherDTOMapper.mapToDto(publisher);
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO dto) {
        PublisherEntity publisher = publisherEntityMapper.mapToEntity(dto);
        PublisherEntity savedPublisher = publisherRepository.save(publisher);

        return publisherDTOMapper.mapToDto(savedPublisher);
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO dto) {
        PublisherEntity publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));

        publisherEntityMapper.updateEntity(publisher, dto);

        PublisherEntity savedPublisher = publisherRepository.save(publisher);

        return publisherDTOMapper.mapToDto(savedPublisher);
    }

    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new RuntimeException("Publisher not found with id: " + id);
        }

        publisherRepository.deleteById(id);
    }
}