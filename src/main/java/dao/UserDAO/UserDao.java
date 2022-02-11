package dao.UserDAO;

import pojo.User;

/**
 * @author liaoke
 * @create 2021-10-27-9:58
 */
public interface UserDao {


    //根据用户名查找用户 避免重名
    public User queryUserByUsername(String username);
    //登录 根据用户名密码查询
    public User queryUserByUsernameAndPassword(String username,String password);
    //注册User
    public int saveUser(User user);
}
