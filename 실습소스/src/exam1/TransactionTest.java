package exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTest {

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
			 
con.setAutoCommit(false);
			 //1. insert문
			 String sql = "insert into dept (deptno,dname,loc) values (?,?,?)";
			 pstmt = con.prepareStatement(sql);
			 // ? 대신에 나중에 값을 설정
			 // pstmt.setXXX(?위치,  값);
			 pstmt.setInt(1, 52 );
			 pstmt.setString(2, "인사");
			 pstmt.setString(3, "제주");
			 int n = pstmt.executeUpdate();
			 if(n>=1) System.out.println("저장성공");
			 //2. update문
			 String sql2 ="update dept set dname=?, loc=? where deptno=?";
			 pstmt = con.prepareStatement(sql2);		 
			 pstmt.setInt(3, 51 );
			 pstmt.setString(2, "인사과");
			 pstmt.setString(1, "제주도");
			 int n2 = pstmt.executeUpdate();
			 if(n2>=1) System.out.println("수정 성공");
con.commit();			 
		} catch (SQLException e) {
			 try {
				 if(con !=null) con.rollback();
				 System.out.println("오류발생");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
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

