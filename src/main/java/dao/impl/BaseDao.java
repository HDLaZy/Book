package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author liaoke
 * @create 2021-10-27-9:41
 */
public  abstract  class BaseDao {

    //使用DBUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();


    //插入 修改 删除
    public  int update(String sql,Object ... args){
        //System.out.println("BaseDao程序在:【"+Thread.currentThread().getName()+"】线程中");

        Connection conn= JDBCUtils.getConn();
        try {
            return   queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    public <T> T  query(Class<T> type,String sql,Object ...args){

        Connection conn= JDBCUtils.getConn();
        try {
            return   queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public <T> List<T> queryList(Class<T> type, String sql, Object ...args){

        Connection conn= JDBCUtils.getConn();
        try {
            return   queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public Object queryValue(String sql,Object ...args){

        Connection conn = JDBCUtils.getConn();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
