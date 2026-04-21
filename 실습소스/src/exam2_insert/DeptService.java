package exam2_insert;



public interface DeptService {
	public abstract void setDao(DeptDAO dao);
	public abstract int insert(DeptDTO dto)throws DuplicatedDeptnoException;
}
