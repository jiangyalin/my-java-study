package com.dudu.dao.impl;

import com.dudu.dao.LoginDao;
import com.dudu.domain.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Login add(Login login) {
        try {
            entityManager.persist(login);
        } catch (PersistenceException e) {
            String errorMessage = null;

            errorMessage = e.getMessage();
            Throwable rootCause = e.getCause();
            if (rootCause instanceof org.hibernate.exception.ConstraintViolationException) {
                org.hibernate.exception.ConstraintViolationException hibernateException =
                        (org.hibernate.exception.ConstraintViolationException) rootCause;

                Throwable databaseRootCause = hibernateException.getCause();
                if (databaseRootCause instanceof java.sql.SQLException) {
                    java.sql.SQLException sqlException = (java.sql.SQLException) databaseRootCause;
                    errorMessage = sqlException.getMessage();
                }
            }
            throw new RuntimeException(errorMessage, e);
        }

        Long id = login.getId();

        return entityManager.find(Login.class, id);
    }

    @Override
    public int delete(String ids) {
        return jdbcTemplate.update("delete from login where id in (" + ids + ")");
    }

    @Override
    public Login update(String id, Login login) {
        jdbcTemplate.update("update login set username=?,password=? where id = ?", new Object[]{login.getUsername(), login.getPassword(), id});

        String querySql = "select * from login where id = ?";

        return jdbcTemplate.queryForObject(querySql, new BeanPropertyRowMapper<>(Login.class), id);
    }

    @Override
    public boolean check(Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        String sql = "select count(*) from login where username = ? AND password = ?";

        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);

        return count > 0;
    }

    @Override
    public Login findByUsername(String username) {
        String jpql = "SELECT l FROM Login l WHERE l.username = :username";
        TypedQuery<Login> query = entityManager.createQuery(jpql, Login.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }
}
