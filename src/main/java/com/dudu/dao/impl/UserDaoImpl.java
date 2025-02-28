package com.dudu.dao.impl;

import com.dudu.dao.UserDao;
import com.dudu.domain.User;
import com.dudu.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Override
    public User add(User user) {
        entityManager.persist(user);

        Long id = user.getId();

        return entityManager.find(User.class, id);
    }

    @Override
    public Page queryUserList(Map<String, Object> params) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from user where 1=1");
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()), jdbcTemplate);
        return page;
    }

    @Override
    public int delete(String ids) {
        return jdbcTemplate.update("delete from user where id in (" + ids + ")");
    }

    @Override
    public User update(String id, User user) {
        jdbcTemplate.update("update user set email=?,phone=? where id = ?", new Object[]{user.getEmail(), user.getPhone(), id});

        String querySql = "select * from user where id = ?";

        return jdbcTemplate.queryForObject(querySql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public User info(Long id) {
        User user = null;
        user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User findByLoginId(Long loginId) {
        String jpql = "SELECT u FROM User u WHERE u.login.id = :loginId";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("loginId", loginId);
        try {
            return query.getSingleResult();
        } catch (PersistenceException e) {
            System.out.println(555);
            return null;
        }
    }
}
