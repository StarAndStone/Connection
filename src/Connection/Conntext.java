package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * ��ͨ�������ݿ�ķ�ʽ
 * @author shitou
 *
 */
public class Conntext {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//�������ݴ洢����
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ���Ӷ���
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","root");
			System.out.println(conn);
			String sql="select * from t_user where uname=? and pwd=?";
			//����Sql�������
			ps=conn.prepareStatement(sql);
			//ִ��
			rs=ps.executeQuery();
//			//����ִ�н��
//			while(rs.next()){
//				u=new User();
//				u.setuName(rs.getString("uname"));
//				u.setuPwd(rs.getString("pwd"));
//			}
			//�ر���Դ
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
