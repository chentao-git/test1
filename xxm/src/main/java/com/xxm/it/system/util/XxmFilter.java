package com.xxm.it.system.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 路径拦截器
 * 
 * @author Administrator
 *
 */
public class XxmFilter implements Filter {

	private static Logger logger = Logger.getLogger(XxmFilter.class);

	// 排除请求方法集合
	private static String[] filterRequest = new String[] { "login", "bankAudit", "startAuditResult", "endAuditResult",
			"signInfo", "auditInfo", "loaninfoInform", "applyInfo", "bankInsure" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("----过滤器初始化----");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 对request和response进行一些预处理
		request.setCharacterEncoding(XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
		response.setCharacterEncoding(XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
		response.setContentType("text/html;charset=" + XxmConstant.BaseDataConstant.INPUT_STREAM_READER_ENCODING);
		// 判断是否是登录界面
		String uri = request.getRequestURI();
		logger.info("request uri:" + uri);
		// HTML页面过滤
		if (uri.contains(".html")) {
			if (uri.contains("login.html")) {
				chain.doFilter(request, response); // 让目标资源执行，放行
				return;
			}
			// 判断当前用户是否登录
			String userId = XxmUser.getCurrentUserId();
			if (null != userId && !"".equals(userId)) {
				chain.doFilter(request, response); // 让目标资源执行，放行
				return;
			}
			// 跳转到主页
			response.sendRedirect(request.getContextPath() + "/main/login.html");
		}
		// rest请求路径
		if (uri.contains("/rest/")) {
			// 过滤部分请求方法
			for (int i = 0; i < filterRequest.length; i++) {
				if (uri.contains(filterRequest[i])) {
					chain.doFilter(request, response); // 让目标资源执行，放行
					return;
				}
			}
			// 判断当前用户是否登录
			String userId = XxmUser.getCurrentUserId();
			if (null != userId && !"".equals(userId)) {
				chain.doFilter(request, response); // 让目标资源执行，放行
				return;
			}
			response.setIntHeader("REQUIRES_AUTH", 1);
			response.setHeader("REQUIRES_AUTH_URL", request.getContextPath() + "/main/login.html");
			return;
		}
		chain.doFilter(request, response); // 让目标资源执行，放行
	}

	@Override
	public void destroy() {
		logger.info("----过滤器销毁----");
	}

}
