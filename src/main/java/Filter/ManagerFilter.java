package Filter;

import pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author liaoke
 * @create 2021-11-11-11:00
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @description 未登录不可以访问后台管理
     * @author HDLaZy
     * @updateTime 2021/11/11 11:02
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        User loginUser = (User) httpServletRequest.getSession().getAttribute("user");

        if(loginUser==null){
            //未登录 去登陆页面
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
            
        }
    }

    @Override
    public void destroy() {

    }
}
