package me.gracenam.musicsampleapi.domain.artists.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.service.ArtistService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity getArtists(Pageable pageable) {
        return ResponseEntity.ok(artistService.findAllArtist(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity getArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.findArtistById(id));
    }
    
    @PostMapping
    public ResponseEntity saveArtist(ArtistRequest dto) {
        return ResponseEntity.ok(artistService.saveArtistData(dto));
    }

    @GetMapping("/search")
    public ResponseEntity searchArtist(Pageable pageable,
                                 @RequestParam @NotBlank(message = "검색할 내용을 입력해주세요")
                                 String word) {
        return ResponseEntity.ok(artistService.searchArtistByName(pageable, word));
    }


}
