package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.AlbumRequestDTO;
import nl.novi.vinylshop.dtos.AlbumResponseDTO;
import nl.novi.vinylshop.dtos.ArtistResponseDTO;
import nl.novi.vinylshop.services.AlbumService;
import nl.novi.vinylshop.services.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;

    public AlbumController(AlbumService albumService,
                           ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAllAlbums(
            @RequestParam(required = false) Boolean stock) {

        if (stock != null) {
            return ResponseEntity.ok(albumService.getAlbumsWithStock(stock));
        }

        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbum(
            @Valid @RequestBody AlbumRequestDTO dto) {

        return ResponseEntity.ok(albumService.createAlbum(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(
            @PathVariable Long id,
            @Valid @RequestBody AlbumRequestDTO dto) {

        return ResponseEntity.ok(albumService.updateAlbum(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> linkArtist(
            @PathVariable Long albumId,
            @PathVariable Long artistId) {

        albumService.linkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> unlinkArtist(
            @PathVariable Long albumId,
            @PathVariable Long artistId) {

        albumService.unlinkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/artists")
    public ResponseEntity<List<ArtistResponseDTO>> getArtistsForAlbum(
            @PathVariable Long id) {

        return ResponseEntity.ok(artistService.getArtistsForAlbum(id));
    }
}