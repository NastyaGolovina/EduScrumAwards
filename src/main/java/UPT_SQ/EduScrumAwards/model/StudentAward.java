package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

import java.util.Date;

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

    public StudentAward(int studentAwardId, Award award, Student student, Teacher teacher, Project project, Team team, Date date, int points) {
        this.studentAwardId = studentAwardId;
        this.award = award;
        this.student = student;
        this.teacher = teacher;
        this.project = project;
        this.team = team;
        this.date = date;
        this.points = points;
    }

    public int getStudentAwardId() {
        return studentAwardId;
    }

    public void setStudentAwardId(int studentAwardId) {
        this.studentAwardId = studentAwardId;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
