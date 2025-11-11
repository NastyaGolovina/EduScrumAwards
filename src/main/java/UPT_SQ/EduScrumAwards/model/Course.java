/**
 * File with the description of Course class
 * Project - EduScrumAwards
 * Subject - Software Quality
 * Teacher - Fátima Leal
 * Developers of the file - José Ángel (53150)
 * Date - 29/10/2025 (SPRINT 1)
 * Version: 0.3
 */
package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Class Course which represents a Course with all it's attributes, identification, participants and projects
 */
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private int courseID;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName;

    /**
     * Teachers for this course (CourseTeacher rows).
     * Mapped by the "course" field in CourseTeacher.
     */
    @OneToMany(mappedBy = "course")
    private List<CourseTeacher> teachers;

    /**
     * The list of projects that belong to this course.
     * Mapped by the "course" field in Project.
     */
    @OneToMany(mappedBy = "course")
    private List<Project> projects;
    
    // Students list (association) could be added here in the future

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
    public List<CourseTeacher> getTeachers() { return teachers; }
    public List<Project> getProjects() { return projects; }

    /**
     * Setters of the attributes of the class Course
     * The params are the attribute to change
     */
    public void setCourseID(int courseID) { this.courseID = courseID; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setTeachers(List<CourseTeacher> teachers) { this.teachers = teachers; }
    public void setProjects(List<Project> projects) { this.projects = projects; }
    

    /**
     * Finds a CourseTeacher by ID within this course.
     * @param courseTeacherId CourseTeacher ID
     * @return the found CourseTeacher or null if not present
     */
    public CourseTeacher findCourseTeacherById(int courseTeacherId) {
        if (teachers == null) return null;
        for (CourseTeacher ct : teachers) {
            if (ct != null && ct.getCourseTeacherID() == courseTeacherId) {
                return ct;
            }
        }
        return null;
    }

    /**
     * Finds a CourseTeacher by the associated Teacher's name within this course.
     * Comparison is case-insensitive and trims surrounding whitespace.
     * @param name Teacher's name
     * @return the CourseTeacher whose Teacher has that name, or null if not found
     */
    public CourseTeacher findCourseTeacherByName(String name) {
        if (teachers == null || name == null) return null;
        String target = name.trim();
        for (CourseTeacher ct : teachers) {
            if (ct != null && ct.getTeacher() != null) {
                String teacherName = ct.getTeacher().getName();
                if (equalsIgnoreCaseAndTrim(teacherName, target)) {
                    return ct;
                }
            }
        }
        return null;
    }

    /**
     * Finds and returns the same instance of CourseTeacher if it exists in this course's collection.
     * First attempts by ID (>0); if not found, compares by reference/equals.
     * @param courseTeacher the object to search for
     * @return the stored instance or null
     */
    public CourseTeacher findCourseTeacher(CourseTeacher courseTeacher) {
        if (teachers == null || courseTeacher == null) return null;
        int id = courseTeacher.getCourseTeacherID();
        if (id > 0) {
            CourseTeacher byId = findCourseTeacherById(id);
            if (byId != null) return byId;
        }
        for (CourseTeacher ct : teachers) {
            if (ct == courseTeacher || (ct != null && ct.equals(courseTeacher))) {
                return ct;
            }
        }
        return null;
    }

    /**
     * Finds a Project by ID within this course.
     * @param projectId project ID
     * @return the found Project or null if not present
     */
    public Project findProjectById(int projectId) {
        if (projects == null) return null;
        for (Project p : projects) {
            if (p != null && p.getProjectId() == projectId) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds a Project by name within this course.
     * Comparison is case-insensitive and trims surrounding whitespace.
     * @param name project name
     * @return the found Project or null if not present
     */
    public Project findProjectByName(String name) {
        if (projects == null || name == null) return null;
        String target = name.trim();
        for (Project p : projects) {
            if (p != null && equalsIgnoreCaseAndTrim(p.getProjectName(), target)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds and returns the same instance of Project if it exists in this course's collection.
     * First attempts by ID (>0); if not found, compares by reference/equals.
     * @param project the object to search for
     * @return the stored instance or null
     */
    public Project findProject(Project project) {
        if (projects == null || project == null) return null;
        int id = project.getProjectId();
        if (id > 0) {
            Project byId = findProjectById(id);
            if (byId != null) return byId;
        }
        for (Project p : projects) {
            if (p == project || (p != null && p.equals(project))) {
                return p;
            }
        }
        return null;
    }

    // Utility method to compare strings ignoring case and trimming whitespace.
    private static boolean equalsIgnoreCaseAndTrim(String a, String b) {
        if (a == null || b == null) return false;
        return a.trim().equalsIgnoreCase(b.trim());
    }
}
