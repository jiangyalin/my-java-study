package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;

public class UserListDto extends PageDto {
    // @NotEmpty(message = "页码不能为空")
    @ApiModelProperty(value = "关键字", example = "")
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
