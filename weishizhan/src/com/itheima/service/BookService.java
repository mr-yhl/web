package com.itheima.service;

import com.itheima.dao.BookDao;
import com.itheima.domain.Book;

import java.util.List;


public class BookService {
    /**
     * 查询所有的图书数据
     * @return
     */
    public List<Book> findAll() {
        //创建对象
        BookDao bookDao = new BookDao();
        //查询数据
        List<Book> bookList = bookDao.findAll();
        //返回数据
        return bookList;
    }

    /**
     * 根据id获得图书对象
     */
    public Book findById(Integer id) {
        //创建对象
        BookDao bookDao = new BookDao();
        //查询图书数据
        Book book = bookDao.findById(id);
        return book;
    }

    /**
     * 修改案例
     */
    public void update(Book book) {
        BookDao bookDao = new BookDao();
        bookDao.update(book);
    }



    /**
     * 保存书籍对象
     * @param book
     */
    public void save(Book book) {
        System.out.println("BookService方法执行了....");
        // 创建对象
        BookDao bookDao = new BookDao();
        // 保存对象
        bookDao.save(book);
    }

    /**
     * 删除目录
     * @param valueOf
     */
    public void deleteById(Integer valueOf) {
        // 创建对象
        BookDao bookDao = new BookDao();
        bookDao.deleteById(valueOf);
    }
}
