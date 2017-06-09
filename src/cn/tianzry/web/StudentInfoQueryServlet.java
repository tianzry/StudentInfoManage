package cn.tianzry.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tianzry.dao.InfoQueryDao;
import cn.tianzry.model.PageBean;
import cn.tianzry.util.DbUtil;
import cn.tianzry.util.JsonUtil;
import cn.tianzry.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentInfoQueryServlet extends HttpServlet{

	DbUtil dbUtil = new DbUtil();
	InfoQueryDao infoQueryDao = new InfoQueryDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ע��������post
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post ������page��rowsÿҳ��¼�� ��������
		
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(infoQueryDao.studentInfo(con, pageBean));
			int total = infoQueryDao.studentInfoCount(con);
			// ������д��Json������
			result.put("rows", jsonArray);
			result.put("total", total);
			// ��Json����д��response��
			ResponseUtil.write(resp, result);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}