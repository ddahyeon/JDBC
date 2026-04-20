package exam1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {

		// Dept 테이블 조회
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
	     ResultSet rs = null;
	     try {
			 con = DriverManager.getConnection(url, userid, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    //4. sql문 작성
	     String sql = "select deptno, dname, loc from dept";
	     
	     //5. PrepareStatement 객체 ( sql문 전송 객체)
	     try {
			 pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    //6. sql문 실행
	     try {
			 rs = pstmt.executeQuery();
			while(rs.next()) { // 행선택
				//열선택
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.printf("%d, %s , %s \n", deptno, dname,loc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
	    //7. 자원반납 ( 사용했던 자원 역순으로 close() ) 
	       try {
			  rs.close();
			  pstmt.close();
		      con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}
}


