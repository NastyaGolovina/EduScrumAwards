/**
 * File with the description of Course class
 * Project - EduScrumAwards
 * Subject - Software Quality
 * Teacher - Fátima Leal
 * Developers of the file - José Ángel (53150)
 * Date - 28/10/2025 (SPRINT 1)
 * Version: 0.0
 */
package UPT_SQ.EduScrumAwards.model;

import java.util.ArrayList;

/**
 * Class Course which represents a Course with all it's attributes, identification, participants and projects
 */
public class Course {
    private final int courseID;
    private String courseName;
    private String courseDescription;
    private int year;
    private int semester;
    // private ArrayList<Teacher> teachers;
    // private ArrayList<Student> students;
    // private ArrayList<Project> projects;

    /**
     * Constructor of the class Course
     * @param courseID Integer with the ID
     * @param courseName String with the name
     * @param courseDescription String with the description
     * @param year Integer with the year
     * @param semester Integer with the semester
     */
    public Course(int courseID, String courseName, String courseDescription, int year, int semester) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        if (courseID > 0 && courseID < 5) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("ConstructorError; Invalid year. Should be between 1 and 4.");
        }
        if (semester > 0 && semester < 3) {
            this.semester = semester;
        }  else {
            throw new IllegalArgumentException("ConstructorError; Invalid semester. Should be between 1 and 2.");
        }
    }

    /**
     * Getters of the attributes of the class Course
     * @return the attribute
     */
    public int getCourseID() { return courseID; }
    public String getCourseName() { return courseName; }
    public String getCourseDescription() { return courseDescription; }
    public int getYear() { return year; }
    public int getSemester() { return semester; }

    /**
     * Setters of the attributes of the class Course
     * The params are the attribute to change
     */
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }
    public void setYear(int year) { this.year = year; }
    public void setSemester(int semester) { this.semester = semester; }
}
