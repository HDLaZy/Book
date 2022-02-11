<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">

			<span class="wel_word">我的订单</span>

		<!-- 静态包含-->
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>


	<div id="main">

		<table>
			<tr>
				<td>单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td colspan="2">操作</td>
			</tr>
			<c:if test="${empty sessionScope.userOrders}">
				<tr>
					<td colspan="5"><a href="index.jsp">您暂时未买东西，立刻去选购！！！</a></td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.userOrders}">
				<c:forEach items="${sessionScope.userOrders}" var="order">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<td>
					<c:choose>
						<c:when test="${order.status==2}">
							已签收
						</c:when>
						<c:otherwise>
							未签收
						</c:otherwise>
					</c:choose>
				</td>
				<td><a href="orderServlet?action=showOrderDetail&userOrderId=${order.orderId}">详情</a> </td>
				<td><a id="receive" href="orderServlet?action=receiveOrder&userOrderId=${order.orderId}">确认收货</a></td>
			</tr>
				</c:forEach>
			</c:if>
		</table>
		
	
	</div>

	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>