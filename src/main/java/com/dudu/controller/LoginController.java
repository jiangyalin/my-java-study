package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.Login;
import com.dudu.domain.User;
import com.dudu.service.LoginService;
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
     * @deprecated 登录
     * */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void login(@RequestParam(value = "username", defaultValue = "") String username, @RequestParam(value = "password", defaultValue = "") String password, HttpServletResponse response) {
        // ErrorCode errorCode = ErrorCode.ADMIN_ACCOUNT_IS_NULL;
        // System.out.println("errorCode");
        // System.out.println(errorCode);
        // System.out.println(errorCode.getCode());
        // System.out.println(errorCode.getRes_code());
        // System.out.println(errorCode.getMessage());
        // System.out.println(errorCode.getHttpStatus());
        // System.out.println("errorCode");

        if (Objects.equals(username, "")) {
            JSONObject json = new JSONObject();
            json.put("code", 400);
            json.put("data", new Object());
            json.put("msg", "用户名不能为空");
            ServletUtil.createSuccessResponse(200, json, response);
            return;
        }

        if (Objects.equals(password, "")) {
            JSONObject json = new JSONObject();
            json.put("code", 400);
            json.put("data", new Object());
            json.put("msg", "密码不能为空");
            ServletUtil.createSuccessResponse(200, json, response);
            return;
        }

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        User user = loginService.userLogin(login);

        if (user == null) {
            JSONObject json = new JSONObject();
            json.put("code", 500);
            json.put("data", new Object());
            json.put("msg", "登录失败，请检查用户名和密码");
            ServletUtil.createSuccessResponse(200, json, response);
            return;
        }

        String token = JwtUtil.generateToken(username, user.getId());

        JSONObject json = new JSONObject();
        json.put("data", token);

        ServletUtil.createSuccessResponse(200, json, response);
    }
}
