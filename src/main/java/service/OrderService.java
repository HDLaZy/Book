package service;

import com.sun.org.apache.xpath.internal.operations.Or;
import pojo.*;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-11-10-22:57
 */
public interface OrderService {

    public String createOrder(Cart cart,Integer userId);

    public List<Order>showAllOrders();

    public List<OrderItem> showOrderDetail(String orderId);

    public List<Order>showMyOrders(Integer userId);

    public void receiverOrder(String orderId);

}
