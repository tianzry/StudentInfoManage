package cn.tianzry.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tianzry.dao.InfoQueryDao;
import cn.tianzry.model.PageBean;
import cn.tianzry.model.StudentInfo;
import cn.tianzry.util.DbUtil;
import cn.tianzry.util.JsonUtil;
import cn.tianzry.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentInfoDeleteServlet extends HttpServlet{

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
		
		String deleteIds = req.getParameter("deleteIds");
			
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			int deleteNum = infoQueryDao.studentInfoDelete(con, deleteIds);
			
			// ����ɾ���ķ���ֵ��ȷ���Ƿ�ɾ���ɹ�
			if (deleteNum > 0) {
				result.put("success", "true");
				result.put("deleteNum", deleteNum);
			} else {
				result.put("errorMsg", "ɾ��ʧ�ܣ����飡");
			}
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
