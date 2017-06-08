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
 * ��¼ҳ���ҳ�����
 * ��Ҫ����web.xml�ļ�������
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
		// ��ȡ�û���������
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		
		// �ж��û�������Ƿ�Ϊ�գ������������ת
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			req.setAttribute("error", "�û���������Ϊ�գ�");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return;
		}
		
		// ���ڱ����û��������Ϣ
		req.setAttribute("userName", userName);
		req.setAttribute("password", password);
		
		// �û�������ʱ�����ת��¼
		// Ĭ���˺�Ϊadmin admin
		User user = new User(userName,password);
		Connection con = null;
		try {
			con = (Connection) dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if (currentUser == null) {
				req.setAttribute("error", "�û������������");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} else {
				// ��ת����ҳ
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
