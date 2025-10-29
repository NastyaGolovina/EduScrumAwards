package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Student in the EduScrum Awards system
 * A student can participate in multiple teams and accumulate awards
 *
 * @author [Ana Souto]
 * @version 29/10/2025
 */
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    // STUDENT-SPECIFIC FIELDS
    @Column(name = "student_number", unique = true, length = 20)
    private String studentNumber;

    @Column(name = "current_semester")
    private Integer currentSemester;

    // SCORE (transient - dynamically calculated)
    @Transient
    private Integer totalScore = 0;

    // RELATIONSHIPS (commented until other classes are ready)

    // Relationship with TeamMember (to be implemented by Lily)
    // @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<TeamMember> teamMemberships = new ArrayList<>();

    // Relationship with StudentAward (to be implemented by Anastasia)
    // @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<StudentAward> studentAwards = new ArrayList<>();

    // Constructors
    public Student() {
        super();
        this.setRole(UserRole.STUDENT);
    }

    public Student(String name, String login, String password) {
        super(name, login, password, UserRole.STUDENT);
    }

    public Student(String name, String login, String password, String studentNumber, Integer currentSemester) {
        super(name, login, password, UserRole.STUDENT);
        this.studentNumber = studentNumber;
        this.currentSemester = currentSemester;
    }

    // BUSINESS METHODS

    /**
     * Calculates the student's total score based on received awards
     * TODO: Implement real calculation when StudentAward is ready
     * @return Student's total score
     */
    public Integer calculateTotalScore() {
        // TODO: Implement calculation based on awards when StudentAward is ready
        // For now, return a placeholder value
        System.out.println("Calculating total score for student: " + this.getName());

        // In the future: iterate through studentAwards and sum points
        // for (StudentAward award : studentAwards) {
        //     this.totalScore += award.getAward().getPointsValue();
        // }

        return this.totalScore;
    }

    /**
     * Returns the student's award list
     * TODO: Implement when StudentAward is ready
     * @return List of student awards
     */
    public List<StudentAward> getStudentAwards() {
        // TODO: Implement when StudentAward is ready
        System.out.println("Retrieving student awards...");
        return new ArrayList<>();
    }

    /**
     * Method for the student to receive an award
     * TODO: Implement when StudentAward is ready
     * @param award Award to be received
     */
    public void earnAward(Award award) {
        // TODO: Implement when Award and StudentAward are ready
        System.out.println("Student " + this.getName() + " earned award: " + award.getAwardName());
        // In the future: create StudentAward and add to list
    }

    /**
     * Generates student's personal dashboard
     * TODO: Implement student dashboard generation
     */
    public void generateDashboard() {
        // TODO: Implement student dashboard generation
        System.out.println("Generating student dashboard for: " + this.getName());
        // In the future: will return progress, awards, ranking, etc.
    }

    /**
     * Checks if the student is enrolled in a specific course
     * TODO: Implement when relationships are complete
     * @param course Course to check
     * @return true if student is enrolled in the course
     */
    public boolean isEnrolledInCourse(Course course) {
        // TODO: Implement via Team -> Project -> Course
        System.out.println("Checking if student is enrolled in course: " + course.getCourseName());
        return false;
    }

    // Getters and Setters
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    // Getters for relationships (will be uncommented when classes are ready)
    /*
    public List<TeamMember> getTeamMemberships() {
        return teamMemberships;
    }

    public void setTeamMemberships(List<TeamMember> teamMemberships) {
        this.teamMemberships = teamMemberships;
    }
    */

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", currentSemester=" + currentSemester +
                ", totalScore=" + totalScore +
                '}';
    }
}