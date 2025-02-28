package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.User;
import com.dudu.service.UserService;
import com.dudu.tools.JwtUtil;
import com.dudu.tools.ServletUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据id获取用户信息
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void queryBookList(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            User user = userService.info(id);

            System.out.println(user);
            ServletUtil.createSuccessResponse(user, response);
        } catch (RuntimeException e) {
            ServletUtil.createErrorResponse(500, e.getMessage(), response);
        }
    }

    /**
     * 根据token获取用户信息
     * */
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void queryBookList(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");

        Claims claims = JwtUtil.parseToken(token.substring(7));

        try {
            User user = userService.info(Long.parseLong(claims.get("userId").toString()));

            ServletUtil.createSuccessResponse(user, response);
        } catch (RuntimeException e) {
            ServletUtil.createErrorResponse(500, e.getMessage(), response);
        }
    }
}
