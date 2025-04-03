package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.base.domain.Result;
import com.dudu.base.domain.ResultStatus;
import com.dudu.domain.Login;
import com.dudu.domain.User;
import com.dudu.dto.request.RegisterDto;
import com.dudu.dto.response.UserInfoResponseDto;
import com.dudu.service.LoginService;
import com.dudu.tools.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/register")
@Api(tags = "注册")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "注册")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<UserInfoResponseDto> register(@ApiParam(value = "注册请求参数", required = true) @Valid @RequestBody RegisterDto responseDto) {
        String username = responseDto.getUsername();
        String password = responseDto.getPassword();
        String email = responseDto.getEmail();
        String phone = responseDto.getPhone();
        String nickName = responseDto.getNickName();

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        User user = new User();
        user.setEmail(email);
        user.setPhone(phone);
        user.setNickName(nickName);

        try {
            User newUser = loginService.userRegister(login, user);

            UserInfoResponseDto dto = new UserInfoResponseDto();
            BeanUtils.copyProperties(newUser, dto);

            return Result.ok(dto);
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }
}
