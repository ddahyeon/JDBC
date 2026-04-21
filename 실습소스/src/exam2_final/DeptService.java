package exam2_final;

import java.util.List;

public interface DeptService {
	public abstract void setDao(DeptDAO dao);
	public abstract List<DeptDTO> list();
	public abstract int insert(DeptDTO dto)throws DuplicatedDeptnoException;
	public abstract int update(DeptDTO dto);
	public abstract int delete(int deptno) throws RecordNotFoundException;
}
