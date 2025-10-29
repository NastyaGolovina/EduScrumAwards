package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Teacher in the EduScrum Awards system
 * A teacher can teach multiple courses and create projects
 *
 * @author [Ana Souto]
 * @version 29/10/2025
 */
@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    // RELATIONSHIP WITH COURSES: A Teacher can teach multiple Courses
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseTeacher> courseTeachers = new ArrayList<>();

    // Constructors
    public Teacher() {
        super();
        this.setRole(UserRole.TEACHER);
    }

    public Teacher(String name, String login, String password) {
        super(name, login, password, UserRole.TEACHER);
    }

    // METHODS TO MANAGE RELATIONSHIP WITH COURSES

    /**
     * Adds a course to the teacher with responsibility indication
     * @param course Course to be added
     * @param isResponsible true if the teacher is responsible for the course
     */
    public void addCourse(Course course, Boolean isResponsible) {
        CourseTeacher courseTeacher = new CourseTeacher(course, this, isResponsible);
        this.courseTeachers.add(courseTeacher);
        // Maintains bidirectional consistency
        if (course != null && course.getCourseTeachers() != null) {
            course.getCourseTeachers().add(courseTeacher);
        }
    }

    /**
     * Removes a course from the teacher
     * @param course Course to be removed
     */
    public void removeCourse(Course course) {
        CourseTeacher courseTeacherToRemove = null;
        for (CourseTeacher ct : this.courseTeachers) {
            if (ct.getCourse() != null && ct.getCourse().equals(course)) {
                courseTeacherToRemove = ct;
                break;
            }
        }
        if (courseTeacherToRemove != null) {
            this.courseTeachers.remove(courseTeacherToRemove);
            courseTeacherToRemove.setCourse(null);
            courseTeacherToRemove.setTeacher(null);
        }
    }

    /**
     * Returns all courses that the teacher teaches
     * @return List of courses
     */
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        for (CourseTeacher ct : this.courseTeachers) {
            if (ct.getCourse() != null) {
                courses.add(ct.getCourse());
            }
        }
        return courses;
    }

    /**
     * Checks if the teacher is responsible for a specific course
     * @param course Course to check
     * @return true if responsible
     */
    public boolean isResponsibleForCourse(Course course) {
        for (CourseTeacher ct : this.courseTeachers) {
            if (ct.getCourse() != null &&
                    ct.getCourse().equals(course) &&
                    Boolean.TRUE.equals(ct.getIsResponsible())) {
                return true;
            }
        }
        return false;
    }

    // BUSINESS METHODS (to be implemented)

    /**
     * Method to create a new course
     * TODO: Implement when the Course class is ready
     */
    public void createCourse() {
        // Logic will be implemented later
        System.out.println("Creating new course...");
    }

    /**
     * Method to create a new project
     * TODO: Implement when the Project class is ready
     */
    public void createProject() {
        //Logic will be implemented later
        System.out.println("Creating new project...");
    }

    /**
     * Method to manually assign an award
     * TODO: Implement when the Award class is ready
     * @param student Student who will receive the award
     * @param award Award to be assigned
     */
    public void assignAward(Student student, Award award) {
        // TODO: Implement when the Award class is ready
        System.out.println("Assigning award to student: " + student.getName());
    }

    /**
     * Method to generate performance dashboard
     * TODO: Implement dashboard generation
     */
    public void generateDashboard() {
        // TODO: Implement dashboard generetion
        System.out.println("Generating teacher dashboard...");
        // In the future> will return course and project performance data
    }

    // Getters and Setters
    public List<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    public void setCourseTeachers(List<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", coursesCount=" + (courseTeachers != null ? courseTeachers.size() : 0) +
                '}';
    }
}