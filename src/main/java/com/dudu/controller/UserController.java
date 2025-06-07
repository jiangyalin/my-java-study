package com.dudu.controller;

import com.dudu.base.domain.Result;
import com.dudu.base.domain.ResultStatus;
import com.dudu.domain.User;
import com.dudu.dto.request.UserAddDto;
import com.dudu.dto.request.UserDeleteDto;
import com.dudu.dto.request.UserListDto;
import com.dudu.dto.request.UserUpdateDto;
import com.dudu.dto.response.OkResponseDto;
import com.dudu.dto.response.UserInfoResponseDto;
import com.dudu.dto.response.UserListResponseDto;
import com.dudu.service.UserService;
import com.dudu.tools.JwtUtil;
import com.dudu.tools.Page;
import com.google.common.base.Splitter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "根据id获取用户信息")
    @RequestMapping(value = "/infoById", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<UserInfoResponseDto> userInfoById(
            @ApiParam(value = "用户ID", required = true, example = "1") @RequestParam("id") Long id) {
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

    @ApiOperation(value = "获取用户信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<Page<UserListResponseDto>> userList(
            @ApiParam(value = "用户列表", required = true) @Valid UserListDto userListDto) {
        try {
            Page<UserListResponseDto> list = userService.queryUserList(userListDto);
            return Result.ok(list);
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<UserInfoResponseDto> userAdd(
            @ApiParam(value = "新增用户", required = true) @Valid @RequestBody UserAddDto userAddDto) {
        try {
            User user = new User();
            user.setNickName(userAddDto.getNickName());
            user.setPhone(userAddDto.getPhone());
            user.setEmail(userAddDto.getEmail());

            User newUser = userService.add(user);
            UserInfoResponseDto dto = new UserInfoResponseDto();
            BeanUtils.copyProperties(newUser, dto);
            return Result.ok(dto);
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<OkResponseDto> userDelete(
            @ApiParam(value = "删除用户", required = true) @Valid @RequestBody UserDeleteDto userDeleteDto) {
        try {
            List<Long> longList = Splitter.on(',')
                    .trimResults()          // 自动去除空格
                    .omitEmptyStrings()     // 忽略空值
                    .splitToList(userDeleteDto.getIds())
                    .stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            userService.delete(longList);
            return Result.ok(new OkResponseDto());
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }

    @ApiOperation(value = "编辑用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<UserInfoResponseDto> userUpdate(
            @ApiParam(value = "编辑用户", required = true) @Valid @RequestBody UserUpdateDto userUpdateDto) {
        try {
            Long id = userUpdateDto.getId();
            User user = userService.info(id);
            user.setNickName(userUpdateDto.getNickName());
            user.setPhone(userUpdateDto.getPhone());
            user.setEmail(userUpdateDto.getEmail());

            User newUser = userService.update(user);
            UserInfoResponseDto dto = new UserInfoResponseDto();
            BeanUtils.copyProperties(newUser, dto);
            return Result.ok(dto);
        } catch (RuntimeException e) {
            return Result.of(ResultStatus.ERROR, e.getMessage());
        }
    }
}
