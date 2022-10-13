package me.gracenam.musicsampleapi.domain.artists.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArtistRequest {

    @NotBlank(message = "아티스트 명을 입력해주세요")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "생년월일을 입력해주세요")
    private LocalDate birth;

    private String agency;

    @NotNull(message = "국적을 입력해주세요")
    private String nationality;

    private String introduction;

}
