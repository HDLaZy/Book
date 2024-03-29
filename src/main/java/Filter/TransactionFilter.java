package Filter;

import utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 事务管理的过滤器
 * @author liaoke
 * @create 2021-11-11-19:21
 */
public class TransactionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollBackAndClose();
            e.printStackTrace();
            //异常抛给Tomcat服务器
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {


    }
}
