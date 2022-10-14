package me.gracenam.musicsampleapi.domain.albums.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDateTime registeredDate;

}
