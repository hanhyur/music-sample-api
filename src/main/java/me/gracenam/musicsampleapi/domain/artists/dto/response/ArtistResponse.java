package me.gracenam.musicsampleapi.domain.artists.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class ArtistResponse {

    private Long artistId;

    private String artistName;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

    private LocalDateTime registeredDate;

}
