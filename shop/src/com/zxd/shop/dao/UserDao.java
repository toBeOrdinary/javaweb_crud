package com.zxd.shop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.zxd.shop.bean.User;
import com.zxd.shop.utils.JDBCUtils;

//实现对数据库的操作，  这里只实现了 插入、查询功能
public class UserDao {
	//----------------------添加用户----------------------------------------------
	public boolean insert(User user) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {//获得数据的连接
			conn = JDBCUtils.getConnection();
			//获得Statement对象
			stmt = conn.createStatement();
			//发送sql语句
			//insert into users(username,password) values('testname','testpwd')
			String sql = "insert into users(username,password) values("
					+ "'"+user.getUserName()+"','"+user.getPassWord()+"')";
			int num = stmt.executeUpdate(sql);
			if(num>0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt,conn);
		}
		return false;
	}
	//---------------------添加购物车商品--------------
	//传递商品名称，传递用户名，根据用户名向数据库插入商品
	public boolean insertCart(String product,String username) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {//获得数据的连接
			conn = JDBCUtils.getConnection();
			//获得Statement对象
			stmt = conn.createStatement();
			//发送sql语句，向cart字段追加商品
			//UPDATE users SET cart= CONCAT(cart,'books') WHERE username='admin';
			String sql = "update users set cart=concat(cart,'"+product+"、') where username='"+username+"'";
			int num = stmt.executeUpdate(sql);
			if(num>0) {
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt,conn);
		}
		return false;
	}
	//--------------------根据username和password查询用户-------------------------------
	public User find(String username,String password) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//获得数据的连接
			conn = JDBCUtils.getConnection();
			//获得Statement对象
			stmt=conn.createStatement();
			//发送sql语句
			String sql="select*from users where username='"+username+"' and password='"+password+"'";
			rs = stmt.executeQuery(sql);
			//处理结果集
			while(rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				return user;
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt,conn);
		}
		return null;
	}
	//根据指定用户名 获取cart字段值
	public String findCart(String username) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//获得数据的连接
			conn = JDBCUtils.getConnection();
			//获得Statement对象
			stmt=conn.createStatement();
			//发送sql语句
			String sql="select cart from users where username='"+username+"'";
			System.out.println(username);//测试用户名编码
			rs = stmt.executeQuery(sql);
			//处理结果集
			while(rs.next()) {
				String product=rs.getString("cart");//根据cart列名获取加购的商品
				return product;
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt,conn);
		}
		return null;
	}

}
