<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		
		$(function () {

			$("a.deleteClass").click(function () {
				/*
				confirm确认提示框函数
				参数为提示内容
				返回true确实
				返回false取消
				 */

				return confirm("确定要删除["+$(this).parent().parent().find("td:first").text()+"]");



			});

		});
		
	</script>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		

			<c:forEach items="${requestScope.page.items}" var="book">

				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookservlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookservlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>


			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

		<%--	静态包含分页条--%>

		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>