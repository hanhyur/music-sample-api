package me.gracenam.musicsampleapi.domain.albums.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumDetailResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumSearchParam;
import me.gracenam.musicsampleapi.domain.albums.entity.Album;
import me.gracenam.musicsampleapi.domain.albums.exception.AlbumNotFoundException;
import me.gracenam.musicsampleapi.domain.albums.mapper.AlbumMapper;
import me.gracenam.musicsampleapi.global.commons.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumMapper albumMapper;

    private final ModelMapper modelMapper;


    public PageResponse<AlbumResponse> findAllAlbum(AlbumSearchParam param) {
        param.searchParamValidate();

        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        PageInfo<AlbumResponse> pageInfo = PageInfo.of(albumMapper.findAll(param));

        return PageResponse.<AlbumResponse>builder()
                .param(param)
                .page(pageInfo)
                .build();
    }

    public AlbumDetailResponse findAlbumById(Long id) {
        Album albumInfo = albumMapper.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(Long.toString(id)));

        AlbumDetailResponse albumDetailResponse = AlbumDetailResponse.builder()
                .id(albumInfo.getId())
                .artistName(albumInfo.getArtistName())
                .title(albumInfo.getTitle())
                .releaseDate(albumInfo.getReleaseDate())
                .genre(albumInfo.getGenre())
                .description(albumInfo.getDescription())
                .registeredDate(albumInfo.getRegisteredDate())
                .build();

        return albumDetailResponse;
    }

    @Transactional
    public AlbumResponse saveAlbum(AlbumRequest albumReq) {
        Album album = modelMapper.map(albumReq, Album.class);

        Long id = albumMapper.save(album);

        Album result = albumMapper.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(Long.toString(id)));

        AlbumResponse albumResponse = AlbumResponse.builder()
                .id(result.getId())
                .artistName(result.getArtistName())
                .title(result.getTitle())
                .releaseDate(result.getReleaseDate())
                .genre(result.getGenre())
                .description(result.getDescription())
                .registeredDate(result.getRegisteredDate())
                .build();

        return albumResponse;
    }

    @Transactional
    public void deleteAlbum(Long id) {
        findAlbumById(id);

        albumMapper.delete(id);
    }

}
