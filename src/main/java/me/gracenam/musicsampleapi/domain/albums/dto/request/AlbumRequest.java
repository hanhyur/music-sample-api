package me.gracenam.musicsampleapi.domain.albums.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlbumRequest {

    @NotBlank(message = "아티스트를 선택해주세요")
    private String artistName;

    @NotBlank(message = "앨범명을 입력해주세요")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "발매일을 입력해주세요")
    private LocalDate releaseDate;

    private String genre;

    private String description;

}
