package workshop4;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    // 1. 전체 조회
    public List<StudentDTO> selectAll(Connection con) throws Exception {
        List<StudentDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TB_STUDENT";

        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            list.add(new StudentDTO(
                rs.getString("STUDENT_NO"),
                rs.getString("DEPARTMENT_NO"),
                rs.getString("STUDENT_NAME"),
                rs.getString("STUDENT_SSN"),
                rs.getString("STUDENT_ADDRESS"),
                rs.getString("ENTRANCE_DATE"),
                rs.getString("ABSENCE_YN").charAt(0),
                rs.getString("COACH_PROFESSOR_NO")
            ));
        }

        rs.close();
        pstmt.close();
        return list;
    }

    // 2. 이름 검색
    public List<StudentDTO> searchByStuName(Connection con, String name) throws Exception {
        List<StudentDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TB_STUDENT WHERE STUDENT_NAME LIKE ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + name + "%");

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            list.add(new StudentDTO(
                rs.getString("STUDENT_NO"),
                rs.getString("DEPARTMENT_NO"),
                rs.getString("STUDENT_NAME"),
                rs.getString("STUDENT_SSN"),
                rs.getString("STUDENT_ADDRESS"),
                rs.getString("ENTRANCE_DATE"),
                rs.getString("ABSENCE_YN").charAt(0),
                rs.getString("COACH_PROFESSOR_NO")
            ));
        }

        rs.close();
        pstmt.close();
        return list;
    }

    // 3. 입학년도 범위
    public List<StudentDTO> searchByEntranceDate(Connection con, String start, String end) throws Exception {
        List<StudentDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM TB_STUDENT WHERE ENTRANCE_DATE BETWEEN ? AND ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, start);
        pstmt.setString(2, end);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            list.add(new StudentDTO(
                rs.getString("STUDENT_NO"),
                rs.getString("DEPARTMENT_NO"),
                rs.getString("STUDENT_NAME"),
                rs.getString("STUDENT_SSN"),
                rs.getString("STUDENT_ADDRESS"),
                rs.getString("ENTRANCE_DATE"),
                rs.getString("ABSENCE_YN").charAt(0),
                rs.getString("COACH_PROFESSOR_NO")
            ));
        }

        rs.close();
        pstmt.close();
        return list;
    }

    // 4. 학번 다중 조회
    public List<StudentDTO> searchByStuNoList(Connection con, String input) throws Exception {

        List<StudentDTO> list = new ArrayList<>();
        String[] stuNos = input.split(",");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stuNos.length; i++) {
            sb.append("'").append(stuNos[i].trim()).append("'");
            if (i != stuNos.length - 1) sb.append(",");
        }

        String sql = "SELECT * FROM TB_STUDENT WHERE STUDENT_NO IN (" + sb.toString() + ")";

        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            list.add(new StudentDTO(
                rs.getString("STUDENT_NO"),
                rs.getString("DEPARTMENT_NO"),
                rs.getString("STUDENT_NAME"),
                rs.getString("STUDENT_SSN"),
                rs.getString("STUDENT_ADDRESS"),
                rs.getString("ENTRANCE_DATE"),
                rs.getString("ABSENCE_YN").charAt(0),
                rs.getString("COACH_PROFESSOR_NO")
            ));
        }

        rs.close();
        pstmt.close();
        return list;
    }
}