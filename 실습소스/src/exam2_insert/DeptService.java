package exam2_insert;



public interface DeptService {
	public abstract void setDAO(DeptDAO dao);
	public abstract int insert(DeptDTO dto);

}
