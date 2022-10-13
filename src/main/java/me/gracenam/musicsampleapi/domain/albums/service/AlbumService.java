package me.gracenam.musicsampleapi.domain.albums.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumSearchParam;
import me.gracenam.musicsampleapi.domain.albums.mapper.AlbumMapper;
import me.gracenam.musicsampleapi.global.commons.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
