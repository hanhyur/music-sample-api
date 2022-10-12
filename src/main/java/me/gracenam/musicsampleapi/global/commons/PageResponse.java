package me.gracenam.musicsampleapi.global.commons;

import com.github.pagehelper.PageInfo;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private SearchParam param;
    private PageInfo<T> page;

}
