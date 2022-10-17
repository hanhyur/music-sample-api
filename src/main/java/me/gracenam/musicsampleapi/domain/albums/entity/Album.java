package me.gracenam.musicsampleapi.domain.albums.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"soundtrackRequests"})
public class Album {

    private Long id;

    private String artistName;

    private String title;

    private LocalDate releaseDate;

    private String genre;

    private String description;

    private LocalDateTime registeredDate;

}
