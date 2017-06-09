package cn.tianzry.dao;



import java.sql.*;

import cn.tianzry.model.PageBean;

public class InfoQueryDao {
	/**
	 * �����������ƵĲ��ң�ֻ���ҵ�pҳrows������
	 * @param con
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public ResultSet studentInfo(Connection con, PageBean pageBean) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_studentinfo");
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
			
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * �������ݿ��е�ѧ����Ϣ����
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int studentInfoCount(Connection con) throws Exception {
		String sql = "select count(*) as total from t_studentinfo";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
}
