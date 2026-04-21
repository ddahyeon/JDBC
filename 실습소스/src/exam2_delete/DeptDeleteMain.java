package exam2_delete;

import java.util.Scanner;

public class DeptDeleteMain {

	public static void main(String[] args) {
	
		DeptService service  = new DeptServiceImpl();
		service.setDao(new DeptDAO());
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("삭제한 부서번호를 입력하시오");
		int deptno = scan.nextInt();
		
		int n=service.delete(deptno);
		if(n>=1)System.out.println("저장성공");
		
	}
}