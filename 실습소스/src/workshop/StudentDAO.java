package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/workshop?serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWD = "6120";

    public ArrayList<StudentDTO> selectAllStudents() {
        ArrayList<StudentDTO> list = new ArrayList<>();

        String sql = "select student_no, department_no, student_name, student_ssn, " +
                     "student_address, entrance_date, absence_yn, coach_professor_no " +
                     "from tb_student " +
                     "order by student_no";

        try {
            Class.forName(DRIVER);

            try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWD);
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
            ) {
                while (rs.next()) {
                    StudentDTO dto = new StudentDTO();

                    dto.setStuNo(rs.getString("student_no"));
                    dto.setDepartNo(rs.getString("department_no"));
                    dto.setStuName(rs.getString("student_name"));
                    dto.setStuSsn(rs.getString("student_ssn"));
                    dto.setStuAddress(rs.getString("student_address"));
                    dto.setEntDate(rs.getString("entrance_date"));
                    String abs = rs.getString("absence_yn");
                    dto.setAbsYn(abs != null && !abs.isEmpty() ? abs.charAt(0) : ' ');
                    dto.setCoachProfessorNo(rs.getString("coach_professor_no"));

                    list.add(dto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}