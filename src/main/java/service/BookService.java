package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-10-28-16:45
 */
public interface BookService {

    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
