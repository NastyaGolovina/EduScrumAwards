package UPT_SQ.EduScrumAwards.DTO;

import java.util.Date;

public class CourseAverageDTO {
    private int courseId;
    private String courseName;
    private double courseAverage;
    private int studentPointValue;

    public CourseAverageDTO(int courseId, String courseName, double courseAverage, int studentPointValue) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseAverage = courseAverage;
        this.studentPointValue = studentPointValue;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseAverage() {
        return courseAverage;
    }

    public void setCourseAverage(double courseAverage) {
        this.courseAverage = courseAverage;
    }

    public int getStudentPointValue() {
        return studentPointValue;
    }

    public void setStudentPointValue(int studentPointValue) {
        this.studentPointValue = studentPointValue;
    }
}
