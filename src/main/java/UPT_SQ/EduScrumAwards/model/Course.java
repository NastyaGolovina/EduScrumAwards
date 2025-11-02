/**
 * File with the description of Course class
 * Project - EduScrumAwards
 * Subject - Software Quality
 * Teacher - Fátima Leal
 * Developers of the file - José Ángel (53150)
 * Date - 29/10/2025 (SPRINT 1)
 * Version: 0.2
 */
package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.ArrayList;

/**
 * Class Course which represents a Course with all it's attributes, identification, participants and projects
 */
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName;

    @Transient
    private ArrayList<CourseTeacher> projects;

    /**
     * No-args constructor required by Hibernate
     */
    public Course() {}

    /**
     * Constructor of the class Course
     * @param courseID Integer with the ID
     * @param courseName String with the name
     */
    public Course(int courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    /**
     * Getters of the attributes of the class Course
     * @return the attribute
     */
    public int getCourseID() { return courseID; }
    public String getCourseName() { return courseName; }

    /**
     * Setters of the attributes of the class Course
     * The params are the attribute to change
     */
    public void setCourseID(int courseID) { this.courseID = courseID; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}
