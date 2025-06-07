package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class UserAddDto {
    @NotEmpty(message = "昵称")
    @ApiModelProperty(value = "昵称", example = "明日香")
    private String nickName;

    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱", required = true, example = "xxx@163.com")
    private String email;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true, example = "xxx")
    private String phone;

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
}
