package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserUpdateDto {
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id", required = true, example = "1")
    private Long id;

    @NotEmpty(message = "昵称")
    @ApiModelProperty(value = "昵称", example = "明日香")
    private String nickName;

    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱", example = "xxx@163.com")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true, example = "xxx")
    private String phone;
}
