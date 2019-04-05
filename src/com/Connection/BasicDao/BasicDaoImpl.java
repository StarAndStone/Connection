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
	 * �Ǹ���tomcat���������µ��������ݿⷽʽ
	 * 1.������tomcatĿ¼�µ�conf��context.xml��������
		 * <Context>
				<Resource name="jdbc/blog" auth="Container" 
				type="javax.sql.DataSource" maxActive="100" maxIdle="30" 
				maxWait="1000" username="root" password="root" 
				driverClassName="com.mysql.jdbc.Driver" 
				url="jdbc:mysql://127.0.0.1:3306/blog?characterencoding=UTF8"></Resource>
			</Context>//��������Context��ǩ��Ҳ���Ǳ������content��ǩ���棩�����л��е�

	 *2. �������·��������TomcatĿ¼�µ�lib�ļ����¿���mysql-connector-java-5.1.41-bin.jar
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
	 * ���¾�̬���������ӳؼ����� 
	 * 1.ֻ��Ҫ����Ŀ������
	 * 	commons-collections-3.2.1.jar
	 * 	commons-dbcp-1.4.jar
	 * 	commons-pool-1.6.jar
	 * 	������jar���Ϳ������ӵ����ݿ�
	 * 2.��дproperties�ļ�
	 */
	static {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"/Connection/resources/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataSourceFactory = new BasicDataSourceFactory();// ���ӳع���
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
