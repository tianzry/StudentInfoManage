package cn.tianzry.dao;



import java.sql.*;

import cn.tianzry.model.PageBean;
import cn.tianzry.model.StudentInfo;
import cn.tianzry.util.StringUtil;

public class InfoQueryDao {
	/**
	 * 进行条件限制的查找，只查找第p页rows条数据
	 * @param con
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public ResultSet studentInfo(Connection con, PageBean pageBean, StudentInfo studentInfo) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_studentinfo");
		
		// 搜索内容
		if (StringUtil.isNotEmpty(studentInfo.getName())) {
			sb.append(" and name like '%" + studentInfo.getName() + "%'");
		}
		
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
			
		}
		
		// 注意最后那个替换，如果存在and则替换第一个为where查询，没有的话不操作
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * 计算数据库中的学生信息总数
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int studentInfoCount(Connection con, StudentInfo studentInfo) throws Exception {
		StringBuffer sql = new StringBuffer("select count(*) as total from t_studentinfo");
		
		
		// 如果用户输入搜索框内容不为空
		if (StringUtil.isNotEmpty(studentInfo.getName())) {
			// 注意sql语句and前面的空格啊，不然出错了You have an error in your SQL syntax;
			sql.append(" and name like '%"+studentInfo.getName()+"%'");
		}
		
		// 如果存在搜索内容，则替换掉and
		PreparedStatement pstmt = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	// 删除函数,注意删除语句的写法
	public int studentInfoDelete(Connection con, String deleteIds) throws Exception {
		String sql = "delete from t_studentInfo where id in(" + deleteIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
