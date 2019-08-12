package com.zxd.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxd.shop.bean.User;
import com.zxd.shop.dao.UserDao;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//注册功能实现，外加jsp页面未实现
		
		//获取请求表单中的数据  username 和  password
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		//将传递的username和password封装到user对象中，通过dao层的insert()方法实现注册功能
		User user = new User();
		user.setUserName(username);
		user.setPassWord(password);
		//向数据库插入用户
		UserDao dao = new UserDao();
		boolean b = dao.insert(user);		
		if(b==true) {
			//插入记录成功后返回true  即注册成功，跳转到登录界面
			response.getWriter().write("注册成功，将在3秒内跳转到登录界面");
			response.setHeader("refresh", "3;URL=/shop/login.jsp");
		}else {
			//若数据库存在与从request获取的username相同的值，返回false，注册失败
			response.getWriter().write("注册失败，该用户名已被注册！<a href='/shop/register.jsp'>重新注册</a>");
		}
		
	}

}
