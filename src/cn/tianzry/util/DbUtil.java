package cn.tianzry.util;

import java.sql.Connection;
import java.sql.DriverManager;

//TODO ��������һ�����������ǽ������ݿ��Լ�������
// ʹ�� if not exists �����伴��
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_studentInfo";
	private String dbUserName="root";
	private String dbPassword="root";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * �ر����ݿ�����
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	// ����һ�����Ժ����������������ݿ��Ƿ������ϣ�ֱ��ִ������༴��
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
