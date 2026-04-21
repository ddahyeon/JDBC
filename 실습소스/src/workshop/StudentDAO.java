package workshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

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
                String stuNo = rs.getString("student_no");
                String departNo = rs.getString("department_no");
                String stuName = rs.getString("student_name");
                String stuSsn = rs.getString("student_ssn");
                String stuAddress = rs.getString("student_address");
                String entDate = rs.getString("entrance_date");
                char absYn = rs.getString("absence_yn").charAt(0);
                String coachProfessorNo = rs.getString("coach_professor_no");

                StudentDTO dto = new StudentDTO(
                        stuNo, departNo, stuName, stuSsn,
                        stuAddress, entDate, absYn, coachProfessorNo
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