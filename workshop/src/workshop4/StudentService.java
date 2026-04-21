package workshop4;

import java.util.List;

public interface StudentService {

	  public void setDao(StudentDAO dao);

	    public List<StudentDTO> selectAll() throws Exception;
	    public List<StudentDTO> searchByStuName(String stuName) throws Exception;
	    public List<StudentDTO> searchByEntranceDate(String startYear, String endYear) throws Exception;
	    public List<StudentDTO> searchByStuNoList(String input) throws Exception;
}