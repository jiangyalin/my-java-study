package com.dudu.service;

import com.dudu.dto.request.UserListDto;
import com.dudu.dto.response.UserListResponseDto;
import com.dudu.tools.Page;
import com.dudu.domain.User;

import java.util.List;

public interface UserService {
    Page<UserListResponseDto> queryUserList(UserListDto userListDto);

    User update(User user);

    User info(Long id);

    User add(User user);

    int delete(List<Long> ids);
}
