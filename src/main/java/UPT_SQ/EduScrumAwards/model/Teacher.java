package UPT_SQ.EduScrumAwards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<CourseTeacher> courses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<StudentAward> assignedAwards = new ArrayList<>();

    public Teacher() {
        super();
        this.setRole(UserRole.TEACHER);
    }

    public Teacher(String name, String login, String password) {
        super(name, login, password, UserRole.TEACHER);
    }

    public void generateDashboard() {
        // TODO: Implement dashboard generation
        System.out.println("Generating teacher dashboard...");
    }

    /**
     * Loads all CourseTeacher associations for this teacher using JPQL
     */
    public void readAllCourseTeacherWithJplq() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            List<CourseTeacher> courseTeacherList = session.createQuery(
                            "SELECT ct FROM CourseTeacher ct WHERE ct.teacher.userId = :teacherId",
                            CourseTeacher.class)
                    .setParameter("teacherId", this.getUserId())
                    .getResultList();
            this.courses = (ArrayList<CourseTeacher>) courseTeacherList;
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Loads all StudentAward associations for this teacher using JPQL
     */
    public void readAllStudentAwardWithJplq() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            List<StudentAward> studentAwardList = session.createQuery(
                            "SELECT sa FROM StudentAward sa WHERE sa.teacher.userId = :teacherId",
                            StudentAward.class)
                    .setParameter("teacherId", this.getUserId())
                    .getResultList();
            this.assignedAwards = (ArrayList<StudentAward>) studentAwardList;
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    // Getters e Setters
    public List<CourseTeacher> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTeacher> courses) {
        this.courses = courses;
    }

    public List<StudentAward> getAssignedAwards() {
        return assignedAwards;
    }

    public void setAssignedAwards(List<StudentAward> assignedAwards) {
        this.assignedAwards = assignedAwards;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", coursesCount=" + (courses != null ? courses.size() : 0) +
                ", assignedAwardsCount=" + (assignedAwards != null ? assignedAwards.size() : 0) +
                '}';
    }
}