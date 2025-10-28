/**
 * File with the description of CourseTeacher
 * Project - EduScrumAwards
 * Subject - Software Quality
 * Teacher - Fátima Leal
 * Developers of the file - José Ángel (53150)
 * Date - 28/10/2025 (SPRINT 1)
 * Version: 0.0
 */
package UPT_SQ.EduScrumAwards.model;

/**
 * Class that represente a teacher as a course director
 */
public class CourseTeacher {
    private final int courseTeacherID;
    private Course course;
    // private Teacher teacher;
    private boolean isResponsible;

    /**
     * Constructor of the class CourseTeacher
     * @param courseTeacherID Integer of the ID
     * @param course Course
     * @param isResponsible Boolean that indicates if the teacher is the responsible for the course
     */
    public CourseTeacher(int courseTeacherID, Course course, boolean isResponsible) {
        this.courseTeacherID = courseTeacherID;
        this.course = course;
        this.isResponsible = isResponsible;
    }

    /**
     * Getters of the attributes of the class CourseTeachers
     * @return the attribute
     */
    public int getCourseTeacherID() { return courseTeacherID; }
    public Course getCourse() { return course; }
    public boolean isResponsible() { return isResponsible; }

    /**
     * Setters of the attributes of the class CourseTeacher
     * The params are the attribute to change
     */
    public void setCourse(Course course) { this.course = course; }
    public void setIsResponsible(boolean isResponsible) { this.isResponsible = isResponsible; }
}
