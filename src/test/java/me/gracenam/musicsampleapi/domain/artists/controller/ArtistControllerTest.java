package me.gracenam.musicsampleapi.domain.artists.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArtistService artistService;

    @Test
    @DisplayName("아티스트 등록 테스트")
    public void saveArtistDataTest() throws Exception {
        ArtistRequest inputDto = getArtistDto();

        mockMvc.perform(post("/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("아이유"));
    }

    @Test
    @DisplayName("잘못된 값을 입력했을 때")
    public void saveArtistDataTest_Empty() throws Exception {
        ArtistRequest inputDto = getArtistDto();

        inputDto.setName("");

        mockMvc.perform(post("/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Nested
    @DisplayName("목록 조회, 검색")
    class findArtistListAndSearch {

        @BeforeEach
        void setList() {
            for (int i = 0; i < 100; i++) {
                saveArtists(i);
            }
        }

        @Test
        @DisplayName("목록 조회")
        public void findAllTest() throws Exception {
            mockMvc.perform(get("/artist")
                            .param("pageNum", "1")
                            .param("pageSize", "2"))
                    .andDo(print())
                    .andExpect(jsonPath("page.total").value(100))
                    .andExpect(status().isOk())
            ;
        }

    }

    @Nested
    @DisplayName("아티스트 수정 테스트")
    class updateArtistTest {

        @Test
        @DisplayName("수정 성공")
        public void updateArtistInfoTest() throws Exception {
            Long id = saveArtists(1).getId();

            ArtistRequest dto = ArtistRequest.builder()
                    .name("르세라핌")
                    .birth(LocalDate.of(2022, 7, 22))
                    .agency("쏘스뮤작")
                    .nationality("다국적")
                    .introduction("하이브 소속")
                    .build();

            mockMvc.perform(patch("/artist/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Validation")
        public void updateArtistInfoTest_Validation() throws Exception {
            Long id = saveArtists(1).getId();

            ArtistRequest dto = ArtistRequest.builder()
                    .name("")
                    .agency("쏘스뮤작")
                    .nationality("다국적")
                    .introduction("카즈하 이뻐요")
                    .build();

            mockMvc.perform(patch("/artist/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
            ;
        }

        @Test
        @DisplayName("아티스트가 존재하지 않을 때")
        public void updateArtistInfoTest_NotFound() throws Exception {

            Long id = 100L;

            ArtistRequest dto = ArtistRequest.builder()
                    .name("르세라핌")
                    .agency("쏘스뮤직")
                    .birth(LocalDate.of(2022, 7, 22))
                    .nationality("다국적")
                    .introduction("카즈하 이뻐요")
                    .build();

            mockMvc.perform(patch("/artist/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
            ;
        }

    }

    @Nested
    @DisplayName("아티스트 삭제 테스트")
    class deleteArtistTest{

        @Test
        @DisplayName("성공 200")
        public void deleteArtistTest() throws Exception {
            Long id = saveArtists(1).getId();

            mockMvc.perform(delete("/artist/{id}", id))
                    .andDo(print())
                    .andExpect(status().isOk())
            ;
        }

        @Test
        @DisplayName("아티스트가 존재하지 않을 때")
        public void deleteArtistTest_BadRequest() throws Exception {
            Long id = 100L;

            mockMvc.perform(delete("/artist/{id}", id))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
            ;
        }

    }

    private static ArtistRequest getArtistDto() {
        ArtistRequest artistRequest = ArtistRequest.builder()
                .name("아이유")
                .birth(LocalDate.of(1993, 5, 19))
                .agency("EDAM엔터")
                .nationality("한국")
                .introduction("너와 내가 음악으로 하나가 된다.")
                .build();

        return artistRequest;
    }

    private ArtistResponse saveArtists(int i) {
        ArtistRequest artistRequest = ArtistRequest.builder()
                .name("테스트 아티스트 " + i)
                .birth(LocalDate.of(2022, 10, 12))
                .agency("나이스엔터")
                .nationality("한국")
                .introduction("오늘도! 나이스데이!")
                .build();

        return artistService.saveArtistData(artistRequest);
    }

}