<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//删除提示
			$("a.deleteItem").click(function () {
				return confirm("确定删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");
			});

			//清空提示
			$("#cleanCart").click(function () {
				return confirm("确定清空购物车吗？？？");
			});

			//修改商品数量的文本框信息改变事件
			$(".updateCount").change(function () {
				let name=$(this).parent().parent().find("td:first").text();
				var count=this.value;
				var id = $(this).attr("bookId");
			    if( confirm("确定将【"+name+"】的数量修改为【"+count+"】")){
					//发起修改请求
					location.href="http://localhost:8080/Book/cartServlet?action=updateCount&count="+count+"&id="+id;
				}else{
					//不修改，恢复之前的值
					this.value=this.defaultValue;
				}
			});
		});
		
	</script>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">购物车</span>

		<!-- 静态包含-->
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%--购物车非空--%>
			<c:if test="${not empty sessionScope.Cart.items}">
				<c:forEach items="${sessionScope.Cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>

							<%--商品数量的输入框--%>
							<input bookId="${entry.value.id}" class="updateCount" style="width: 50px" type="text" value="${entry.value.count}">

						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<%--购物车空--%>
			<c:if test="${empty sessionScope.Cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">购物车还没有宝贝,立刻去选购！！！</a></td>
				</tr>
			</c:if>
		</table>

		<%--购物车非空--%>
		<c:if test="${not empty sessionScope.Cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.Cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.Cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="cleanCart" href="cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>