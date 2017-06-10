package cn.tianzry.dao;



import java.sql.*;

import cn.tianzry.model.PageBean;
import cn.tianzry.model.StudentInfo;
import cn.tianzry.util.StringUtil;

public class StudentInfoDao {
	/**
	 * �����������ƵĲ��ң�ֻ���ҵ�pҳrows������
	 * @param con
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public ResultSet studentInfoQuery(Connection con, PageBean pageBean, String searchStr) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_studentinfo");
		
		// �������ݣ����������ģ������ƥ��
		if (StringUtil.isNotEmpty(searchStr)) {
			if (searchStr.matches("\\d+")) {
				sb.append(" and id like '%"+searchStr+"%'"+
						" or birthday like '%"+searchStr+"%'"+
						" or grade="+Integer.parseInt(searchStr)+
						" or phone like '%"+searchStr+"%'"+
						" or address like '%"+searchStr+"%'"
						);
			} else {
				sb.append(" and id like '%"+searchStr+"%'"+
							" or name like '%"+ searchStr + "%'"+ 
							" or sex like '"+searchStr+"'"+
							" or birthday like '%"+searchStr+"%'"+
							" or address like '%"+searchStr+"%'"
						);
			}

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
	public int studentInfoCount(Connection con, String searchStr) throws Exception {
		StringBuffer sql = new StringBuffer("select count(*) as total from t_studentinfo");
		
		
		// ����û��������������ݲ�Ϊ��
		if (StringUtil.isNotEmpty(searchStr)) {
			// ע��sql���andǰ��Ŀո񰡣���Ȼ������You have an error in your SQL syntax;
			if (searchStr.matches("\\d+")) {
				sql.append(" and id like '%"+searchStr+"%'"+ 
						" or birthday like '%"+searchStr+"%'"+
						" or grade="+Integer.parseInt(searchStr)+
						" or phone like '%"+searchStr+"%'"+
						" or address like '%"+searchStr+"%'"
						);
			} else {
				sql.append(" and id like '%"+searchStr+"%'"+ 
						" or name like '%"+ searchStr + "%'"+ 
						" or sex like '"+searchStr+"'"+
						" or birthday like '%"+searchStr+"%'"+
						" or address like '%"+searchStr+"%'"
						);
			}
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
	// ����ѡ�е�ѧ����ѧ�Ž����޸ģ���ˣ�ѧ���ǲ������޸ĵ�
	public int studentInfoDelete(Connection con, String deleteIds) throws Exception {
		String sql = "delete from t_studentInfo where id in(" + deleteIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	/**
	 * �������ݵ����ݿ�
	 * @param con
	 * @param studentInfo
	 * @return
	 * @throws Exception
	 */
	public int studentInfoAdd(Connection con, StudentInfo studentInfo) throws Exception {
		// �����ֵΪ��ѧ�š��������Ա����ա����֣���ֵ�����绰��������һ��7��
		String sql = "insert into t_studentInfo values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, studentInfo.getId());
		pstmt.setString(2, studentInfo.getName());
		pstmt.setString(3, studentInfo.getSex());
		pstmt.setString(4, studentInfo.getBirthday());
		pstmt.setInt(5, studentInfo.getGrade());
		pstmt.setString(6, studentInfo.getPhone());
		pstmt.setString(7, studentInfo.getAddress());
		
		return pstmt.executeUpdate();
	}
	/**
	 * �޸�����
	 * @param con
	 * @param studentInfo
	 * @return
	 * @throws Exception
	 */
	public int studengInfoModify(Connection con, StudentInfo studentInfo) throws Exception{
		String sql = "update t_studentInfo set name=?, sex=?, birthday=?, grade=?, phone=?, address=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, studentInfo.getName());
		pstmt.setString(2, studentInfo.getSex());
		pstmt.setString(3, studentInfo.getBirthday());
		pstmt.setInt(4, studentInfo.getGrade());
		pstmt.setString(5, studentInfo.getPhone());
		pstmt.setString(6, studentInfo.getAddress());
		pstmt.setString(7, studentInfo.getId());
		
		return pstmt.executeUpdate();
	}
	
}
