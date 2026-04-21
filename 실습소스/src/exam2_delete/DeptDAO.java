package exam2_delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//DB 연동
//
public class DeptDAO {

	public int delete(Connection con, int deptno)
	{
		int n = 0;
		PreparedStatement pstmt = null;
		try {
			 String sql = "delete from dept  where deptno=?";
		  pstmt = con.prepareStatement(sql);
		  pstmt.setInt(1, deptno );

		  n = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				 if(pstmt !=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// try~catch~finally
		return n;
	}//end insert
}//end class