package me.gracenam.musicsampleapi.domain.albums.entity;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Album {

    private Long albumId;

    private String artistName;

    private String albumTitle;

    private LocalDate releaseDate;

    private String genre;

    private String description;

    private List<Soundtrack> soundtracks;

    private LocalDateTime registeredDate;

}
