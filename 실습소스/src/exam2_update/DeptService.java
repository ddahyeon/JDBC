package exam2_update;



public interface DeptService {
	public abstract void setDao(DeptDAO dao);
	public abstract int update(DeptDTO dto);

}
