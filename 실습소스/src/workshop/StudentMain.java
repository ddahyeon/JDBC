package workshop;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("****************************************");
            System.out.println("\t[학생 정보 관리 메뉴]");
            System.out.println("****************************************");
            System.out.println("1. 전체 학생 목록");
            System.out.println("0. 종료");
            System.out.println("****************************************");
            System.out.print("메뉴 입력 => ");

            int menu = sc.nextInt();

            if (menu == 0) {
                System.out.println("프로그램이 종료되었습니다.");
                break;
            } else if (menu == 1) {
                ArrayList<StudentDTO> list = dao.selectAllStudents();

                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("%-10s %-8s %-15s %-18s %-15s %-8s%n",
                        "학번", "이름", "주민번호", "주소", "입학년도", "휴학여부");
                System.out.println("--------------------------------------------------------------------------------");

                for (StudentDTO dto : list) {
                    System.out.printf("%-10s %-8s %-15s %-18s %-15s %-8s%n",
                            dto.getStuNo(),
                            dto.getStuName(),
                            maskSsn(dto.getStuSsn()),
                            shortAddress(dto.getStuAddress()),
                            formatDate(dto.getEntDate()),
                            dto.getAbsYn());
                }

                System.out.println();
                System.out.println("총 학생수: " + list.size() + " 명");
                System.out.println();
            } else {
                System.out.println("잘못된 메뉴입니다.");
                System.out.println();
            }
        }

        sc.close();
    }

    // 주민번호 마스킹: 760830-1******
    private static String maskSsn(String ssn) {
        if (ssn == null || ssn.length() < 8) {
            return ssn;
        }

        String front = ssn.substring(0, 6);
        String backFirst = ssn.substring(7, 8);
        return front + "-" + backFirst + "******";
    }

    // 주소 10글자 + ...
    private static String shortAddress(String address) {
        if (address == null) {
            return "";
        }

        if (address.length() <= 10) {
            return address;
        }

        return address.substring(0, 10) + "...";
    }

    // 날짜 포맷: 1995/03/01 형태로 맞춤
    private static String formatDate(String date) {
        if (date == null) {
            return "";
        }

        // MySQL DATE가 1995-03-01로 들어오는 경우
        return date.replace("-", "/");
    }
}