package com.dudu.dao.impl;

import com.dudu.dao.BookDao;
import com.dudu.domain.Book;
import com.dudu.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Book add(Book book) {
        String sql = "insert into book(name, author, publicationDate, copiesNumber) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublicationDate());
            ps.setString(4, book.getCopiesNumber().toString());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("插入数据失败，未生成主键");
        }
        Long id = key.longValue();

        String querySql = "select * from book where id = ?";

        return jdbcTemplate.queryForObject(querySql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    @Override
    public Page queryBookList(Map<String, Object> params) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from book where 1=1");
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()), jdbcTemplate);
        return page;
    }

    @Override
    public int delete(String ids) {
        return jdbcTemplate.update("delete from book where id in (" + ids + ")");
    }

    public Book update(String id, Book book) {
        jdbcTemplate.update("update book set name=?,author=?,publicationDate=?,copiesNumber=? where id = ?", new Object[]{book.getName(), book.getAuthor(), book.getPublicationDate(), book.getCopiesNumber(), id});

        String querySql = "select * from book where id = ?";

        return jdbcTemplate.queryForObject(querySql, new BeanPropertyRowMapper<>(Book.class), id);
    }
}
