package dao.impl;

import dao.OrderDAO.OrderDao;
import junit.framework.TestCase;
import org.junit.Test;
import pojo.Order;
import utils.JDBCUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

/**
 * @author liaoke
 * @create 2021-11-10-22:38
 */
public class OrderDaoImplTest extends TestCase {

private  OrderDao orderDao=new OrderDaoImpl();

    @Test
    public void testsaveOrder() {

        orderDao.saveOrder(new Order("13113131313131",new Date(),new BigDecimal(100),0,1));

        JDBCUtils.commitAndClose();
    }

    @Test
    public void testqueryOrderByUserId() {
        List<Order> orders = orderDao.queryOrderByUserId(1);
        JDBCUtils.commitAndClose();
        System.out.println(orders);
    }

    @Test
    public void testchangOrderStatus() {
        orderDao.changOrderStatus("13113131313131",1);
        JDBCUtils.commitAndClose();
    }

    @Test
    public void testqueryOrders() {
        List<Order> orders = orderDao.queryOrders();
        JDBCUtils.commitAndClose();
        System.out.println(orders);
    }
}