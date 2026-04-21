package workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/workshop?serverTimezone=Asia/Seoul";
    String userid = "root";
    String passwd = "6120";

    StudentDAO dao;

    @Override
    public void setDAO(StudentDAO dao) {
        this.dao = dao;
    }

    public StudentServiceImpl() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
}