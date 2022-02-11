package dao.impl;

import dao.OrderItemDAO.OrderItemDao;
import junit.framework.TestCase;
import org.junit.Test;
import pojo.OrderItem;
import utils.JDBCUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liaoke
 * @create 2021-11-10-22:50
 */
public class OrderItemDaoImplTest extends TestCase {

   private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void testsaveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"test",1,new BigDecimal(11),new BigDecimal(11),"13113131313131"));
        JDBCUtils.commitAndClose();
    }

    @Test
    public void testquery(){
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId("13113131313131");
        JDBCUtils.commitAndClose();
        System.out.println(orderItems);
    }
}