package dao.OrderDAO;

import pojo.Book;
import pojo.Order;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-11-10-22:15
 */
public interface OrderDao {

    public int saveOrder(Order order);

    //通过用户id查询订单
    public List<Order> queryOrderByUserId(Integer userId);

    //改变订单状态
    public int changOrderStatus(String orderId,Integer status);

    //查询所有的订单
    public List<Order> queryOrders();



}
