package UPT_SQ.EduScrumAwards.model;

import java.util.Date;

public class StudentAward {
    private int studentAwardId;
    private Award award;
    private Student student;
    private Teacher teacher;
    private Project project;
    private Team team;
    private Date date;
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
