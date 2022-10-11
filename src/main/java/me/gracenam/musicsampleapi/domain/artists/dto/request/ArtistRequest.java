package me.gracenam.musicsampleapi.domain.artists.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class ArtistRequest {

    private String artistName;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

}
