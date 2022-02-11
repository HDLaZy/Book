package service;

import pojo.User;

/**
 * @author liaoke
 * @create 2021-10-27-10:19
 */
public interface UserService {

    //用户注册
    public void registUser(User user);

    //用户登录
    public User login(User user);

    //检查用户名是否可用 返回true不可以用  false可用
    public boolean existsUsername(String username);
}
