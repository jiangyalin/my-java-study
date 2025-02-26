package com.dudu.service;

import com.dudu.domain.Login;
import com.dudu.domain.User;

public interface LoginService {
    // 登录
    User userLogin(Login login);

    // 注册
    User userRegister(Login login, User user);
}
