package com.dudu.service.impl;

import com.dudu.dao.LoginDao;
import com.dudu.dao.UserDao;
import com.dudu.domain.Login;
import com.dudu.domain.User;
import com.dudu.service.LoginService;
import com.dudu.service.UserService;
import com.dudu.tools.Page;
import com.dudu.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;
    @Autowired
    UserDao userDao;

    @Override
    public User userLogin(Login login) {
        boolean check = this.loginDao.check(login);
        if (!check) {
            return null;
        }
        Login newLogin = loginDao.findByUsername(login.getUsername());
        return userDao.findByLoginId(newLogin.getId());
    }

    @Override
    @Transactional
    public User userRegister(Login login, User user) {
        User newUser = null;
        try {
            this.loginDao.add(login);
            user.setLogin(login);
            newUser = this.userDao.add(user);
            newUser.setLogin(null);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return newUser;
    }
}
