package dao.impl;

import dao.UserDAO.UserDao;
import pojo.User;

/**
 * @author liaoke
 * @create 2021-10-27-10:02
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {

        String sql="select id,username,password,email from t_user where username=?";

        return query(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {

        String sql="select id,username,password,email from t_user where username=? and password=?";

        return query(User.class, sql, username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="INSERT INTO t_user (username,password,email) VALUES(?,?,?)";

        return  update(sql,user.getUsername(),user.getPassword(),user.getEmail());

    }
}
