package UPT_SQ.EduScrumAwards.DTO;

public class StudentPointsDTO {
    private Long id;
    private String name;
    private String studentNumber;
    private Integer points;


    public StudentPointsDTO(Long id, String name, String studentNumber, Integer points) {
        this.id = id;
        this.name = name;
        this.studentNumber = studentNumber;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
