package me.gracenam.musicsampleapi.domain.artists.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArtistUpdateRequest {

    private String name;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

}
