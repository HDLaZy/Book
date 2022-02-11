package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author liaoke
 * @create 2021-10-27-9:18
 */
public class JDBCUtils {


    private static DruidDataSource dataSource;

    //ThreadLocal的创建 泛型为JDBC连接
    private static ThreadLocal<Connection> conns=new ThreadLocal<>();


    static {

        try {
            Properties properties=new Properties();

            //读取配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//获取连接
    public static Connection  getConn(){

       Connection conn= conns.get();
       if(conn==null){
           try {
               conn=dataSource.getConnection();
               conn.setAutoCommit(false);//手动管理
               conns.set(conn);//将连接写入ThreadLocal中
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       return conn;
    }

//提交事务，关闭释放连接
    public static void commitAndClose(){

        //得到链接对象
        Connection connection = conns.get();
        if(connection!=null){//说明之前使用过数据库

            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        conns.remove();//需要移除在ThreadLocal的Connection
    }

    //回滚事务，关闭释放连接
    public static void rollBackAndClose(){
        //得到链接对象
        Connection connection = conns.get();
        if(connection!=null){//说明之前使用过数据库

            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        conns.remove();//需要移除在ThreadLocal的Connection
    }
}
