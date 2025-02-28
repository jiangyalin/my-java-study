package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.Login;
import com.dudu.domain.User;
import com.dudu.service.LoginService;
import com.dudu.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    /**
     * @description 注册
     * */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if (Objects.equals(username, "")) {
            JSONObject json = new JSONObject();
            json.put("code", 400);
            json.put("data", new Object());
            json.put("msg", "用户名不能为空");
            ServletUtil.createSuccessResponse(json, response);
            return;
        }

        if (Objects.equals(password, "")) {
            JSONObject json = new JSONObject();
            json.put("code", 400);
            json.put("data", new Object());
            json.put("msg", "密码不能为空");
            ServletUtil.createSuccessResponse(json, response);
            return;
        }

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        User user = new User();
        user.setEmail(email);
        user.setPhone(phone);

        try {
            User newUser = loginService.userRegister(login, user);

            JSONObject json = new JSONObject();
            json.put("code", 200);
            json.put("data", newUser);
            json.put("msg", "");

            ServletUtil.createSuccessResponse(json, response);
        } catch (RuntimeException e) {
            JSONObject json = new JSONObject();
            json.put("code", 500);
            json.put("data", new Object());
            json.put("msg", e.getMessage());

            ServletUtil.createSuccessResponse(json, response);
        }
    }
}
