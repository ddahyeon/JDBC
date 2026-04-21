package workshop2;

import java.util.List;
import java.util.Scanner;

public class StudentMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentServiceImpl();
        service.setDAO(new StudentDAO());

        while (true) {
            System.out.println("****************************************");
            System.out.println("\t[학생 정보 관리 메뉴]");
            System.out.println("****************************************");
            System.out.println("1.전체 학생 목록");
            System.out.println("2.학생 이름 검색");
            System.out.println("0.종료");
            System.out.println("****************************************");
            System.out.print("메뉴 입력 => ");

            int menu = sc.nextInt();
            sc.nextLine();

            if (menu == 0) {
                System.out.println("프로그램이 종료되었습니다.");
                break;
            } else if (menu == 1) {
                List<StudentDTO> list = service.list();
                printStudents(list);
            } else if (menu == 2) {
                System.out.print("검색할 학생명을 입력하시오 => ");
                String name = sc.nextLine();

                List<StudentDTO> list = service.searchByName(name);
                printStudents(list);
            } else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }

        sc.close();
    }

    public static void printStudents(List<StudentDTO> list) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-8s %-15s %-18s %-15s %-8s%n",
                "학번", "이름", "주민번호", "주소", "입학년도", "휴학여부");
        System.out.println("--------------------------------------------------------------------------------");

        for (StudentDTO dto : list) {
            System.out.printf("%-10s %-8s %-15s %-18s %-15s %-8s%n",
                    dto.getStuNo(),
                    dto.getStuName(),
                    maskSsn(dto.getStuSsn()),
                    printAddress(dto.getStuAddress()),
                    formatDate(dto.getEntDate()),
                    dto.getAbsYn());
        }

        System.out.println();
        System.out.println("총 학생수: " + list.size() + " 명");
        System.out.println();
    }

    public static String maskSsn(String ssn) {
        if (ssn == null || ssn.length() < 8) return "";
        String front = ssn.substring(0, 6);
        String backFirst = ssn.substring(7, 8);
        return front + "-" + backFirst + "******";
    }

    public static String printAddress(String address) {
        if (address == null) {
            return "***주소 미상***";
        }
        if (address.length() <= 10) {
            return address;
        }
        return address.substring(0, 10) + "...";
    }

    public static String formatDate(String entDate) {
        if (entDate == null) return "";
        return entDate.replace("-", "/");
    }
}