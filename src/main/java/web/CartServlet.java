package web;

import com.google.gson.Gson;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaoke
 * @create 2021-11-10-10:20
 */
public class CartServlet  extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * @description 加入购物车
     * @author HDLaZy
     * @updateTime 2021/11/10 10:23
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品编号 调用BookService.queryById(Id)去得到图书的信息
        Book book = bookService.queryBookById(WebUtils.parseInt(req.getParameter("id"), 0));
        //将图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem)添加
        Cart cart = (Cart) req.getSession().getAttribute("Cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("Cart", cart);
        }
        cart.addItem(cartItem);
        //重定向回发起请求的页面

        req.getSession().setAttribute("lastBook",cartItem.getName());

        resp.sendRedirect(req.getHeader("Referer"));

        //发起请求的地址
        // System.out.println(req.getHeader("Referer"));
    }

    /**
     * @description 删除商品项
     * @author HDLaZy
     * @updateTime 2021/11/10 10:51
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //找到Session的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("Cart");

        if (cart != null) {
            //删除商品项
            cart.deleteItem(id);
            //重定向到请求来的页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @description 清空购物车
     * @author HDLaZy
     * @updateTime 2021/11/10 10:51
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("Cart");

        if(cart!=null){
            cart.clean();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @description 修改商品数量
     * @author HDLaZy
     * @updateTime 2021/11/10 10:51
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品的数量和编号
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("Cart");
        {
            //修改商品
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品编号 调用BookService.queryById(Id)去得到图书的信息
        Book book = bookService.queryBookById(WebUtils.parseInt(req.getParameter("id"), 0));
        //将图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem)添加
        Cart cart = (Cart) req.getSession().getAttribute("Cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("Cart", cart);
        }
        cart.addItem(cartItem);
        //重定向回发起请求的页面

        req.getSession().setAttribute("lastBook",cartItem.getName());

        Map<String,Object> resultMap=new HashMap<>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson=new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);

    }


}