package dao.UserDAO;

import dao.impl.UserDaoImpl;
import junit.framework.TestCase;
import org.junit.Test;
import pojo.User;
import utils.JDBCUtils;

/**
 * @author liaoke
 * @create 2021-10-27-10:07
 */
public class UserDaoTest extends TestCase {

    @Test
    public void testqueryUserByUsername() {
        UserDao userDAO=new UserDaoImpl();

        if(userDAO.queryUserByUsername("liaoke666")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }

    }

    @Test
    public void testqueryUserByUsernameAndPassword() {

        UserDao userDAO=new UserDaoImpl();

        if(userDAO.queryUserByUsernameAndPassword("liaoke666", "liaoke666")==null){
            System.out.println("用户不存在");
        }else{
            System.out.println("登陆成功");
        }



    }

    @Test
    public void testsaveUser() {

        UserDao userDAO=new UserDaoImpl();

        User u=new User(null,"hdlazy777","hdlazy777","hdlazy@qq.com");

        int i = userDAO.saveUser(u);

        if(i!=-1){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }


    }
}