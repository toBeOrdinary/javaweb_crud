package com.zxd.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxd.shop.dao.UserDao;

public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String islogin=(String) request.getSession().getAttribute("isLogin");
		
		//当session中不存在isLogin对应的值，为null
		if(islogin==null) {
			System.out.println("空session");
			//未登录，跳转到登录界面
			response.getWriter().write("还未登录，将在3秒内跳转到登录界面");
			response.setHeader("refresh", "3,url=/shop/login.jsp");
		}else if(islogin.equals("yes")) {//已经登录，直接添加到购物车
			UserDao dao = new UserDao();
			
			//获取提交表单中的checkbox选取值，封装到values数组，遍历输出
			 String[] products = request.getParameterValues("product") ;
			   if(products!=null && products.length>0) {
			        for(int i= 0;i<products.length;i++) {
				        System.out.println(products[i]);//将选取的商品打印到控制台
				        //从session域中获取用户名
						String name=(String) request.getSession().getAttribute("username");
						dao.insertCart(products[i], name);//执行将商品插入数据库的操作
			        }
			   }
			   response.getWriter().write("加购成功，"
					   +"<a href='/shop/MyCartServlet'>点击跳转到购物车页面</a>");
		}else {
			//未登录，跳转到登录界面
			response.getWriter().write("还未登录，将在3秒内跳转到登录界面"+"<a href='/shop/login.jsp'>点击登录</a>");
			response.setHeader("refresh", "3,url=/shop/login.jsp");
		}
	}

}
