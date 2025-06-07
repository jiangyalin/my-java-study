package com.dudu.service.impl;

import com.dudu.dao.UserDao;
import com.dudu.domain.User;
import com.dudu.dto.request.UserListDto;
import com.dudu.service.UserService;
import com.dudu.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Page queryUserList(UserListDto userListDto) {
        return this.userDao.queryUserList(userListDto);
    }

    @Override
    @Transactional
    public User update(User user) {
        return this.userDao.update(user);
    }

    @Override
    public User info(Long id) {
        User user = userDao.info(id);
        if (user != null) {
            user.setLogin(null);
        }
        return user;
    }

    @Override
    @Transactional
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.userDao.delete(ids);
    }
}
