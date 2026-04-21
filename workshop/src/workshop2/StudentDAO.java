package workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import workshop2.StudentDTO;

public class StudentDAO {
	
	public ArrayList<StudentDTO> selectByName(Connection con, String str){
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select student_no 학번, student_name 이름, "
				+ " rpad(substr(student_ssn, 1, 8), 14, '*') 주민번호, "
			    + " case when student_address is null then '**주소미상**' else concat(substr(student_address, 1, 10),'...' ) end 주소,"
				+ " DATE_FORMAT(entrance_date,'%Y/%m/%d') 입학년도, "
				+ " absence_yn 휴학여부";
			   sql += " from tb_student where student_name like ? order by 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + str + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String stuNo = rs.getString("학번");
				String stuName = rs.getString("이름");
				String stuSsn = rs.getString("주민번호");
				String stuAdress = rs.getString("주소");
				String entDate = rs.getString("입학년도");
				String absYn = rs.getString("휴학여부");
				
				StudentDTO s = new StudentDTO(stuNo, stuName, stuSsn, stuAdress, entDate,	absYn);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<StudentDTO> selectAllStudent(Connection con) {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select student_no 학번, student_name 이름, "
				+ " rpad(substr(student_ssn, 1, 8), 14, '*') 주민번호, "
				+ " concat(substr(student_address, 1, 10),'...' ) 주소, "
				+ " DATE_FORMAT(entrance_date,'%Y/%m/%d') 입학년도, "
				+ " absence_yn 휴학여부";
			   sql += " from tb_student order by 1 ";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String stuNo = rs.getString("학번");
				String stuName = rs.getString("이름");
				String stuSsn = rs.getString("주민번호");
				String stuAdress = rs.getString("주소");
				String entDate = rs.getString("입학년도");
				String absYn = rs.getString("휴학여부");
				
				StudentDTO s = new StudentDTO(stuNo, stuName, stuSsn, stuAdress, entDate, absYn);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
