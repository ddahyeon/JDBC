package workshop3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 1. 전체 학생 목록 조회
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

    // 2. 학생 이름 검색
    public List<StudentDTO> searchByName(Connection con, String name) {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select student_no, department_no, student_name, student_ssn, "
                   + "student_address, entrance_date, absence_yn, coach_professor_no "
                   + "from tb_student "
                   + "where student_name like ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");

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

    // 3. 학생 입학년도 범위 검색
    public List<StudentDTO> searchByYear(Connection con, String startYear, String endYear) {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select student_no, department_no, student_name, student_ssn, "
                   + "student_address, entrance_date, absence_yn, coach_professor_no "
                   + "from tb_student "
                   + "where substr(entrance_date, 1, 4) between ? and ? "
                   + "order by entrance_date desc";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, startYear);
            pstmt.setString(2, endYear);

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
}