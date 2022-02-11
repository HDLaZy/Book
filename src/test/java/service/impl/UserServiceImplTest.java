package service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import pojo.User;
import service.UserService;
import utils.JDBCUtils;

import static org.junit.Assert.*;

/**
 * @author liaoke
 * @create 2021-10-27-10:26
 */
public class UserServiceImplTest extends TestCase {

    UserService userService=new UserServiceImpl();

    @Test
    public void testregistUser() {
        userService.registUser(new User(null,"test123","test123","test123@test.com"));
    }

    @Test
    public void testlogin() {
        User login = userService.login(new User(null, "test123", "test123", "test123@test.com"));
        if(login==null){
            System.out.println("登录失败");
        }else{
            System.out.println("登陆成功");
        }
    }

    @Test
    public void testexistsUsername() {
        boolean test123 = userService.existsUsername("liaoke666");
        if(test123==true){
            System.out.println("用户名存在");
        }else {
            System.out.println("用户名可用");
        }

        JDBCUtils.commitAndClose();
    }
}