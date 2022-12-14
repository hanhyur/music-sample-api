package me.gracenam.musicsampleapi.domain.albums.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.gracenam.musicsampleapi.domain.soundtrack.entity.Soundtrack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class AlbumResponse {

    private Long id;

    private String artistName;

    private String title;

    private LocalDate releaseDate;

    private String genre;

    private String description;

    private LocalDateTime registeredDate;

}
