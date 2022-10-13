package me.gracenam.musicsampleapi.domain.albums.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumSearchParam;
import me.gracenam.musicsampleapi.domain.albums.service.AlbumService;
import me.gracenam.musicsampleapi.global.commons.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity getAlbums(AlbumSearchParam pageable) {
        PageResponse<AlbumResponse> result = albumService.findAllAlbum(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumService);
    }

    @PostMapping
    public ResponseEntity saveAlbum(@RequestBody @Valid AlbumRequest dto,
                                    BindingResult result) {
        if (result.hasErrors()) {
            throw new AlbumValidationException(result);
        }

        return ResponseEntity.ok(albumService);
    }

}
