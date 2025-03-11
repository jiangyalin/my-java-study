package com.dudu.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "token")
public class LoginResponseDto {
    @ApiModelProperty(value = "token")
    private String token;
}
