package com.dudu.controller;

import com.dudu.base.domain.Result;
import com.dudu.base.domain.ResultStatus;
import com.dudu.domain.User;
import com.dudu.dto.response.UserInfoResponseDto;
import com.dudu.service.UserService;
import com.dudu.tools.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "根据id获取用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<UserInfoResponseDto> userInfoById(
            @ApiParam(value = "用户ID", required = true, example = "1") @PathVariable("id") Long id) {
        try {
            User user = userService.info(id);

            UserInfoResponseDto dto = new UserInfoResponseDto();
            BeanUtils.copyProperties(user, dto);
            return Result.ok(dto);
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "获取用户信息", notes = "根据token获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<UserInfoResponseDto> userInfoByToken(@RequestHeader("Authorization") String token) {
        try {
            Claims claims = JwtUtil.parseToken(token.substring(7));

            User user = userService.info(Long.parseLong(claims.get("userId").toString()));

            UserInfoResponseDto dto = new UserInfoResponseDto();
            BeanUtils.copyProperties(user, dto);
            return Result.ok(dto);
        } catch (JwtException e) {
            return Result.of(ResultStatus.UN_AUTH, "Token 无效或已过期");
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }
}
