package workshop2;

import java.util.ArrayList;
import workshop2.StudentDTO;

public interface StudentService {
	public ArrayList<StudentDTO> selectAllStudent();
	public ArrayList<StudentDTO> selectByName(String str);
}
