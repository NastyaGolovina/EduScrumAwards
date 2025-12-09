package UPT_SQ.EduScrumAwards.DTO;

public class StudentDetailDTO {
    private Long userId;
    private String name;
    private String login;
    private String studentNumber;
    private Integer currentSemester;
    private Integer totalScore;

    public StudentDetailDTO() {}

    public StudentDetailDTO(Long userId, String name, String login,
                            String studentNumber, Integer currentSemester, Integer totalScore) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.studentNumber = studentNumber;
        this.currentSemester = currentSemester;
        this.totalScore = totalScore;
    }

    // Getters e Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public Integer getCurrentSemester() { return currentSemester; }
    public void setCurrentSemester(Integer currentSemester) { this.currentSemester = currentSemester; }

    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
}
