package me.gracenam.musicsampleapi.domain.artists.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.service.ArtistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/truncate.sql")
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArtistService artistService;

    @Test
    @DisplayName("등록 테스트")
    public void saveArtistDataTest() throws Exception {
        ArtistRequest artist = ArtistRequest.builder()
                .name("아이유")
                .birth(LocalDate.of(1993, 5, 17))
                .agency("EDAM 엔터")
                .nationality("한국")
                .introduction("너와 나 하나되는 음악")
                .build();

        mockMvc.perform(post("/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("아이유"));
    }

    @Test
    @DisplayName("등록 시 잘못된 값을 입력했을 때")
    public void saveArtistDataTest_Empty() throws Exception {
        ArtistRequest artist = ArtistRequest.builder()
                .name("")
                .birth(LocalDate.of(1993, 5, 17))
                .agency("EDAM 엔터")
                .nationality("한국")
                .introduction("너와 나 하나되는 음악")
                .build();

        mockMvc.perform(post("/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artist)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private ArtistResponse generateArtist(int index) {
        ArtistRequest artistRequest = buildArtist(index);

        return artistService.saveArtistData(artistRequest);
    }

    private static ArtistRequest buildArtist(int index) {
        ArtistRequest artist = ArtistRequest.builder()
                .name("테스트 아티스트 " + index)
                .birth(LocalDate.of(2022, 10, 12))
                .agency("나이스엔터")
                .nationality("한국")
                .introduction("오늘도! 나이스데이!")
                .build();

        return artist;
    }

    @Test
    @DisplayName("목록 조회")
    public void findAllTest() throws Exception {
        IntStream.range(0, 100).forEach(this::generateArtist);

        mockMvc.perform(get("/artist")
                        .param("pageNum", "1")
                        .param("pageSize", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.total").value(100))
        ;
    }

    @Test
    @DisplayName("상세 조회")
    public void findArtistTest() throws Exception {
        IntStream.range(0, 100).forEach(this::generateArtist);

        mockMvc.perform(get("/artist/{id}", 20))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("수정 성공")
    public void updateArtistInfoTest() throws Exception {
        ArtistResponse artist = generateArtist(200);

        ArtistRequest dto = ArtistRequest.builder()
                .name("르세라핌")
                .agency("쏘스뮤직")
                .birth(LocalDate.of(2022, 7, 22))
                .nationality("다국적")
                .introduction("카즈하 이뻐요")
                .build();

        mockMvc.perform(patch("/artist/{id}", artist.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("수정하려는 아티스트가 존재하지 않을 때")
    public void updateArtistInfoTest_NotFound() throws Exception {
        mockMvc.perform(patch("/artist/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }


    @Test
    @DisplayName("삭제 성공")
    public void deleteArtistTest() throws Exception {
        ArtistResponse artist = generateArtist(1);

        mockMvc.perform(delete("/artist/{id}", artist.getId()))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("삭제하려는 아티스트가 존재하지 않을 때")
    public void deleteArtistTest_BadRequest() throws Exception {
        mockMvc.perform(delete("/artist/200"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

}