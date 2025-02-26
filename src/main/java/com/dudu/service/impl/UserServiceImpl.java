package com.dudu.service.impl;

import com.dudu.dao.UserDao;
import com.dudu.domain.User;
import com.dudu.service.UserService;
import com.dudu.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Page queryUserList(Map<String, Object> params) {
        return this.userDao.queryUserList(params);
    }

    @Override
    public User update(String id, User user) {
        return this.userDao.update(id, user);
    }

    @Override
    public User info(Long id) {
        User user = userDao.info(id);
        user.setLogin(null);
        return user;
    }
}
