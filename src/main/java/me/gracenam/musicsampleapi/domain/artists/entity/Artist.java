package me.gracenam.musicsampleapi.domain.artists.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private Long id;

    private String name;

    private LocalDate birth;

    private String agency;

    private String nationality;

    private String introduction;

    private LocalDateTime registeredDate;

}

