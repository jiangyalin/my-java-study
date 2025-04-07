package com.dudu.service;

import com.dudu.dto.request.UserListDto;
import com.dudu.tools.Page;
import com.dudu.domain.User;

import java.util.Map;

public interface UserService {
    Page queryUserList(UserListDto userListDto);

    User update(String id, User user);

    User info(Long id);
}
