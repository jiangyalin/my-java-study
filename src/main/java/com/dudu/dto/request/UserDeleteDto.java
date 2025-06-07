package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDeleteDto {
    @NotEmpty(message = "ids")
    @ApiModelProperty(value = "ids", example = "1")
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
