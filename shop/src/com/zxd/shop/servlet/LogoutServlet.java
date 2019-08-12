package com.zxd.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//注销成功后  将session域中  isLogin状态设置为no
		String login="no";
		request.getSession().setAttribute("isLogin", login);
		request.getSession().removeAttribute("username");

		response.setHeader("refresh", "0,url=/shop/index.jsp");
		
		//response.getWriter().write("注销成功     ");
		//response.getWriter().write("&emsp;<a href='/shop/index.jsp'>商城首页</a>");

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
