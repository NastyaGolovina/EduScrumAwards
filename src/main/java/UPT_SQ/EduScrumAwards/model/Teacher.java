package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    /*@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseTeacher> courses = new ArrayList<>();*/

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

    // Getters and Setters
    /*public List<CourseTeacher> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTeacher> courses) {
        this.courses = courses;
    }*/

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                /*", coursesCount=" + (courses != null ? courses.size() : 0) +*/
                '}';
    }
}