package com.zxd.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxd.shop.bean.User;
import com.zxd.shop.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取请求表单中的数据  username 和  password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//调用dao层  find()方法 
		UserDao dao = new UserDao();
		User user = dao.find(username, password);
		
		if(user!=null) {//user不为空，即在数据库中找到用户名和密码都匹配的记录
			//登录成功后将用户名、登录状态保存到session对象中-------
			String login="yes";
			request.getSession().setAttribute("isLogin", login);
			request.getSession().setAttribute("username", username);			
			//登录成功、跳转到首页
			//response.getWriter().write(username+",你好！将在3秒内跳转到首页");
			response.setHeader("refresh", "0,url=/shop/index.jsp");
		}else {//数据库查无记录，跳转到登陆界面
			response.getWriter().write("用户名或密码错误，将在3秒内跳转到登录界面");
			response.setHeader("refresh", "3;URL=/shop/login.jsp");
		}	
	}
}