package dao.impl;

import dao.OrderItemDAO.OrderItemDao;
import pojo.OrderItem;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-11-10-22:17
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
       // System.out.println("OrderItemDaoImpl程序在:【"+Thread.currentThread().getName()+"】线程中");
        String sql="insert into t_order_item (`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return  update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql="SELECT id  ,`name`,`count`,price,total_price AS totalPrice, order_id AS orderId FROM t_order_item WHERE order_id =?";
        return queryList(OrderItem.class,sql,orderId);
    }


}

