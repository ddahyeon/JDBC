package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import workshop.StudentDAO;
import workshop.StudentDTO;


public class StudentServiceImpl implements StudentService{

	//1. 4가지 정보 설정
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/workshop";
	String userid = "root";
	String passwd = "6120";

	public StudentServiceImpl() { // 생성자에 드라이버 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<StudentDTO> selectAllStudent() {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			list = dao.selectAllStudent(con);

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
