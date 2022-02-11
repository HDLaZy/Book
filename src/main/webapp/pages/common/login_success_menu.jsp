<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/10/27
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临HDLaZyの网上书城</span>
    <a href="userservlet?action=loginOut">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
