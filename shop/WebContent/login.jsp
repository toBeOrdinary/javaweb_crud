<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>

    	
</head>
<body>
<div align="center">
	登录界面 <br>    	
    	
    	<script type="text/javascript">
    		function myfunction(){
    			if(document.getElementById("username").value==''){
    				alert("用户名不能为空");
    				return false;    				
    			}
    			if(document.getElementById("password").value==''){
    				alert("密码不能为空");
    				return false;    				
    			}		
    			return true;
    		}
    	</script>

    	<form action="/shop/LoginServlet" method="post" >
	    	用户名:<input type="text" name="username"  id="username"/><br>
	    	密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" id="password"/><br>
	    	<input type="submit" value="登录" onclick="return myfunction()" />
   		</form>
    </div>
</body>
</html>