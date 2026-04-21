package exam2_insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//DB 연동
//
public class DeptDAO {

	public int insert(Connection con, DeptDTO dto)
	   throws DuplicatedDeptnoException
	{
		int n = 0;
		PreparedStatement pstmt = null;
		try {
		  String sql = "insert into dept (deptno,dname,loc) values (?,?,?)";
		  pstmt = con.prepareStatement(sql);
		  pstmt.setInt(1, dto.getDeptno() );
	      pstmt.setString(2, dto.getDname());
		  pstmt.setString(3, dto.getLoc());
		  n = pstmt.executeUpdate();
		}catch(SQLException e) {
			//SQLIntegrityConstraintViolationException 발생
			//e.printStackTrace();
			throw new DuplicatedDeptnoException(dto.getDeptno()+" 값이 중복되어 예외발생됨");
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