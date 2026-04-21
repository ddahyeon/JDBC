package exam2_final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

// 로직처리 + 트랜잭션처리( con.commit() )
// Connection 얻어서 DeptDAO와 연동
public class DeptServiceImpl implements DeptService {

	//인스턴스 변수
	 String driver = "com.mysql.cj.jdbc.Driver"; // 핵심이 되는 클래스명
     String url = "jdbc:mysql://localhost:3306/testdb";
     String userid ="root";
     String passwd = "6120";
     
     //DeptDAO 설정
     DeptDAO dao;
     // set메서드로 초기화 ( main에서 설정함)
     @Override 
     public void setDao(DeptDAO dao) {
    	 this.dao = dao;
     }
     
     //기본생성자에서 드라이브 로딩처리
 	public DeptServiceImpl() {
 	       try {
 				Class.forName(driver);
 			} catch (ClassNotFoundException e) {
 				e.printStackTrace();
 			}
	}//end 생성자
     
	@Override
	public List<DeptDTO> list() {
		List<DeptDTO> list = null;
		Connection con = null;
		 try {
			con = DriverManager.getConnection(url, userid, passwd);
			//DeptDAO연동해서 list()호출
			list = dao.list(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				 if(con !=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// try~catch~finally
		return list;  // DeptSelectMain에 리턴
	}//end list()
	@Override 
	public int insert(DeptDTO dto)
     throws DuplicatedDeptnoException{
    	int n = 0;
		Connection con = null;
	     try {
			 con = DriverManager.getConnection(url, userid, passwd);
			 n = dao.insert(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				 if(con !=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// try~catch~finally
	     return n;
	}//end insert
    @Override 
	public int update(DeptDTO dto){
    	int n = 0;
		Connection con = null;
	     try {
			 con = DriverManager.getConnection(url, userid, passwd);
			 n = dao.update(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				 if(con !=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// try~catch~finally
	     return n;
	}//end update
    
    @Override 
   	public int delete(int deptno) throws RecordNotFoundException{
       	int n = 0;
   		Connection con = null;
   	     try {
   			 con = DriverManager.getConnection(url, userid, passwd);
   			 n = dao.delete(con, deptno);
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}finally {
   			try {
   				 if(con !=null) con.close();
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   		}// try~catch~finally
   	     return n;
   	}//end delete
    
}//end class