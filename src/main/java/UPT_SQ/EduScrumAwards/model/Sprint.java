package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a Sprint entity in the EduScrum Awards system.
 *
 * A Sprint belongs to a specific Project and can have multiple Goals.
 * Provides methods to manage Goals and calculate scores.
 * Mapped to the "sprints" table in the database.
 *
 * @author Sania Fatima
 * @version 1.0
 * @since 2025-10-31
 */
@Entity
@Table(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    private int sprintId;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Goal> goals = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /** Default no-argument constructor required by JPA */
    public Sprint() {}

    /** Full constructor */
    public Sprint(int sprintId, Date startDate, Date endDate, List<Goal> goals, Project project) {
        this.sprintId = sprintId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goals = goals != null ? goals : new ArrayList<>();
        this.project = project;
    }

    /** Constructor without ID (for creating new Sprints) */
    public Sprint(Date startDate, Date endDate, List<Goal> goals, Project project) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.goals = goals != null ? goals : new ArrayList<>();
        this.project = project;
    }

    /** Constructor with only dates */
    public Sprint(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getSprintId() { return sprintId; }
    public void setSprintId(int sprintId) { this.sprintId = sprintId; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public List<Goal> getGoals() { return goals; }
    public void setGoals(List<Goal> goals) { this.goals = goals != null ? goals : new ArrayList<>(); }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    /** Add an existing Goal to this Sprint */
    public String addGoal(Goal goal) {
        if (goal == null) return "Goal cannot be null!";
        if (goals == null) goals = new ArrayList<>();
        goal.setSprint(this);
        goals.add(goal);

        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(goal);
        session.getTransaction().commit();
        session.close();
        db.exit();

        return "Goal added successfully!";
    }

    /** Create a Goal from parameters and add it */
    public String createGoal(String description, int score) {
        if (description == null || description.isEmpty()) return "Goal description cannot be empty!";
        if (score <= 0) return "Goal score must be positive!";
        Goal goal = new Goal(description, score);
        return addGoal(goal);
    }

    /** Update an existing Goal */
    public String updateGoal(int goalId, String description, int score, boolean completed) {
        if (goals == null || goals.isEmpty()) return "No goals found!";
        Goal goal = goals.stream().filter(g -> g.getGoalId() == goalId).findFirst().orElse(null);
        if (goal == null) return "Goal not found!";

        goal.setDescription(description);
        goal.setScore(score);
        goal.setCompleted(completed);

        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(goal);
        session.getTransaction().commit();
        session.close();
        db.exit();

        return "Goal updated successfully!";
    }

    public void retrieveGoals() {
        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        session.beginTransaction();

        List<Goal> goalList = session.createQuery(
                        "SELECT g FROM Goal g WHERE g.sprint.sprintId = :sid", Goal.class
                )
                .setParameter("sid", this.sprintId)
                .getResultList();

        this.goals = new ArrayList<>(goalList);

        session.getTransaction().commit();
        session.close();
        db.exit();
    }

    /** Calculate total score of all Goals */
    public double calcTotalScore() {
        if (goals == null || goals.isEmpty()) return 0;
        return goals.stream().mapToInt(Goal::getScore).sum();
    }

    /** Calculate completion percentage based on Goal scores */
    public double calcCompletionByScore() {
        double totalScore = calcTotalScore();
        if (totalScore == 0) return 0;
        double completedScore = goals.stream()
                .filter(Goal::isCompleted)
                .mapToInt(Goal::getScore)
                .sum();
        return (completedScore / totalScore) * 100;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "sprintId=" + sprintId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", goals=" + goals +
                '}';
    }
}
