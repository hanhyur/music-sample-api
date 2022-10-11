package me.gracenam.musicsampleapi.domain.artists.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private Long artistId;

    private String artistName;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

    private LocalDateTime registeredDate;

    private String username;

}
