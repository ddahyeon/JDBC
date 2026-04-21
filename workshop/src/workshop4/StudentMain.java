package workshop4;

import java.util.*;

public class StudentMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentServiceImpl();
        service.setDao(new StudentDAO());

        while (true) {
            try {
                System.out.println("1. 전체조회");
                System.out.println("2. 이름검색");
                System.out.println("3. 입학년도 검색");
                System.out.println("4. 학번 다중조회");
                System.out.println("0. 종료");

                int menu = sc.nextInt();
                sc.nextLine();

                List<StudentDTO> list = null;

                switch (menu) {

                    case 1:
                        list = service.selectAll();
                        break;

                    case 2:
                        System.out.print("이름 입력: ");
                        list = service.searchByStuName(sc.nextLine());
                        break;

                    case 3:
                        System.out.print("시작년도: ");
                        String s = sc.nextLine();
                        System.out.print("끝년도: ");
                        String e = sc.nextLine();
                        list = service.searchByEntranceDate(s, e);
                        break;

                    case 4:
                        System.out.print("학번 입력 (쉼표): ");
                        list = service.searchByStuNoList(sc.nextLine());
                        break;

                    case 0:
                        return;
                }

                if (list != null) {
                    for (StudentDTO dto : list) {
                        System.out.println(dto.getStuNo() + " " + dto.getStuName());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}