<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%@include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

		<table align="center">
			<form action="userservlet" method="post">
				<tr>
					<input hidden name="action" value="ManagerPass">
				</tr>
				<tr>
					<td>管理员密码：</td>
					<td><input type="password" name="managerPass"></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="提交">
					</td>
				</tr>
	        </form>
			<tr>
				<td>
					<a href="index.jsp">返回商城</a>
				</td>
			</tr>
		</table>
</body>
</html>