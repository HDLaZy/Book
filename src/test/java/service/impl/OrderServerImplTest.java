package service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;
import utils.JDBCUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liaoke
 * @create 2021-11-12-12:45
 */
public class OrderServerImplTest extends TestCase {

    OrderService orderService=new OrderServerImpl();

    @Test
    public void testcreateOrder() {
        Cart c=new Cart();

        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(2,"test2",1,new BigDecimal(100),new BigDecimal(100)));
        orderService.createOrder(c,1);
        JDBCUtils.commitAndClose();
    }

    @Test
    public void testshowAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        JDBCUtils.commitAndClose();
        System.out.println(orders);
    }



    @Test
    public void testshowOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("1636692494612");
        JDBCUtils.commitAndClose();
        System.out.println(orderItems);
    }

    @Test
    public void testshowMyOrders() {
        List<Order> orders = orderService.showMyOrders(1);
        JDBCUtils.commitAndClose();
        System.out.println(orders);
    }

    @Test
    public void testreceiverOrder() {
        orderService.receiverOrder("1636692494612");
        JDBCUtils.commitAndClose();
    }
}