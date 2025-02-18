package com.dudu.service.impl;

import com.dudu.dao.BookDao;
import com.dudu.domain.Book;
import com.dudu.service.BookService;
import com.dudu.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;

    @Override
    public Book add(Book book) {
        return this.bookDao.add(book);
    }

    @Override
    public Page queryBookList(Map<String, Object> params) {
        return this.bookDao.queryBookList(params);
    }

    @Override
    public int delete(String ids) {
        return this.bookDao.delete(ids);
    }

    @Override
    public Book update(String id, Book book) {
        return this.bookDao.update(id, book);
    }
}
