package com.xxm.it.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 将mysql表字段转换成对象属性
 * 
 * @author Administrator
 *
 */
public class Test1 {

	private Connection connection;
	// 数据库中的表名
	String table = "xxm_flow_log_t";
	String databaseName = "xxmdb";

	public Test1() {
		// mysql jdbc的java包驱动字符串
		String driverClassName = "com.mysql.jdbc.Driver";
		/* mysql url的连接字符串 */
		String url = "jdbc:mysql://192.168.16.106:3306/" + databaseName
				+ "?useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
		// 账号
		String user = "root";
		// 密码
		String password = "root";
		try {// 驱动注册
			Class.forName(driverClassName);
			if (connection == null || connection.isClosed())
				// 获得链接
				connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Oh,not");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Oh,not");
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void doAction() {
		String sql = "SELECT COLUMN_NAME COLUMN_NAME, DATA_TYPE DATA_TYPE, COLUMN_COMMENT COLUMN_COMMENT";
		sql += " FROM INFORMATION_SCHEMA.COLUMNS";
		sql += " WHERE table_name = '" + table + "'";
		sql += " AND table_schema = '" + databaseName + "'";

		Statement stmt = null;
		ResultSet rss = null;
		try {
			stmt = connection.createStatement();
			rss = stmt.executeQuery(sql);
			while (rss.next()) {
				String coloumnName = rss.getString(1); // 获取字段名称
				String comment = rss.getString(3);// 字段注释
				// System.out.println("private String " + convert(coloumnName) +
				// ";//" + comment);
				// System.out.println(coloumnName + " = #{" +
				// convert(coloumnName) + ",jdbcType=VARCHAR},");
				System.out.println("t." + coloumnName + " as " + convert(coloumnName) + ",");
				// System.out.println(coloumnName + ",");
				// System.out.println("#{" + convert(coloumnName) +
				// ",jdbcType=VARCHAR},");
				// System.out.println("methodName_ =" + convert(coloumnName));
				// System.out.println(convert(coloumnName) + "=" + comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String convert(String p) {
		String newP = "";
		String[] strs = p.split("_");
		if (null != strs && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				if (i == 0) {
					newP = strs[i];
					// newP = strs[i].substring(0, 1).toUpperCase() +
					// strs[i].substring(1);
				} else {
					newP += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
				}
			}
		}
		return newP;
	}

	public static void main(String[] args) {
		Test1 bean = new Test1();
		bean.doAction();
	}
}
