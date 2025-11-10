/**
 * File with the description of CourseTeacher
 * Project - EduScrumAwards
 * Subject - Software Quality
 * Teacher - Fátima Leal
 * Developers of the file - José Ángel (53150)
 * Date - 29/10/2025 (SPRINT 1)
 * Version: 0.2
 */
package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

/**
 * Class that represente a teacher as a course director
 */
@Entity
@Table(name = "CourseTeachers")
public class CourseTeacher {
    @Id
    @Column(name = "course_teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseTeacherID;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "is_responsible")
    private boolean isResponsible;

    /**
     * No-args constructor required by Hibernate
     */
    public CourseTeacher() {}

    /**
     * Constructor of the class CourseTeacher
     * @param courseTeacherID Integer of the ID
     * @param course Course
     * @param teacher Teacher
     * @param isResponsible Boolean that indicates if the teacher is the responsible for the course
     */
    public CourseTeacher(int courseTeacherID, Course course, Teacher teacher, boolean isResponsible) {
        this.courseTeacherID = courseTeacherID;
        this.course = course;
        this.teacher = teacher;
        this.isResponsible = isResponsible;
    }

    /**
     * Getters of the attributes of the class CourseTeachers
     * @return the attribute
     */
    public int getCourseTeacherID() { return courseTeacherID; }
    public Course getCourse() { return course; }
    public Teacher getTeacher() { return teacher; }
    public boolean isResponsible() { return isResponsible; }

    /**
     * Setters of the attributes of the class CourseTeacher
     * The params are the attribute to change
     */
    public void setCourse(Course course) { this.course = course; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    public void setIsResponsible(boolean isResponsible) { this.isResponsible = isResponsible; }
}
