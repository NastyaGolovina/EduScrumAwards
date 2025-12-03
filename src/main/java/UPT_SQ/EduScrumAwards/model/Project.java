package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a Project entity in the EduScrum Awards system.
 *
 * A Project has a name, belongs to a Team and a Course, and contains multiple Sprints.
 * This class also contains methods to manage Sprints and retrieve Student Awards for this Project.
 *
 * @author Sania Fatima
 * @version 1.0
 * @since 2025-10-31
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "project_name", length = 50, nullable = false)
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "Team_ID", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints = new ArrayList<>();

    /** Default no-argument constructor required by JPA */
    public Project() {}

    /** Constructor with all fields */
    public Project(int projectId, String projectName, Team team, Course course, List<Sprint> sprints) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.team = team;
        this.course = course;
        this.sprints = sprints != null ? sprints : new ArrayList<>();
    }

    // Getters and setters
    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<Sprint> getSprints() { return sprints; }
    public void setSprints(List<Sprint> sprints) { this.sprints = sprints != null ? sprints : new ArrayList<>(); }

    /** Search for a sprint by its ID */
    public Sprint searchSprint(int sprintId) {
        if (sprints == null || sprints.isEmpty()) return null;
        for (Sprint sprint : sprints) {
            if (sprint.getSprintId() == sprintId) return sprint;
        }
        return null;
    }

    /** Retrieves all sprints from the database for this project */
    public void retrieveSprints() {
        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        session.beginTransaction();

        List<Sprint> sprintList = session.createQuery(
                        "SELECT s FROM Sprint s WHERE s.project.projectId = :pid",
                        Sprint.class)
                .setParameter("pid", this.projectId)
                .getResultList();

        this.sprints = new ArrayList<>(sprintList);

        // load goals for each sprint
        for (Sprint s : this.sprints) {
            if (s != null) {
                s.retrieveGoals();
            }
        }

        session.getTransaction().commit();
        session.close();
        db.exit();
    }

    /** Creates a new Sprint for this project */
    public String createSprint(Date startDate, Date endDate) {
        if (sprints == null) sprints = new ArrayList<>();

        Sprint sprint = new Sprint(startDate, endDate, new ArrayList<>(), this);
        sprints.add(sprint);

        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(sprint);
        session.getTransaction().commit();
        session.close();
        dbHelper.exit();

        return "Sprint added Successfully!";
    }

    /** Updates an existing Sprint */
    public String updateSprint(int sprintId, Date startDate, Date endDate) {
        if (sprints == null || sprints.isEmpty()) return "No sprints found for this project.";

        Sprint sprint = searchSprint(sprintId);
        if (sprint == null) return "Sprint with ID " + sprintId + " not found!";

        sprint.setStartDate(startDate);
        sprint.setEndDate(endDate);

        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(sprint);
        session.getTransaction().commit();
        session.close();
        dbHelper.exit();

        return "Sprint updated Successfully!";
    }

    /** Deletes a Sprint by ID if no StudentAwards are linked to it */
    public String deleteSprint(int sprintId) {
        if (sprints == null || sprints.isEmpty()) return "No sprints to delete!";

        Sprint sprintToDelete = searchSprint(sprintId);
        if (sprintToDelete == null) return "Sprint with ID " + sprintId + " not found!";

        // Check if there are StudentAwards linked to this Sprint
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();
        try {
            Long awardsCount = session.createQuery(
                    "SELECT COUNT(sa) FROM StudentAward sa WHERE sa.sprint.sprintId = :sid",
                    Long.class
            ).setParameter("sid", sprintId).getSingleResult();

            if (awardsCount != null && awardsCount > 0) {
                return "Cannot delete Sprint: StudentAwards are linked to it!";
            }

            // Begin transaction to delete Sprint and its goals
            session.beginTransaction();

            // Remove Sprint (cascade will delete Goals due to orphanRemoval = true)
            if (!session.contains(sprintToDelete)) {
                sprintToDelete = session.merge(sprintToDelete);
            }
            session.remove(sprintToDelete);

            session.getTransaction().commit();

            // Remove from in-memory list
            sprints.removeIf(s -> s.getSprintId() == sprintId);

            return "Sprint deleted successfully!";
        } catch (Exception e) {
            if (session.getTransaction().isActive()) session.getTransaction().rollback();
            return "ERROR: Failed to delete Sprint. " + e.getMessage();
        } finally {
            session.close();
            dbHelper.exit();
        }
    }


    /** Retrieves all student awards for this project */
    public ArrayList<StudentAward> studentsAwards() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.setup();
        Session session = dbHelper.getSessionFactory().openSession();

        List<StudentAward> list = session.createQuery(
                        "SELECT sa FROM StudentAward sa WHERE sa.project.projectId = :projectId",
                        StudentAward.class
                )
                .setParameter("projectId", this.projectId)
                .getResultList();

        session.close();
        dbHelper.exit();

        return new ArrayList<>(list != null ? list : new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", team=" + team +
                ", course=" + course +
                ", sprints=" + sprints +
                '}';
    }
}
