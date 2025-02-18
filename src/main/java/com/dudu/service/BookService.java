package com.dudu.service;

import com.dudu.domain.Book;
import com.dudu.tools.Page;

import java.util.Map;

public interface BookService {

    Page queryBookList(Map<String, Object> params);
    Book add(Book book);
    int delete(String ids);

    Book update(String id, Book book);
}
