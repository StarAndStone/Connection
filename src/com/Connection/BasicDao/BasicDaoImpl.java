package com.Connection.BasicDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class BasicDaoImpl implements BasicDao {

	private Connection con;
	private PreparedStatement pre;
	private Statement sta;
	private static BasicDataSourceFactory dataSourceFactory;
	private static DataSource dataSource;

	/**
	 * 是根据tomcat监听配置下的链接数据库方式
	 * 1.必须在tomcat目录下的conf中context.xml配置如下
		 * <Context>
				<Resource name="jdbc/blog" auth="Container" 
				type="javax.sql.DataSource" maxActive="100" maxIdle="30" 
				maxWait="1000" username="root" password="root" 
				driverClassName="com.mysql.jdbc.Driver" 
				url="jdbc:mysql://127.0.0.1:3306/blog?characterencoding=UTF8"></Resource>
			</Context>//这个是外层Context标签（也就是必须放在content标签里面）不能有换行等

	 *2. 根据以下方法必须给Tomcat目录下的lib文件夹下拷贝mysql-connector-java-5.1.41-bin.jar
	 */
//	static {
//		Context c = null;
//		try {
//			c = new InitialContext();
//			dataSource = (DataSource) c.lookup("java:comp/evn/jdbc/blog");
//		} catch (NamingException e1) {
//			e1.printStackTrace();
//		}
//	}

	
	/**
	 * 以下静态方法是连接池技术； 
	 * 1.只需要在项目中配置
	 * 	commons-collections-3.2.1.jar
	 * 	commons-dbcp-1.4.jar
	 * 	commons-pool-1.6.jar
	 * 	者三个jar包就可以链接到数据库
	 * 2.书写properties文件
	 */
	static {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"/Connection/resources/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataSourceFactory = new BasicDataSourceFactory();// 链接池工厂
		try {
			dataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Connection getCon() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PreparedStatement getPre(String sql) {
		if (con == null) {
			con = getCon();
		}
		try {
			pre = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pre;
	}
}
