package me.gracenam.musicsampleapi.global.commons;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.gracenam.musicsampleapi.global.error.exception.ParamValidationException;

@Getter
@Setter
@ToString
public class SearchParam {

    private String searchType;
    private String keyword;

    private int pageSize = 10;
    private int pageNum;

    public void searchParamValidate() {
        if(this.pageNum < 0) {
            throw new ParamValidationException();
        }

        if(this.pageSize < 0) {
            throw new ParamValidationException();
        }
    }

}
