package com.dudu.service;

import com.dudu.dto.request.UserListDto;
import com.dudu.dto.response.UserListResponseDto;
import com.dudu.tools.Page;
import com.dudu.domain.User;

public interface UserService {
    Page<UserListResponseDto> queryUserList(UserListDto userListDto);

    User update(String id, User user);

    User info(Long id);
}
