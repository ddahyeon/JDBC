package exam2_delete;



public interface DeptService {
	public abstract void setDao(DeptDAO dao);
	public abstract int delete(int deptno);
}
