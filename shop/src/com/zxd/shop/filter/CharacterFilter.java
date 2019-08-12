package com.zxd.shop.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


//解决全站中文乱码问题-----------
public class CharacterFilter implements Filter {
    public CharacterFilter() {
    }
	public void destroy() {
		
	}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//拦截所有请求  解决全站乱码问题
		//指定request和response的编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		CharacterRequest characterRequest=new CharacterRequest(request);
		chain.doFilter(characterRequest, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
	class CharacterRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		public CharacterRequest(HttpServletRequest request) {
			super(request);
			this.request=request;
		}
		public String getParameter(String name) {
			String value=super.getParameter(name);
			if(value==null)
				return null;
			String method =super.getMethod();
			if("get".equalsIgnoreCase(method)) {
				try {
					value=new String(value.getBytes("iso-8859-1"),"utf-8");
				}catch(UnsupportedEncodingException e){
					throw new RuntimeException(e);
			}
		}
			return value;
	}

	}