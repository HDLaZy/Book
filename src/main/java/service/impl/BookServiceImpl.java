package service.impl;

import dao.BookDAO.BookDao;
import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-10-28-16:48
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDAO=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDAO.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
     return    bookDAO.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {

        Page<Book> page=new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount=bookDAO.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount % pageSize>0){
            pageTotal+=1;
        }

        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDAO.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount=bookDAO.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount % pageSize>0){
            pageTotal+=1;
        }

        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDAO.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
