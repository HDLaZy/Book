import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author liaoke
 * @create 2021-10-27-9:36
 */
public class JDBCUtilsTest {


    @Test
    public void test(){

        for(int i=0;i<100;i++){
            Connection conn = JDBCUtils.getConn();
            System.out.println(conn);

        }
    }



}
