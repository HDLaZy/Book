package dao.OrderItemDAO;

import pojo.OrderItem;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-11-10-22:16
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemsByOrderId(String orderId);

}
