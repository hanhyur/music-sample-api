package me.gracenam.musicsampleapi.domain.artists.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class ArtistRequest {

    private String name;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

}
