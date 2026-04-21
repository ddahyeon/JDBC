package workshop3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/workshop";
    String userid = "root";
    String passwd = "6120";  

    StudentDAO dao;

    // DAO 설정
    @Override
    public void setDAO(StudentDAO dao) {
        this.dao = dao;
    }

    // 생성자: 드라이버 로딩
    public StudentServiceImpl() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 1. 전체 학생 목록 조회
    @Override
    public List<StudentDTO> list() {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            list = dao.list(con);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // 2. 학생 이름 검색
    @Override
    public List<StudentDTO> searchByName(String name) {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            list = dao.searchByName(con, name);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // 3. 학생 입학년도 범위 검색
    @Override
    public List<StudentDTO> searchByYear(String startYear, String endYear) {
        List<StudentDTO> list = new ArrayList<StudentDTO>();
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, userid, passwd);
            list = dao.searchByYear(con, startYear, endYear);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}