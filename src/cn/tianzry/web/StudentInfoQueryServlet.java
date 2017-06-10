package cn.tianzry.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tianzry.dao.StudentInfoDao;
import cn.tianzry.model.PageBean;
import cn.tianzry.model.StudentInfo;
import cn.tianzry.util.DbUtil;
import cn.tianzry.util.JsonUtil;
import cn.tianzry.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentInfoQueryServlet extends HttpServlet{

	DbUtil dbUtil = new DbUtil();
	StudentInfoDao infoQueryDao = new StudentInfoDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 注意这里是post
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 发送了page和rows每页记录数 两个参数
		
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		
		// 获取数据库返回的搜索内容
		String searchStr = req.getParameter("searchStr");
		
		if (searchStr == null) {
			searchStr = "";
		}

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(infoQueryDao.studentInfoQuery(con, pageBean, searchStr));
			int total = infoQueryDao.studentInfoCount(con, searchStr); //TODO 
			// 将数据写入Json对象中
			result.put("rows", jsonArray);
			result.put("total", total);
			// 将Json对象写入response中
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
