package com.itheima.db;

import com.itheima.domain.Book;
//import com.itheima.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化数据使用
 */
public class InitDB {
    //定义图书集合对象
    private static List<Book> bookList = null;



    static {
        //初始化数据
        bookList = new ArrayList<Book>();
        bookList.add(new Book(1 , "红楼梦" , "曹雪芹" , 50d));
        bookList.add(new Book(2 , "java23种设计模式" , "张三" , 60d));
        bookList.add(new Book(3 , "java进阶之路" , "李四" , 60d));
        bookList.add(new Book(4 , "企业web实战" , "王五" , 70d));
        bookList.add(new Book(5 , "java入门到精通" , "赵六" , 80d));
        bookList.add(new Book(6 , "数据结构与算法分析" , "田七" , 90d));


    }
    //返回数据
    public static List<Book> getBookList(){
        return bookList;
    }


    /**
     * 根据图书id 从集合中找到一个用户数据
     * @param id
     * @return
     */
    public static Book findById(Integer id) {
        //1.遍历集合
        for (Book book : bookList) {
            //2.判断id是否相同
            if(book.getBookId().equals(id) ){
                //3.返回id相同的book
                return book;
            }
        }
        return null;
    }

    /**
     * 修改数据  
     * 1.遍历集合
     * 2.根据id找到集合中的book对象
     * 3.替换
     * @param book
     */
    public static void update(Book book) {
        //1.遍历集合
        for (int i = 0; i < bookList.size() ; i++) {
            //2.根据id找到集合中的book对象
            Book oldBook = bookList.get(i);
            //判断
            if( oldBook.getBookId().equals(book.getBookId())  ){
                //3.替换
                bookList.remove(i);
                bookList.add(i , book);
            }
        }
       
    }


    public static void save(Book book) {
        System.out.println("InitDB执行了...");
        // 获取最后一个长度
        int size = bookList.size();
        book.setBookId(bookList.get(size-1).getBookId()+1);
        // 添加元素
        bookList.add(size,book);
    }

    public static void delete(Integer valueOf) {
        // 遍历集合
        for (int i = 0; i < bookList.size(); i++) {
            int bookId = bookList.get(i).getBookId();
            // 找到元素
            if ( bookId == valueOf) {
                bookList.remove(i);
                break;
            }
        }
    }
}
