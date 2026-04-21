package exam2_delete;

import java.util.Scanner;

public class DeptDeleteMain {

	public static void main(String[] args) {
	
		DeptService service  = new DeptServiceImpl();
		service.setDao(new DeptDAO());
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("삭제할 부서번호를 입력하시오");
		int deptno = scan.nextInt();
		
		int n;
		try {
			n = service.delete(deptno);
			if(n>=1)System.out.println("삭제 성공");
		} catch (RecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
}