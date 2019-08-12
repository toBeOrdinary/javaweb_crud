<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
</head>
<body><div align="center">
	注册界面 <br>    	
    	
    	<script type="text/javascript">
    		function myfunction()
    		{

 		       var pwd1 = document.getElementById("password").value;
 		       var pwd2 = document.getElementById("password1").value;
 		       if(pwd1!=pwd2){
 		       alert("两次输入的密码不一致！");
 		       return false;
 		       }
    			if(document.getElementById("username").value=='')
    			{
    				alert("用户名不能为空");
    				return false;    				
    			}
    			if(document.getElementById("password").value=='')
    			{
    				alert("密码不能为空");
    				return false;    				
    			}		
    			return true;
    		}
    	</script>

    	<form action="/shop/RegisterServlet" method="post" >
	    	用&nbsp;户&nbsp;名:<input type="text" name="username"  id="username"/><br>
	    	密&emsp;&emsp;码:<input type="password" name="password" id="password"/><br>
	                     确认密码:<input type="password" name="password1" id="password1"/><br>   	
	    	<input type="submit" value="注册" onclick="return myfunction()"/>
   		</form>
    </div>
</body>
</html>