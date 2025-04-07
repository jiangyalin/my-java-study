package com.dudu.dao;

import com.dudu.domain.User;
import com.dudu.dto.request.UserListDto;
import com.dudu.tools.Page;

import java.util.Map;

public interface UserDao {
    Page queryUserList(UserListDto userListDto);
    User add(User user);
    int delete(String ids);
    User update(String id, User user);
    User info(Long id);
    User findByLoginId(Long id);
}
