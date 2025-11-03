package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents an award given to a student within a specific project or team context.
 * Each StudentAward links a student to an award, teacher, project, and team,
 * along with additional details such as the date of the award and the number of points earned.
 *
 * @author Anastasiia Holovina
 * @version 1.0
 * @since 2025-10-31
 */
//@Entity
//@Table(name = "Student_Award")
public class StudentAward {
//    @Id
//    @Column(name = "student_award_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentAwardId;
//    @ManyToOne
//    @JoinColumn(name = "award_id")
    private Award award;
//    @ManyToOne
//    @JoinColumn(name = "student_id")
    private Student student;
//    @ManyToOne
//    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
//    @ManyToOne
//    @JoinColumn(name = "project_id")
    private Project project;
//    @ManyToOne
//    @JoinColumn(name = "team_id")
    private Team team;
//    @Column(name = "date")
    private Date date;
//    @Column(name = "points")
    private int points;

    /**
     * Empty constructor for creating an StudentAward.
     */
    public  StudentAward() {}

    /**
     * Constructs a new {@code StudentAward} instance with the specified parameters.
     *
     * @param award the award associated with this record
     * @param student the student who received the award
     * @param teacher the teacher who assigned or approved the award
     * @param project the project linked to this award
     * @param team the team the student belonged to
     * @param date the date when the award was granted
     * @param points the number of points associated with the award
     */
    public StudentAward(Award award, Student student, Teacher teacher, Project project, Team team, Date date, int points) {
        this.award = award;
        this.student = student;
        this.teacher = teacher;
        this.project = project;
        this.team = team;
        this.date = date;
        this.points = points;
    }

    /**
     * Returns the unique identifier of this student award.
     *
     * @return the student award ID
     */
    public int getStudentAwardId() {
        return studentAwardId;
    }

    /**
     * Sets the unique identifier of this student award.
     *
     * @param studentAwardId the new student award ID
     */
    public void setStudentAwardId(int studentAwardId) {
        this.studentAwardId = studentAwardId;
    }

    /**
     * Returns the award associated with this student award record.
     *
     * @return the award
     */
    public Award getAward() {
        return award;
    }

    /**
     * Sets the award associated with this student award record.
     *
     * @param award the award to associate
     */
    public void setAward(Award award) {
        this.award = award;
    }

    /**
     * Returns the student who received the award.
     *
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the student who received the award.
     *
     * @param student the student to associate
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Returns the teacher responsible for granting the award.
     *
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher responsible for granting the award.
     *
     * @param teacher the teacher to associate
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Returns the project associated with this award.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project associated with this award.
     *
     * @param project the project to associate
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Returns the team associated with this award.
     *
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team associated with this award.
     *
     * @param team the team to associate
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Returns the date when the award was granted.
     *
     * @return the award date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date when the award was granted.
     *
     * @param date the award date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the number of points earned for this award.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the number of points earned for this award.
     *
     * @param points the new number of points
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
