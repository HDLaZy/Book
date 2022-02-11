package web;

import com.google.gson.Gson;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaoke
 * @create 2021-10-28-14:12
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser == null) {

            req.setAttribute("msg", "用户名或密码错误!!!");

            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamBean(req.getParameterMap(), new User());

        if (token != null && token.equalsIgnoreCase(code)) {


            if (userService.existsUsername(username)) {
                req.setAttribute("msg", "用户名已经存在！！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.setAttribute("password", password);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registUser(user);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }


        } else {

            req.setAttribute("msg", "验证码错误！！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

        }
    }


    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }


    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        String username = req.getParameter("username");
        //调用userService的方法
        boolean existsUsername = userService.existsUsername(username);
        //将返回的结果封阵为Map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        //转化为Json字符串传回
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);

    }

    protected void ManagerPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String passWord = req.getParameter("managerPass");

        if("LkBook".equals(passWord)){
            req.getRequestDispatcher("/pages/manager/managerSuccess.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
        }

    }

}



