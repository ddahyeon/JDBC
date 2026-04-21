package workshop3;

import java.util.List;

public interface StudentService {

    public abstract void setDAO(StudentDAO dao);
    public abstract List<StudentDTO> list();
    public abstract List<StudentDTO> searchByName(String name);
    public abstract List<StudentDTO> searchByYear(String startYear, String endYear);
}