package workshop;

import java.util.ArrayList;
import java.util.Scanner;
import workshop.StudentDTO;
import workshop.StudentServiceImpl;



public class StudentMain {

	public static void main(String[] args) {
		while (true) {
			System.out.println("******************************");
			System.out.println("    [학생 정보 관리 메뉴]      ");
			System.out.println("******************************");
			System.out.println("1. 전체 학생 목록");
			System.out.println("0. 종료");
			System.out.println("******************************");
			Scanner scanner = new Scanner(System.in);
			System.out.print("메뉴 입력 => ");
			int  n = scanner.nextInt();
			if (n == 1) {
				StudentServiceImpl service = new StudentServiceImpl();
				ArrayList<StudentDTO> list = service.selectAllStudent();
				System.out.println("==========================================================");
				System.out.println("학번        이름        주민번호            주소                 입학년도      휴학여부");
				System.out.println("----------------------------------------------------------");
				for (StudentDTO s : list) {
					String stuNo = s.getStuNo();
					String sutName = s.getStuName();
					String stuSsn = s.getStuSsn();
					String stuAddress = s.getStuAdress();
					String entDate = s.getEntDate();
					String absYn = s.getAbsYn();
					System.out.println();
//					System.out.println(s);
					System.out.println( stuNo+ "    " + sutName + "    " + stuSsn + "    "+ stuAddress + "    " +  entDate + "    " + absYn);
					
				}
				System.out.println("총 학생 수 : " + list.size() + "명");
			} else if (n == 0) {
				System.out.println("프로그램이 종료 되었습니다.");
				System.exit(0);
			}
		} // end while
	}// end main
}
