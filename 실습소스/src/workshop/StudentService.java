package workshop;

import java.util.List;

public interface StudentService {
    public abstract void setDAO(StudentDAO dao);
    public abstract List<StudentDTO> list();
}
