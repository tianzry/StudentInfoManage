package cn.tianzry.util;

import java.sql.Connection;
import java.sql.DriverManager;

//TODO 可以增加一个操作，就是建立数据库以及建立表
// 使用 if not exists 来搭配即可
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_studentInfo";
	private String dbUserName="root";
	private String dbPassword="root";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	// 这是一个测试函数，用来测试数据库是否连接上，直接执行这个类即可
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
