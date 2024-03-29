<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>HDLaZyの网上书城注册页面</title>

		<%@include file="/pages/common/head.jsp"%>

		<script type="text/javascript">
			// 页面加载完成之后
			$(function () {

				//给用户名框加上失去焦点事件
				$("#username").blur(function () {
					//1:获取用户名
					var username = this.value;
					$.getJSON("http://localhost:8080/Book/userservlet","action=ajaxExistsUsername&username="+username,function (data){
						if(data.existsUsername){
							$("span.errorMsg").text("用户名已存在！");
						}
					});
				});
				
				//验证码刷新
				$("#codeImg").click(function () {
					//在事件响应的function函数中存在一个this对象，就是当前正在响应事件的dom对象
					//src是当前验证码的图片的路径
					this.src="${basePath}/kaptchaServlet.jpg?d="+new Date();
				})
				
				// 给注册绑定单击事件
				$("#sub_btn").click(function () {
					// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var usernameText = $("#username").val();
					//2 创建正则表达式对象
					var usernamePatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!usernamePatt.test(usernameText)) {
						//4 提示用户结果
						$("span.errorMsg").text("用户名不合法！");

						return false;
					}

					// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var passwordText = $("#password").val();
					//2 创建正则表达式对象
					var passwordPatt = /^\w{5,12}$/;
					//3 使用test方法验证
					if (!passwordPatt.test(passwordText)) {
						//4 提示用户结果
						$("span.errorMsg").text("密码不合法！");

						return false;
					}

					// 验证确认密码：和密码相同
					//1 获取确认密码内容
					var repwdText = $("#repwd").val();
					//2 和密码相比较
					if (repwdText != passwordText) {
						//3 提示用户
						$("span.errorMsg").text("两次密码不一致！");

						return false;
					}

					// 邮箱验证：xxxxx@xxx.com
					//1 获取邮箱里的内容
					var emailText = $("#email").val();
					//2 创建正则表达式对象
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					//3 使用test方法验证是否合法
					if (!emailPatt.test(emailText)) {
						//4 提示用户
						$("span.errorMsg").text("邮箱格式不合法！");

						return false;
					}

					// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
					var codeText = $("#code").val();

					//去掉验证码前后空格
					codeText = $.trim(codeText);

					if (codeText == null || codeText == "") {
						//4 提示用户
						$("span.errorMsg").text("验证码不能为空！");

						return false;
					}

					// 去掉错误信息
					$("span.errorMsg").text("");

				});

			});

		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>


			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册HDLaZyの网上书城会员</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>HDLaZyの网上书城会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
								</span>
							</div>
							<div class="form">
								<form action="userservlet" method="post">
									<input hidden name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"
<%--											<%=request.getAttribute("msg")==null?"":request.getAttribute("username")%>--%>
									/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password"
<%--										   value="${requestScope.password}"--%>
<%--											<%=request.getAttribute("msg")==null?"":request.getAttribute("password")%>--%>
									/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd"
										   value="<%=request.getAttribute("msg")==null?"":request.getAttribute("password")%>"
									/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="<%=request.getAttribute("msg")==null?"":request.getAttribute("email")%>"
									/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px" id="code"/>
									<img id="codeImg"  alt=""  src="kaptchaServlet.jpg"  style="float: right;margin-right: 40px;width: 110px; height: 30px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
			<%@include file="/pages/common/foot.jsp"%>
	</body>
</html>