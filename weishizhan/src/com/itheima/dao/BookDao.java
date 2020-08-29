package com.itheima.dao;

import com.itheima.db.InitDB;
import com.itheima.domain.Book;

import java.util.List;

public class BookDao {
    /**
     * 查询所有
     * @return
     */
    public List<Book> findAll() {
        //查询所有的数据
        List<Book> bookList = InitDB.getBookList();
        return bookList;
    }

    /**
     *  根据用户id查询
     * @param id
     * @return
     */
    public Book findById(Integer id) {
        //查询数据
        Book book = InitDB.findById(id);
        return book;
    }

    /**
     * 修改案例
     * @param book
     */
    public void update(Book book) {
        InitDB.update(book);
    }


    public void save(Book book) {
        System.out.println("BookDao执行了...");
        InitDB.save(book);
    }

    public void deleteById(Integer valueOf) {
        InitDB.delete(valueOf);
    }
}
