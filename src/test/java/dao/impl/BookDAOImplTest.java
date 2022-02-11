package dao.impl;

import dao.BookDAO.BookDao;
import junit.framework.TestCase;
import org.junit.Test;
import pojo.Book;
import utils.JDBCUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liaoke
 * @create 2021-10-28-16:31
 */
public class BookDAOImplTest extends TestCase {
    private BookDao bookDAO=new BookDaoImpl();

    @Test
    public void testaddBook() {
        bookDAO.addBook(new Book(null,"测试","测试",new BigDecimal(9999),100000,0,null));
    }

    @Test
    public void testdeleteBookById() {
        bookDAO.deleteBookById(21);
    }

    @Test
    public void testupdateBook() {
        bookDAO.updateBook(new Book(21,"测试update","测试",new BigDecimal(9999),100000,0,null));
    }

    @Test
    public void testqueryBookById() {
        Book book = bookDAO.queryBookById(20);
        JDBCUtils.commitAndClose();
        System.out.println(book);
    }

    @Test
    public void testqueryBooks() {

        for(Book q:bookDAO.queryBooks()){
            System.out.println(q);
        }

    }

    @Test
    public void testqueryForPageTotalCount() {
        System.out.println(bookDAO.queryForPageTotalCount());
    }

    @Test
    public void testqueryForPageItems() {
        for (Book book : bookDAO.queryForPageItems(0, 4)) {
            System.out.println(book.toString());
        }
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        Integer integer = bookDAO.queryForPageTotalCountByPrice(99, 177);
        System.out.println(integer);
    }

    @Test
    public void testqueryForPageItemsByPrice(){
        List<Book> books = bookDAO.queryForPageItemsByPrice(0, 4, 99, 177);
        for(Book l:books){
            System.out.println(l.toString());
        }
    }

}