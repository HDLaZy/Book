package dao.impl;

import dao.OrderDAO.OrderDao;
import pojo.Book;
import pojo.Order;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-11-10-22:17
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        //System.out.println("OrderDaoImpl程序在:【"+Thread.currentThread().getName()+"】线程中");
        String sql="insert into t_order (`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return  update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql="select order_id as orderId,create_time as  createTime , price ,  status,user_id as userId from t_order where user_id=?";
        return queryList(Order.class,sql,userId);
    }

    @Override
    public int changOrderStatus(String orderId, Integer status) {
        String sql= "UPDATE  t_order  SET `status`=? WHERE order_id=?";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrders() {
        String sql="SELECT order_id as  orderId  ,create_time as  createTime ,price,`status`,user_id as userId FROM t_order ";
        return queryList(Order.class,sql);
    }


}
