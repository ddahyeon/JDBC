package workshop3;

import java.util.List;
import java.util.Scanner;

public class StudentMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentServiceImpl();
        service.setDAO(new StudentDAO());

        while (true) {

            System.out.println("=======================================");
            System.out.println(" 1. 전체 학생 목록");
            System.out.println(" 2. 학생 이름 검색");
            System.out.println(" 3. 입학년도 범위 검색");
            System.out.println(" 0. 종료");
            System.out.println("=======================================");
            System.out.print("메뉴 선택 => ");

            int menu = sc.nextInt();
            sc.nextLine(); // 버퍼 제거

            if (menu == 0) {
                System.out.println("프로그램 종료");
                break;
            }

            else if (menu == 1) {
                // 전체 조회
                List<StudentDTO> list = service.list();
                print(list);
            }

            else if (menu == 2) {
                // 이름 검색
                System.out.print("검색할 이름 입력 => ");
                String name = sc.nextLine();

                List<StudentDTO> list = service.searchByName(name);
                print(list);
            }

            else if (menu == 3) {
                // 입학년도 검색
                System.out.print("시작년도 입력 => ");
                String start = sc.nextLine();

                System.out.print("끝년도 입력 => ");
                String end = sc.nextLine();

                List<StudentDTO> list = service.searchByYear(start, end);
                print(list);
            }

            else {
                System.out.println("잘못된 입력입니다.");
            }
        }

        sc.close();
    }

    // ========================= 출력 메서드 =========================

    public static void print(List<StudentDTO> list) {

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-8s %-15s %-20s %-15s %-5s%n",
                "학번", "이름", "주민번호", "주소", "입학년도", "휴학");
        System.out.println("--------------------------------------------------------------------------------");

        for (StudentDTO dto : list) {

            System.out.printf("%-10s %-8s %-15s %-20s %-15s %-5s%n",
                    dto.getStuNo(),
                    dto.getStuName(),
                    maskSsn(dto.getStuSsn()),
                    formatAddress(dto.getStuAddress()),
                    formatDate(dto.getEntDate()),
                    dto.getAbsYn());
        }

        System.out.println();
        System.out.println("총 학생수: " + list.size() + " 명");
        System.out.println();
    }

    // ========================= 가공 메서드 =========================

    // 주민번호 마스킹
    public static String maskSsn(String ssn) {
        if (ssn == null || ssn.length() < 8) return "";

        String front = ssn.substring(0, 6);
        String backFirst = ssn.substring(7, 8);

        return front + "-" + backFirst + "******";
    }

    // 주소 처리
    public static String formatAddress(String addr) {
        if (addr == null) return "***주소 미상***";

        if (addr.length() <= 10) return addr;

        return addr.substring(0, 10) + "...";
    }

    // 날짜 포맷
    public static String formatDate(String date) {
        if (date == null) return "";

        return date.replace("-", "/");
    }
}