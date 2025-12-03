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

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Course which represents a Course with all it's attributes,
 * identification, participants and projects
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

    /**
     * Teachers for this course (CourseTeacher rows).
     * Mapped by the "course" field in CourseTeacher.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseTeacher> courseTeachers;

    /**
     * The list of projects that belong to this course.
     * Mapped by the "course" field in Project.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Project> projects;

    // Students list (association) could be added here in the future

    /**
     * No-args constructor required by Hibernate
     */
    public Course() {
        courseTeachers = new ArrayList<>();
        projects = new ArrayList<>();
    }

    /**
     * Constructor of the class Course
     * 
     * @param courseName String with the name
     */
    public Course(String courseName) {
        this.courseName = courseName;
        courseTeachers = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public void setCourseTeachers(List<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    public List<CourseTeacher> getCourseTeachers() {
        if (courseTeachers == null) {
            courseTeachers = new ArrayList<>();
        }
        return courseTeachers;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * Getters of the attributes of the class Course
     * 
     * @return the attribute
     */
    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    @JsonIgnore
    public List<CourseTeacher> getTeachers() {
        if (courseTeachers == null)
            courseTeachers = new ArrayList<>();
        return courseTeachers;
    }

    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Setters of the attributes of the class Course
     * The params are the attribute to change
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @JsonIgnore
    public void setTeachers(List<CourseTeacher> teachers) {
        this.courseTeachers = teachers;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Finds a CourseTeacher by ID within this course.
     * 
     * @param courseTeacherId CourseTeacher ID
     * @return the found CourseTeacher or null if not present
     */
    public CourseTeacher findCourseTeacherById(int courseTeacherId) {
        if (courseTeachers == null)
            return null;
        for (CourseTeacher ct : courseTeachers) {
            if (ct != null && ct.getCourseTeacherID() == courseTeacherId) {
                return ct;
            }
        }
        return null;
    }

    /**
     * Finds a CourseTeacher by the associated Teacher's name within this course.
     * Comparison is case-insensitive and trims surrounding whitespace.
     * 
     * @param name Teacher's name
     * @return the CourseTeacher whose Teacher has that name, or null if not found
     */
    public CourseTeacher findCourseTeacherByName(String name) {
        if (courseTeachers == null || name == null)
            return null;
        String target = name.trim();
        for (CourseTeacher ct : courseTeachers) {
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
     * Finds and returns the same instance of CourseTeacher if it exists in this
     * course's collection.
     * First attempts by ID (>0); if not found, compares by reference/equals.
     * 
     * @param courseTeacher the object to search for
     * @return the stored instance or null
     */
    public CourseTeacher findCourseTeacher(CourseTeacher courseTeacher) {
        if (courseTeachers == null || courseTeacher == null)
            return null;
        int id = courseTeacher.getCourseTeacherID();
        if (id > 0) {
            CourseTeacher byId = findCourseTeacherById(id);
            if (byId != null)
                return byId;
        }
        for (CourseTeacher ct : courseTeachers) {
            if (ct == courseTeacher || (ct != null && ct.equals(courseTeacher))) {
                return ct;
            }
        }
        return null;
    }

    /**
     * Finds a Project by ID within this course.
     * 
     * @param projectId project ID
     * @return the found Project or null if not present
     */
    public Project findProjectById(int projectId) {
        if (projects == null)
            return null;
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
     * 
     * @param name project name
     * @return the found Project or null if not present
     */
    public Project findProjectByName(String name) {
        if (projects == null || name == null)
            return null;
        String target = name.trim();
        for (Project p : projects) {
            if (p != null && equalsIgnoreCaseAndTrim(p.getProjectName(), target)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds and returns the same instance of Project if it exists in this course's
     * collection.
     * First attempts by ID (>0); if not found, compares by reference/equals.
     * 
     * @param project the object to search for
     * @return the stored instance or null
     */
    public Project findProject(Project project) {
        if (projects == null || project == null)
            return null;
        int id = project.getProjectId();
        if (id > 0) {
            Project byId = findProjectById(id);
            if (byId != null)
                return byId;
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
        if (a == null || b == null)
            return false;
        return a.trim().equalsIgnoreCase(b.trim());
    }

    /**
     * Check if a teacher is a CT of the course
     * 
     * @param t Teacher
     * @return true if it is, false otherwise
     */
    public boolean isCourseTeacher(Teacher t) {
        for (CourseTeacher ct : courseTeachers) {
            if (ct.getTeacher() == t) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new CourseTeacher and saves it to the database.
     *
     * @param teacher       the associated Teacher (must not be null)
     * @param isResponsible whether the teacher is responsible for the course
     * @return "Success" if created and saved; error message otherwise
     */
    public String createCourseTeacher(Teacher teacher, boolean isResponsible) {

        if (teacher == null) {
            return "ERROR: Teacher is null!";
        }
        CourseTeacher newCt = new CourseTeacher(this, teacher, isResponsible);
        courseTeachers.add(newCt);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(newCt);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();
        return "Success";
    }

    /**
     * Loads all CourseTeacher objects from the database and stores them locally.
     */
    public void readAllCourseTeacherWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        try {
            List<CourseTeacher> list = session.createQuery(
                    "SELECT ct FROM CourseTeacher ct WHERE ct.course.courseID = :cid",
                    CourseTeacher.class)
                    .setParameter("cid", this.courseID)
                    .getResultList();
            this.courseTeachers = new ArrayList<>(list);
        } finally {
            session.close();
            DatabaseHelper.exit();
        }
    }

    /**
     * Searches the local list of CourseTeacher by ID.
     * 
     * @param id CourseTeacher ID
     * @return the CourseTeacher if found, otherwise null
     */
    public CourseTeacher searchCourseTeacher(int id) {
        int i = 0;
        while (i < courseTeachers.size() && courseTeachers.get(i).getCourseTeacherID() != id) {
            i++;
        }
        if (i != courseTeachers.size()) {
            return courseTeachers.get(i);
        }
        return null;
    }

    /**
     * Updates the isResponsible flag of a CourseTeacher and merges it in the
     * database.
     * 
     * @param id            the CourseTeacher ID
     * @param isResponsible new value for responsibility flag
     * @return "Success" if updated; error message otherwise
     */
    public String updateCourseTeacher(int id, boolean isResponsible) {
        CourseTeacher ct = searchCourseTeacher(id);
        if (ct != null) {
            ct.setIsResponsible(isResponsible);

            DatabaseHelper DatabaseHelper = new DatabaseHelper();
            DatabaseHelper.setup();
            Session session = DatabaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            session.merge(ct);

            session.getTransaction().commit();
            session.close();
            DatabaseHelper.exit();
            return "Success";
        }
        return "ERROR: CourseTeacher with this id does not exist";
    }

    /**
     * Deletes a CourseTeacher by its ID from the database and local list.
     *
     * @param id the CourseTeacher ID to delete
     * @return "Success" if deleted; error message otherwise
     */
    public String deleteCourseTeacher(int id) {
        CourseTeacher ct = searchCourseTeacher(id);
        if (ct == null) {
            return "ERROR: CourseTeacher with ID " + id + " does not exist";
        }

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = null;
        try {
            session = databaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            CourseTeacher managedCt = session.merge(ct);
            session.remove(managedCt);

            session.getTransaction().commit();

            courseTeachers.remove(ct);

            return "Success";

        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return "ERROR: Failed to delete CourseTeacher: " + e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
            databaseHelper.exit();
        }
    }

    // Project CRUD

    /** Search for a project by its ID */
    public Project searchProject(int projectId) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();

        Project project = session.get(Project.class, projectId);

        session.close();
        dbHelper.exit();

        return project;
    }

    /** Retrieves all projects for this course and fully loads related arrays */
    public void retrieveProjects() {
        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        session.beginTransaction();

        List<Project> projectList = session.createQuery(
                        "SELECT p FROM Project p WHERE p.course.courseID = :cid",
                        Project.class)
                .setParameter("cid", this.courseID)
                .getResultList();

        this.projects = new ArrayList<>(projectList);

        // Fully load related arrays
        for (Project p : projects) {

            // Load Sprints + Goals
            p.retrieveSprints();

            // Load Team Members
            Team team = p.getTeam();
            if (team != null) {
                if (!session.contains(team)) {
                    team = session.merge(team);
                }
                if (team.getTeamMember() != null) {
                    team.getTeamMember().size();
                }
            }
        }

        session.getTransaction().commit();
        session.close();
        db.exit();
    }

    /** Create a new Project and fully load related arrays */
    public String createProject(String projectName, Team team, Course course) {
        if (projectName == null || projectName.isEmpty())
            return "Project name cannot be empty!";
        if (team == null)
            return "Team cannot be null!";
        if (course == null)
            return "Course cannot be null!";

        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();
        session.beginTransaction();

        // Validate one-to-one team assignment
        Long count = session.createQuery(
                        "SELECT COUNT(p) FROM Project p WHERE p.team.teamID = :tid",
                        Long.class)
                .setParameter("tid", team.getTeamID())
                .uniqueResult();

        if (count != null && count > 0) {
            session.getTransaction().rollback();
            session.close();
            dbHelper.exit();
            return "This team is already assigned to another project!";
        }

        // Create project
        Project project = new Project();
        project.setProjectName(projectName);
        project.setTeam(team);
        project.setCourse(course);

        session.persist(project);
        session.getTransaction().commit();
        session.close();
        dbHelper.exit();

        // Add to local list
        if (this.projects == null) this.projects = new ArrayList<>();
        this.projects.add(project);

        // Initialize in-memory arrays
        if (project.getSprints() == null) project.setSprints(new ArrayList<>());
        if (team.getTeamMember() == null) team.setTeamMember(new ArrayList<>());

        return "Project added successfully!";
    }


    /** Update an existing Project */
    public String updateProject(int projectId, String projectName) {
        Project project = searchProject(projectId);
        if (project == null)
            return "Project with ID " + projectId + " not found!";

        if (projectName != null && !projectName.isEmpty())
            project.setProjectName(projectName);

        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();
        session.beginTransaction();

        Project managed = (Project) session.merge(project);
        session.getTransaction().commit();
        session.close();
        dbHelper.exit();

        // Update in-memory list
        if (this.projects != null) {
            for (int i = 0; i < this.projects.size(); i++) {
                if (this.projects.get(i).getProjectId() == managed.getProjectId()) {
                    this.projects.set(i, managed);
                    break;
                }
            }
        }

        return "Project updated Successfully!";
    }


    /** Delete an existing Project that belongs to this Course */
    public String deleteProject(int projectId) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = null;

        try {
            session = dbHelper.getSessionFactory().openSession();
            session.beginTransaction();

            // Load the project and ensure it belongs to this course
            Project project = session.get(Project.class, projectId);
            if (project == null) {
                session.getTransaction().rollback();
                return "ERROR: Project not found!";
            }
            if (project.getCourse() == null || project.getCourse().getCourseID() != this.courseID) {
                session.getTransaction().rollback();
                return "ERROR: Project does not belong to this course!";
            }

            // Block deletion if there are StudentAward or AwardRule references
            Long awardsCount = session.createQuery(
                            "SELECT COUNT(sa) FROM StudentAward sa WHERE sa.project.projectId = :pid", Long.class)
                    .setParameter("pid", projectId)
                    .uniqueResult();

            Long rulesCount = session.createQuery(
                            "SELECT COUNT(ar) FROM AwardRule ar WHERE ar.project.projectId = :pid", Long.class)
                    .setParameter("pid", projectId)
                    .uniqueResult();

            if ((awardsCount != null && awardsCount > 0) || (rulesCount != null && rulesCount > 0)) {
                session.getTransaction().rollback();
                return "Cannot delete project: linked StudentAward or AwardRule exists.";
            }

            // Clear project's sprints and goals to allow orphan removal
            if (project.getSprints() != null) {
                for (Sprint s : project.getSprints()) {
                    if (s.getGoals() != null) {
                        s.getGoals().clear(); // remove all goals
                    }
                }
                project.getSprints().clear();
            }

            // Delete the project itself
            if (!session.contains(project)) {
                project = session.merge(project);
            }
            session.remove(project);

            // Delete the team (and its members) if present
            Team team = project.getTeam();
            if (team != null) {
                if (!session.contains(team)) {
                    team = session.merge(team);
                }
                if (team.getTeamMember() != null) {
                    team.getTeamMember().clear();
                }
                session.remove(team);
            }

            session.getTransaction().commit();

            // Keep in-memory list in sync
            if (this.projects != null) {
                this.projects.removeIf(p -> p.getProjectId() == projectId);
            }

            return "Project deleted successfully!";
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return "ERROR: Failed to delete project. Reason: " + e.getMessage();
        } finally {
            if (session != null) session.close();
            dbHelper.exit();
        }
    }
}
