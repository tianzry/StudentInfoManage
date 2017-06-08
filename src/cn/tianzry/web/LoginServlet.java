package cn.tianzry.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import cn.tianzry.dao.UserDao;
import cn.tianzry.model.User;
import cn.tianzry.util.DbUtil;
import cn.tianzry.util.StringUtil;

/**
 * 登录页面的页面操作
 * 需要配置web.xml文件来关联
 * @author tianz
 *
 */
public class LoginServlet extends HttpServlet{
	
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取用户名和密码
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		
		// 判断用户输入的是否为空，是则服务器跳转
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			req.setAttribute("error", "用户名或密码为空！");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		
		// 用于保存用户输入的信息
		req.setAttribute("userName", userName);
		req.setAttribute("password", password);
		
		// 用户有输入时候才跳转登录
		// 默认账号为admin admin
		User user = new User(userName,password);
		Connection con = null;
		try {
			con = (Connection) dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if (currentUser == null) {
				req.setAttribute("error", "用户名或密码错误！");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} else {
				// 跳转到首页
				resp.sendRedirect("home.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
