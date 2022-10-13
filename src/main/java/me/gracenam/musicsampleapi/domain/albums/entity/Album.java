package me.gracenam.musicsampleapi.domain.albums.entity;

import lombok.*;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.response.SoundtrackResponse;
import me.gracenam.musicsampleapi.domain.soundtrack.entity.Soundtrack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private Long id;

    private String artistName;

    private String title;

    private LocalDate releaseDate;

    private String genre;

    private String description;

    private List<Soundtrack> soundtrackList;

    private LocalDateTime registeredDate;

}
