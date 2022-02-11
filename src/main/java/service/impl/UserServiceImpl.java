package service.impl;

import dao.UserDAO.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

/**
 * @author liaoke
 * @create 2021-10-27-10:23
 */
public class UserServiceImpl implements UserService {

    private UserDao userDAO=new UserDaoImpl();

    @Override
    public void  registUser(User user) {
          userDAO.saveUser(user);
    }

    @Override
    public User login(User user) {

    return userDAO.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());

    }

    @Override
    public boolean existsUsername(String username) {

        if(userDAO.queryUserByUsername(username)==null){
            return false;
        }else{
            return true;
        }

    }
}
