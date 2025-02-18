package com.dudu.dao;

import com.dudu.domain.Book;
import com.dudu.tools.Page;

import java.util.Map;

public interface BookDao {
    Page queryBookList(Map<String, Object> params);
    Book add(Book book);
    int delete(String ids);
    Book update(String id, Book book);
}
