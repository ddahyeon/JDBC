package workshop2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 기존 전체 조회
    public List<StudentDTO> list(Connection con) {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select student_no, department_no, student_name, student_ssn, "
                   + "student_address, entrance_date, absence_yn, coach_professor_no "
                   + "from tb_student";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StudentDTO dto = new StudentDTO(
                        rs.getString("student_no"),
                        rs.getString("department_no"),
                        rs.getString("student_name"),
                        rs.getString("student_ssn"),
                        rs.getString("student_address"),
                        rs.getString("entrance_date"),
                        rs.getString("absence_yn").charAt(0),
                        rs.getString("coach_professor_no")
                );
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // 이름 검색
    public List<StudentDTO> searchByName(Connection con, String searchName) {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select student_no, department_no, student_name, student_ssn, "
                   + "student_address, entrance_date, absence_yn, coach_professor_no "
                   + "from tb_student "
                   + "where student_name like ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchName + "%");  // 한 글자만 같아도 검색

            rs = pstmt.executeQuery();

            while (rs.next()) {
                StudentDTO dto = new StudentDTO(
                        rs.getString("student_no"),
                        rs.getString("department_no"),
                        rs.getString("student_name"),
                        rs.getString("student_ssn"),
                        rs.getString("student_address"),
                        rs.getString("entrance_date"),
                        rs.getString("absence_yn").charAt(0),
                        rs.getString("coach_professor_no") );
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
