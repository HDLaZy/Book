package web;

import org.apache.commons.beanutils.BeanUtils;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author liaoke
 * @create 2021-10-28-16:56
 */
public class BookServlet extends BaseServlet{

    private BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);

            pageNo+=1;

            Book book= WebUtils.copyParamBean(req.getParameterMap(),new Book());

            bookService.addBook(book);

            resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+pageNo);
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       int id=WebUtils.parseInt(req.getParameter("id"),0);

       bookService.deleteBookById(id);

       resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id=WebUtils.parseInt(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = WebUtils.copyParamBean(req.getParameterMap(), new Book());

        bookService.updateBook(book);

        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1:获取请求参数 pageNo  pageSize
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2:调用BookService.page（ pageNo，pageSize） 返回Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookservlet?action=page");

        //3:将Page对象保持在Request域中
        req.setAttribute("page",page);
        //4:请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
