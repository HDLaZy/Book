package service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.BookService;
import utils.JDBCUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liaoke
 * @create 2021-10-28-16:49
 */
public class BookServiceImplTest extends TestCase {
    private BookService bookService=new BookServiceImpl();

    @Test
    public void testaddBook() {
        bookService.addBook(new Book(null,"TestService","test",new BigDecimal(11111),100,1,null));
        JDBCUtils.commitAndClose();
    }

    @Test
    public void testdeleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void testupdateBook() {
        bookService.updateBook(new Book(22,"TestService2","test",new BigDecimal(11111),100,1,null));
    }

    @Test
    public void testqueryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void testqueryBooks() {
        System.out.println(bookService.queryBooks());
    }


    @Test
    public void testpage(){
        System.out.println(bookService.page(1,4));
    }

    @Test
    public void testPageByprice(){
        Page<Book> page = bookService.pageByPrice(0, 4, 99, 177);
        List<Book> items = page.getItems();
        for(Book l:items){
            System.out.println(l.toString());
        }
    }

}