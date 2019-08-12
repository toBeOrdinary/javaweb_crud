package com.zxd.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxd.shop.dao.UserDao;

public class MyCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取数据库中指定用户名的cart字段
		
		//获取session中username的值
		String username=(String) request.getSession().getAttribute("username");
		if(username!=null) {
			//username=name;		
			UserDao dao = new UserDao();
			String product = dao.findCart(username);
			response.getWriter().write("我的购物车："+product);
		}else {
			response.getWriter().write("还未登录");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
