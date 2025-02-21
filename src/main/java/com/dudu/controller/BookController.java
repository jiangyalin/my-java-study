package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.Book;
import com.dudu.service.BookService;
import com.dudu.tools.Page;
import com.dudu.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void queryBookList(@RequestParam(value = "currentPage", defaultValue = "1") String currentPage, @RequestParam(value = "pageSize", defaultValue = "10") String pageSize, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        Page pageObj = bookService.queryBookList(params);
        List<Map<String, Object>> bookList = pageObj.getResultList();
        JSONObject json = new JSONObject();
        json.put("rows", bookList);
        json.put("total", pageObj.getTotalPages());
        json.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, json, response);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void addBook(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String publicationDate = request.getParameter("publicationDate");
        String copiesNumberStr = request.getParameter("copiesNumber");

        Integer copiesNumber;
        try {
            copiesNumber = Integer.parseInt(copiesNumberStr);
        } catch (NumberFormatException e) {
            ServletUtil.createSuccessResponse(400, "册数类型不对", response);
            return;
        }

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublicationDate(publicationDate);
        book.setCopiesNumber(copiesNumber);

        JSONObject json = new JSONObject();

        Book newBook = bookService.add(book);

        json.put("data", newBook);

        ServletUtil.createSuccessResponse(200, json, response);
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public void deleteBook(@PathVariable("ids") String ids, HttpServletResponse response) {
        int index = bookService.delete(ids);

        JSONObject json = new JSONObject();
        json.put("data", index);

        ServletUtil.createSuccessResponse(200, json, response);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String publicationDate = request.getParameter("publicationDate");
        String copiesNumberStr = request.getParameter("copiesNumber");

        Integer copiesNumber;
        try {
            copiesNumber = Integer.parseInt(copiesNumberStr);
        } catch (NumberFormatException e) {
            ServletUtil.createSuccessResponse(400, "册数类型不对", response);
            return;
        }

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublicationDate(publicationDate);
        book.setCopiesNumber(copiesNumber);

        Book data = bookService.update(id, book);

        JSONObject json = new JSONObject();
        json.put("data", data);

        ServletUtil.createSuccessResponse(200, json, response);
    }
}
