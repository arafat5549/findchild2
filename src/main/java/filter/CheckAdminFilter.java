package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.utils.Globals;
import com.xjxy.web.UserController;

/**
 * 检测后台管理员登录
 * @author wyy
 * 2017年4月19日
 *
 */
public class CheckAdminFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//如果用户没有登录-我就将他强制返回登录页面
		if(req instanceof HttpServletRequest){
			HttpServletRequest httpReq = (HttpServletRequest)req;
			HttpServletResponse httpResp = (HttpServletResponse)resp;
			
			boolean flag = Globals.CheckLoginAdmin(httpReq);
			if(!flag){
				httpResp.sendRedirect(httpReq.getContextPath());
				//req.getRequestDispatcher(UserController.VIEW_PATH+"login.jsp").forward(req, resp);
				return; 
			}
		}
		
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
