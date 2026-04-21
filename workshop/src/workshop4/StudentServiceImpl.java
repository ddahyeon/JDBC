package workshop4;

import java.sql.*;
import java.util.List;

public class StudentServiceImpl implements StudentService {

	String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/workshop";
    String userid = "root";
    String passwd = "6120"; 
    
    private StudentDAO dao;

    @Override
    public void setDao(StudentDAO dao) {
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

    @Override
    public List<StudentDTO> selectAll() throws Exception {
        Connection con = DriverManager.getConnection();
        List<StudentDTO> list = dao.selectAll(con);
        con.close();
        return list;
    }

    @Override
    public List<StudentDTO> searchByStuName(String name) throws Exception {
        Connection con = DriverManager.getConnection();
        List<StudentDTO> list = dao.searchByStuName(con, name);
        con.close();
        return list;
    }

    @Override
    public List<StudentDTO> searchByEntranceDate(String start, String end) throws Exception {
        Connection con = DriverManager.getConnection();
        List<StudentDTO> list = dao.searchByEntranceDate(con, start, end);
        con.close();
        return list;
    }

    @Override
    public List<StudentDTO> searchByStuNoList(String input) throws Exception {
        Connection con = DriverManager.getConnection();
        List<StudentDTO> list = dao.searchByStuNoList(con, input);
        con.close();
        return list;
    }
}