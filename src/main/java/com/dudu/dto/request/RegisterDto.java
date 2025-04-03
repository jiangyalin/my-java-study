package com.dudu.dto.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
public class RegisterDto {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;

    @NotEmpty(message = "昵称")
    @ApiModelProperty(value = "昵称", example = "明日香")
    private String nickName;

    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "昵称", required = true, example = "xxx@163.com")
    private String email;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true, example = "xxx")
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
