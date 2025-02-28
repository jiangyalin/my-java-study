package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.Login;
import com.dudu.domain.User;
import com.dudu.service.LoginService;
import com.dudu.tools.ErrorCode;
import com.dudu.tools.JwtUtil;
import com.dudu.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void login(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password, HttpServletResponse response) {
        if (Objects.equals(username, "")) {
            ServletUtil.createErrorResponse(ErrorCode.USERNAME_IS_NULL, response);
            return;
        }

        if (Objects.equals(password, "")) {
            ServletUtil.createSuccessResponse(ErrorCode.PWD_IS_NULL, response);
            return;
        }

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        User user = loginService.userLogin(login);

        if (user == null) {
            ServletUtil.createErrorResponse(ErrorCode.USER_OR_PWD_ERROR, response);
            return;
        }

        String token = JwtUtil.generateToken(username, user.getId());

        ServletUtil.createSuccessResponse(token, response);
    }
}
