package UPT_SQ.EduScrumAwards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Student in the EduScrum Awards system
 * A student can participate in multiple teams and accumulate awards
 *
 * @author [Ana Carolina Souto]
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

    /**
     * Calculates the total points earned by this student.
     *
     * <p>This method iterates over a list of {@link StudentAward} objects
     * and sums the points for all awards belonging to the current student
     * (based on matching user IDs).</p>
     *
     * @param studentsAwards the list of student awards to check
     * @return the total points awarded to this student
     */
    public int calculatePoints(ArrayList<StudentAward> studentsAwards) {
        int points = 0;
        for(StudentAward studentAward : studentsAwards) {
            if(studentAward.getStudent().getUserId() == this.getUserId()) {
                points += studentAward.getPoints();
            }
        }
        return points;
    }

    // Getters e Setters

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