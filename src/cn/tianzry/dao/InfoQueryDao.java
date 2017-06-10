package cn.tianzry.dao;



import java.sql.*;

import cn.tianzry.model.PageBean;
import cn.tianzry.model.StudentInfo;
import cn.tianzry.util.StringUtil;

public class InfoQueryDao {
	/**
	 * �����������ƵĲ��ң�ֻ���ҵ�pҳrows������
	 * @param con
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public ResultSet studentInfo(Connection con, PageBean pageBean, StudentInfo studentInfo) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_studentinfo");
		
		// ��������
		if (StringUtil.isNotEmpty(studentInfo.getName())) {
			sb.append(" and name like '%" + studentInfo.getName() + "%'");
		}
		
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
			
		}
		
		// ע������Ǹ��滻���������and���滻��һ��Ϊwhere��ѯ��û�еĻ�������
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * �������ݿ��е�ѧ����Ϣ����
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int studentInfoCount(Connection con, StudentInfo studentInfo) throws Exception {
		StringBuffer sql = new StringBuffer("select count(*) as total from t_studentinfo");
		
		
		// ����û��������������ݲ�Ϊ��
		if (StringUtil.isNotEmpty(studentInfo.getName())) {
			// ע��sql���andǰ��Ŀո񰡣���Ȼ������You have an error in your SQL syntax;
			sql.append(" and name like '%"+studentInfo.getName()+"%'");
		}
		
		// ��������������ݣ����滻��and
		PreparedStatement pstmt = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	// ɾ������,ע��ɾ������д��
	public int studentInfoDelete(Connection con, String deleteIds) throws Exception {
		String sql = "delete from t_studentInfo where id in(" + deleteIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
