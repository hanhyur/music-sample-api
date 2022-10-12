package me.gracenam.musicsampleapi.domain.artists.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ArtistResponse {

    private Long id;

    private String name;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

    private LocalDateTime registeredDate;

}
