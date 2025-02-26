package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.Book;
import com.dudu.domain.User;
import com.dudu.service.BookService;
import com.dudu.service.UserService;
import com.dudu.tools.JwtUtil;
import com.dudu.tools.Page;
import com.dudu.tools.ServletUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @deprecated 根据id获取用户信息
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void queryBookList(@PathVariable("id") Long id, HttpServletResponse response) {
        JSONObject json = new JSONObject();

        try {
            json.put("code", 200);
            User user = userService.info(id);
            json.put("data", user);
            json.put("msg", "");

            ServletUtil.createSuccessResponse(200, json, response);
        } catch (RuntimeException e) {
            json.put("code", 500);
            json.put("data", new Object());
            json.put("msg", e.getMessage());

            ServletUtil.createSuccessResponse(200, json, response);
        }
    }

    /**
     * @deprecated 根据token获取用户信息
     * */
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void queryBookList(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        String token = request.getHeader("Authorization");

        Claims claims = JwtUtil.parseToken(token.substring(7));
        System.out.println(111);
        System.out.println(claims.get("userId"));
        System.out.println(111);

        try {
            json.put("code", 200);
            User user = userService.info(Long.parseLong(claims.get("userId").toString()));
            json.put("data", user);
            json.put("msg", "");

            ServletUtil.createSuccessResponse(200, json, response);
        } catch (RuntimeException e) {
            json.put("code", 500);
            json.put("data", new Object());
            json.put("msg", e.getMessage());

            ServletUtil.createSuccessResponse(200, json, response);
        }
    }
}
