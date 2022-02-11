package web;

import pojo.*;
import service.OrderService;
import service.UserService;
import service.impl.OrderServerImpl;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author liaoke
 * @create 2021-11-11-10:33
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServerImpl();

    /**
     * @description 生成订单
     * @author HDLaZy
     * @updateTime 2021/11/11 10:34
     */
    protected void createOrder (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //先获取Cart购物车对象  获取UserId
        Cart cart = (Cart) req.getSession().getAttribute("Cart");
        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

       // System.out.println("OrderServlet程序在:【"+Thread.currentThread().getName()+"】线程中");

        Integer userId =  loginUser.getId();

        //生成订单


        String orderId = orderService.createOrder(cart, userId);


        req.getSession().setAttribute("orderId",orderId);

        //到结账页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }

    /**
     * @description 展示所有的订单
     * @author HDLaZy
     * @updateTime 2021/11/12 16:59
     */
    protected void showAllOrders (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orders = orderService.showAllOrders();
        req.getSession().setAttribute("allOrders",orders);

        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);


    }
    /**
     * @description 管理员查看订单详情
     * @author HDLaZy
     * @updateTime 2021/11/12 17:48
     */
    protected void showOrderDetailByManager (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.getSession().setAttribute("orderItemsByManager",orderItems);
        req.getRequestDispatcher("/pages/manager/managerOrderDetail.jsp").forward(req,resp);

    }
    /**
     * @description 展示用户所有的订单
     * @author HDLaZy
     * @updateTime 2021/11/12 16:59
     */
    protected void showMyOrders (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.showMyOrders(user.getId());
        req.getSession().setAttribute("userOrders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * @description 展示用户订单详情
     * @author HDLaZy
     * @updateTime 2021/11/12 16:59
     */
    protected void showOrderDetail (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userOrderId = req.getParameter("userOrderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(userOrderId);
        req.getSession().setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(req,resp);
    }


    /**
     * @description 用户确认收货
     * @author HDLaZy
     * @updateTime 2021/11/12 16:59
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String order = req.getParameter("userOrderId");
        orderService.receiverOrder(order);

        User user = (User) req.getSession().getAttribute("user");

        List<Order> orders = orderService.showMyOrders(user.getId());

        req.getSession().setAttribute("userOrders",orders);

        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }


}