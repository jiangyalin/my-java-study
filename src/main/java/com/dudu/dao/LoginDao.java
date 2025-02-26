package com.dudu.dao;

import com.dudu.domain.Login;

public interface LoginDao {
    Login add(Login login);
    int delete(String ids);
    Login update(String id, Login login);
    boolean check(Login login);
    Login findByUsername(String username);
}
