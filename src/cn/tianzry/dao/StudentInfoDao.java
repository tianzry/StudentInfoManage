package cn.tianzry.dao;



import java.sql.*;

import cn.tianzry.model.PageBean;
import cn.tianzry.model.StudentInfo;
import cn.tianzry.util.StringUtil;

public class StudentInfoDao {
	/**
	 * 进行条件限制的查找，只查找第p页rows条数据
	 * @param con
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public ResultSet studentInfoQuery(Connection con, PageBean pageBean, String searchStr) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_studentinfo");
		
		// 搜索内容，这里可以做模糊搜索匹配
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
	public int studentInfoCount(Connection con, String searchStr) throws Exception {
		StringBuffer sql = new StringBuffer("select count(*) as total from t_studentinfo");
		
		
		// 如果用户输入搜索框内容不为空
		if (StringUtil.isNotEmpty(searchStr)) {
			// 注意sql语句and前面的空格啊，不然出错了You have an error in your SQL syntax;
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
	// 根据选中的学生的学号进行修改，因此，学号是不允许修改的
	public int studentInfoDelete(Connection con, String deleteIds) throws Exception {
		String sql = "delete from t_studentInfo where id in(" + deleteIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	/**
	 * 插入数据到数据库
	 * @param con
	 * @param studentInfo
	 * @return
	 * @throws Exception
	 */
	public int studentInfoAdd(Connection con, StudentInfo studentInfo) throws Exception {
		// 插入的值为：学号、姓名、性别、生日、均分（数值），电话、户籍，一共7个
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
	 * 修改数据
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
