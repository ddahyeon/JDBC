package workshop4;

public class StudentDTO {

    private String stuNo;
    private String departNo;
    private String stuName;
    private String stuSsn;
    private String stuAddress;
    private String entDate;
    private char absYn;
    private String coachProfessorNo;

    public StudentDTO() {}

    public StudentDTO(String stuNo, String departNo, String stuName, String stuSsn,
                      String stuAddress, String entDate, char absYn, String coachProfessorNo) {
        this.stuNo = stuNo;
        this.departNo = departNo;
        this.stuName = stuName;
        this.stuSsn = stuSsn;
        this.stuAddress = stuAddress;
        this.entDate = entDate;
        this.absYn = absYn;
        this.coachProfessorNo = coachProfessorNo;
    }

    public String getStuNo() { return stuNo; }
    public String getDepartNo() { return departNo; }
    public String getStuName() { return stuName; }
    public String getStuSsn() { return stuSsn; }
    public String getStuAddress() { return stuAddress; }
    public String getEntDate() { return entDate; }
    public char getAbsYn() { return absYn; }
    public String getCoachProfessorNo() { return coachProfessorNo; }
}