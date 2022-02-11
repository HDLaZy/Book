package service.impl;

import dao.BookDAO.BookDao;
import dao.OrderDAO.OrderDao;
import dao.OrderItemDAO.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * @author liaoke
 * @create 2021-11-10-22:58
 */
public class OrderServerImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //System.out.println("OrderServiceImpl程序在:【"+Thread.currentThread().getName()+"】线程中");
        //订单编号
        String orderId=System.currentTimeMillis()+userId+"";
        //保存订单和订单项
        Order order=new Order(orderId,new java.util.Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);


        //int i=12/0;

        //遍历购物车商品项转化为订单项
        for(Map.Entry <Integer,CartItem> entry:cart.getItems().entrySet()){
            CartItem cartItem=entry.getValue();

            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);

            //改变图书的库存和销量
            Book book=bookDao.queryBookById(cartItem.getId());

            //库存
            book.setStock(book.getStock()-cartItem.getCount());
            //销量
            book.setSales(book.getSales()+cartItem.getCount());

            bookDao.updateBook(book);

        }

        //结账清空购物车
        cart.clean();

        return orderId;
    }

    /**
     * @description  管理员查看所有的订单
     * @author HDLaZy
     * @updateTime 2021/11/12 12:39
     */
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }


    /**
     * @description 查看订单详情
     * @author HDLaZy
     * @updateTime 2021/11/12 12:40
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    /**
     * @description   查看自己的订单
     * @author HDLaZy
     * @updateTime 2021/11/12 12:40
     */
    @Override
    public List<Order> showMyOrders(Integer userId) {
       return orderDao.queryOrderByUserId(userId);
    }

    /**
     * @description 确认收货
     * @author HDLaZy
     * @updateTime 2021/11/12 12:40
     */
    @Override
    public void receiverOrder(String orderId) {
        orderDao.changOrderStatus(orderId,2);
    }



}
