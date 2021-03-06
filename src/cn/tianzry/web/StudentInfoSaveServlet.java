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

/**
 * 插入数据的服务器端
 * @author tianz
 *
 */
public class StudentInfoSaveServlet extends HttpServlet{

	DbUtil dbUtil = new DbUtil();
	StudentInfoDao infoQueryDao = new StudentInfoDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 注意这里是post
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 注意这个编码的指定，否则会出现乱码问题
		req.setCharacterEncoding("utf-8");
		
		//添加的内容
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String birthday = req.getParameter("birthday");
		String grade = req.getParameter("grade"); //注意这里可以字符串转数字
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
		StudentInfo studentInfo = new StudentInfo(id, name, sex, birthday, Integer.parseInt(grade),phone, address);
		
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			int saveNum = infoQueryDao.studentInfoAdd(con, studentInfo);
			
			
			if (saveNum > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMsg", "添加失败，请检查！");
			}
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
