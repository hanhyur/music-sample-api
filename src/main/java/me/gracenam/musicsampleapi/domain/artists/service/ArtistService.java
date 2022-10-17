package me.gracenam.musicsampleapi.domain.artists.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistUpdateRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistSearchParam;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.artists.exception.ArtistNotFoundException;
import me.gracenam.musicsampleapi.domain.artists.mapper.ArtistMapper;
import me.gracenam.musicsampleapi.global.commons.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {

    private final ArtistMapper artistMapper;

    private final ModelMapper modelMapper;

    public ArtistResponse findArtistById(Long id) {
        Artist findArtist = artistMapper.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(Long.toString(id)));

        ArtistResponse artistResponse = ArtistResponse.builder()
                .id(findArtist.getId())
                .name(findArtist.getName())
                .birth(findArtist.getBirth())
                .agency(findArtist.getAgency())
                .nationality(findArtist.getNationality())
                .introduction(findArtist.getIntroduction())
                .registeredDate(findArtist.getRegisteredDate())
                .build();

        return artistResponse;
    }

    public PageResponse<ArtistResponse> findAllArtist(ArtistSearchParam param) {
        param.searchParamValidate();

        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        PageInfo<ArtistResponse> pageInfo = PageInfo.of(artistMapper.findAll(param));

        return PageResponse.<ArtistResponse>builder()
                .param(param)
                .page(pageInfo)
                .build();
    }

    @Transactional
    public ArtistResponse saveArtistData(ArtistRequest dto) {
        Artist artist = modelMapper.map(dto, Artist.class);

        log.info("artist = {}", artist);

        artistMapper.save(artist);

        Artist result = artistMapper.findById(artist.getId())
                .orElseThrow(() -> new ArtistNotFoundException(Long.toString(artist.getId())));

        ArtistResponse artistResponse = ArtistResponse.builder()
                .id(result.getId())
                .name(result.getName())
                .birth(result.getBirth())
                .agency(result.getAgency())
                .nationality(result.getNationality())
                .introduction(result.getIntroduction())
                .registeredDate(result.getRegisteredDate())
                .build();

        return artistResponse;
    }

    @Transactional
    public ArtistResponse updateArtistInfo(Long id, ArtistUpdateRequest dto) {
        artistMapper.update(id, dto);

        Artist result = artistMapper.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(Long.toString(id)));

        ArtistResponse artistResponse = ArtistResponse.builder()
                .id(result.getId())
                .name(result.getName())
                .birth(result.getBirth())
                .agency(result.getAgency())
                .nationality(result.getNationality())
                .introduction(result.getIntroduction())
                .registeredDate(result.getRegisteredDate())
                .build();

        return artistResponse;
    }

    @Transactional
    public void deleteArtistInfo(Long id) {
        findArtistById(id);

        artistMapper.delete(id);
    }
}
