package com.dudu.service;

import com.dudu.tools.Page;
import com.dudu.domain.User;

import java.util.Map;

public interface UserService {
    Page queryUserList(Map<String, Object> params);

    User update(String id, User user);

    User info(Long id);
}
