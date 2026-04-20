package exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTest {

	public static void main(String[] args) {

		// Dept 테이블 저장
		//1. 4가지 정보 
	     String driver = "com.mysql.cj.jdbc.Driver"; // 핵심이 되는 클래스명
	     String url = "jdbc:mysql://localhost:3306/testdb";
	     String userid ="root";
	     String passwd = "6120";
		
		//2. 드라이버 로딩
	     try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	     
	    //3. Connection 맺기
	     Connection con = null;
	     PreparedStatement pstmt = null;
	     try {
			 con = DriverManager.getConnection(url, userid, passwd);
			 
			 //  51, 인사 , 제주 ==> 51번을 찾아서 인사과, 제주도 
			 String sql = "update dept set dname=? ,  loc=?  where deptno=?";
			 pstmt = con.prepareStatement(sql);
			
			 // ? 대신에 나중에 값을 설정
			 // pstmt.setXXX(?위치,  값);
			 pstmt.setString(1, "인사과");
			 pstmt.setString(2, "제주도");
			 pstmt.setInt(3, 51 );
			 
			 int n = pstmt.executeUpdate();
			 if(n>=1) System.out.println("수정 성공");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				 if(pstmt !=null) pstmt.close();
				 if(con !=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// try~catch~finally
	}//end main
}//end class