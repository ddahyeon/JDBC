package workshop2;

import java.util.List;

public interface StudentService {
    public abstract void setDAO(StudentDAO dao);
    public abstract List<StudentDTO> list();
    List<StudentDTO> searchByName(String name);
}
