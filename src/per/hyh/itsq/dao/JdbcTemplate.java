package per.hyh.itsq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {

	private JdbcTemplate() {
	}

	private static JdbcTemplate jdbcTemplate = null;

	private Connection conn = null;

	public Connection init() {

		try {
			// 1、加载数据库驱动
			Class.forName("oracle.jdbc.OracleDriver");
			// 2、获取连接
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@124.235.147.19:1521:orcl", "scott", "scott");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "scott");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate();
		}
		return jdbcTemplate.init();
	}
}
/*
 * create table tieba_user( user_id number(4) primary key, user_name
 * varchar2(30) not null, user_pwd varchar2(16), user_role number(1) );
 * 
 * 
 */

