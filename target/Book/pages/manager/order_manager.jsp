<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">订单管理系统</span>
		<%@include file="/pages/common/manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>单号</td>
				<td>用户编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.allOrders}">
				<tr>
					<td colspan="5"><a href="index.jsp">暂时没有订单！！！</a></td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.allOrders}">
				<c:forEach items="${sessionScope.allOrders}" var="order">
					<tr>
						<td>${order.orderId}</td>
						<td>${order.userId}</td>
						<td>${order.createTime}</td>
						<td>${order.price}</td>
						<td><c:choose>
							<c:when test="${order.status==2}">
								已签收
							</c:when>
							<c:otherwise>
								未签收
							</c:otherwise>
						</c:choose></td>
						<td><a href="orderServlet?action=showOrderDetailByManager&orderId=${order.orderId}">详情</a> </td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>

	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>