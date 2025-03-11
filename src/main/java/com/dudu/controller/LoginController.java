package com.dudu.controller;

import com.dudu.base.domain.Result;
import com.dudu.base.domain.ResultStatus;
import com.dudu.domain.Login;
import com.dudu.domain.User;
import com.dudu.dto.request.LoginDto;
import com.dudu.service.LoginService;
import com.dudu.tools.JwtUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@Api(tags = "登录")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录", notes = "通过用户名和密码登录")
    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public Result<String> login(@ApiParam(value = "登录请求参数", required = true) @Valid @RequestBody LoginDto login) {

        Login newLogin = new Login();
        newLogin.setUsername(login.getUsername());
        newLogin.setPassword(login.getPassword());

        User user = loginService.userLogin(newLogin);

        if (user == null) {
            return Result.error(ResultStatus.REQUEST_ERROR, "用户名或密码错误");
        }

        String token = JwtUtil.generateToken(login.getUsername(), user.getId());
        return Result.ok(token);
    }
}
