package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 普通链接数据库的方式
 * @author shitou
 *
 */
public class Conntext {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//声明数据存储对象
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接对象
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","root");
			System.out.println(conn);
			String sql="select * from t_user where uname=? and pwd=?";
			//创建Sql命令对象
			ps=conn.prepareStatement(sql);
			//执行
			rs=ps.executeQuery();
//			//遍历执行结果
//			while(rs.next()){
//				u=new User();
//				u.setuName(rs.getString("uname"));
//				u.setuPwd(rs.getString("pwd"));
//			}
			//关闭资源
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
