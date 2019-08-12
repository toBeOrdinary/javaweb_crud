<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
	<style type="text/css">
		.divForm{
			position: absolute;
			width: 200px;
			height: 200px;
			border: 1px solid black;
			text-align: center;/*(让div中的内容居中)*/
			top: 50%;
			left: 50%;
			margin-top: -200px;
			margin-left: -150px;
			}
		.divLink{
			float:right
		}
	</style>
</head>
<body>
	
	<div class="divForm">
	<div class="divLink">
	
	<!-- 第一次进入首页面，为session域中  isLogin设置初始值 -->
	<%
		if(request.getSession().getAttribute("isLogin")==null){
			request.getSession().setAttribute("isLogin","no");	
		}		
	%>
	<c:if test="${sessionScope.isLogin=='no' }">
		<a href="/shop/register.jsp">新用户注册</a>&nbsp;
	</c:if>
	<!-- 通过获取session域中   isLogin状态    展示登录或注销的链接 -->
		<c:choose>
			<c:when test="${sessionScope.isLogin=='yes' }">
					<a href="/shop/LogoutServlet">${sessionScope.username }_注销</a>&nbsp;
			</c:when>
			<c:otherwise>
					<a href="/shop/login.jsp">登录</a>&nbsp;
			</c:otherwise>
		</c:choose>
		<!-- 仅登录状态显示购物车 -->
		<c:if test="${sessionScope.isLogin=='yes' }">
			<a href="/shop/MyCartServlet">购物车</a>
		</c:if>
	</div>
	<!-- 提交到BuyServlet -->
	<form action="/shop/AddCartServlet" method="post">
		<br />选择添加到购物车<br /><br />
		书籍    <input type="checkbox" name="product" value="书籍"><br />
		水果    <input type="checkbox" name="product" value="水果"><br />
		饰品    <input type="checkbox" name="product" value="饰品"><br />
		零食    <input type="checkbox" name="product" value="零食"><br /><br />
		<input type="submit" name="submit" value="提交">&emsp;&emsp;
	</form>
	</div>
</body> 
</html>