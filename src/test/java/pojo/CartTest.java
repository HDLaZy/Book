package pojo;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author liaoke
 * @create 2021-11-09-23:11
 */
public class CartTest extends TestCase {



    @Test
    public void testaddItem() {

        Cart c=new Cart();

        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(2,"test2",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println(c);

    }

    @Test
    public void testdeleteItem() {
        Cart c=new Cart();

        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(2,"test2",1,new BigDecimal(100),new BigDecimal(100)));
        c.deleteItem(1);
        System.out.println(c);

    }

    @Test
    public void testclean() {
        Cart c=new Cart();

        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(2,"test2",1,new BigDecimal(100),new BigDecimal(100)));
        c.deleteItem(1);
        c.clean();
        System.out.println(c);
    }

    @Test
    public void testupdateCount() {
        Cart c=new Cart();

        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(1,"test1",1,new BigDecimal(100),new BigDecimal(100)));
        c.addItem(new CartItem(2,"test2",1,new BigDecimal(100),new BigDecimal(100)));
        c.deleteItem(1);
        c.clean();

        c.addItem(new CartItem(2,"test2",1,new BigDecimal(100),new BigDecimal(100)));
        c.updateCount(2,22);

        System.out.println(c);
    }
}