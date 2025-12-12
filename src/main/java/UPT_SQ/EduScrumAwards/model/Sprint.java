package UPT_SQ.EduScrumAwards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

    @JsonIgnore
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

// ---------- CREATE ----------
    /** Add an existing Goal or create a new one in DB */
    public String addGoal(Goal goal) {
        if (goal == null) return "Goal cannot be null!";
        if (goals == null) goals = new ArrayList<>();
        goal.setSprint(this);
        goals.add(goal);

        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(goal);  // save to DB
            session.getTransaction().commit();
            return "Goal added successfully!";
        } catch (Exception e) {
            if (session.getTransaction().isActive()) session.getTransaction().rollback();
            return "ERROR: Failed to add goal. " + e.getMessage();
        } finally {
            session.close();
            db.exit();
        }
    }

    /** Create a Goal from parameters and add it */
    public String createGoal(String description, int score) {
        if (description == null || description.isEmpty()) return "Goal description cannot be empty!";
        if (score <= 0) return "Goal score must be positive!";
        Goal goal = new Goal(description, score);
        return addGoal(goal);
    }

    /** Load all goals from DB */
    public void retrieveGoals() {
        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();
        try {
            List<Goal> goalList = session.createQuery(
                            "SELECT g FROM Goal g WHERE g.sprint.sprintId = :sid", Goal.class)
                    .setParameter("sid", this.sprintId)
                    .getResultList();
            this.goals = new ArrayList<>(goalList);
        } finally {
            session.close();
            db.exit();
        }
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
        try {
            session.beginTransaction();
            session.merge(goal);  // update DB
            session.getTransaction().commit();
            return "Goal updated successfully!";
        } catch (Exception e) {
            if (session.getTransaction().isActive()) session.getTransaction().rollback();
            return "ERROR: Failed to update goal. " + e.getMessage();
        } finally {
            session.close();
            db.exit();
        }
    }

    /** Delete a Goal by ID */
    public String deleteGoal(int goalId) {

        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            // Load managed entity from DB (NOT the one in Global)
            Goal managedGoal = session.get(Goal.class, goalId);
            if (managedGoal == null)
                return "Goal not found in DB!";

            // Break relationship WITH sprint
            Sprint sprint = managedGoal.getSprint();
            if (sprint != null) {
                sprint.getGoals().remove(managedGoal);  // remove from list
                managedGoal.setSprint(null);            // remove owner side
                session.merge(sprint);
            }

            session.remove(managedGoal);
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive())
                session.getTransaction().rollback();
            return "ERROR: Failed to delete goal. " + e.getMessage();
        } finally {
            session.close();
            db.exit();
        }

        // Also remove from Global sprint list
        if (goals != null)
            goals.removeIf(g -> g.getGoalId() == goalId);

        return "Goal deleted successfully!";
    }

    public Goal findGoalById(int goalId) {
        for (Goal g : goals) {
            if (g.getGoalId() == goalId) return g;
        }
        return null;
    }


    /** Calculate total score of all Goals */
    public double calcTotalScore() {
        if (goals == null || goals.isEmpty()) return 0;

        int total = 0;
        for (Goal g : goals) {
            total += g.getScore();
        }
        return total;
    }

    /** Calculate completion percentage based on Goal scores */
    public double calcCompletionByScore() {
        double totalScore = calcTotalScore();
        if (totalScore == 0) return 0;

        int completedScore = 0;
        for (Goal g : goals) {
            if (g.isCompleted()) {
                completedScore += g.getScore();
            }
        }

        return (completedScore / totalScore) * 100;
    }


    /**
     * Checks whether a specific award rule has already been assigned to a student
     * within a list of existing student awards for the same sprint.
     *
     * This method iterates over all provided {@link StudentAward} objects and
     * compares both the sprint ID and the rule ID to determine whether the rule
     * has been previously applied.
     *
     * @param studentAwards the list of existing student awards to check
     * @param rule the award rule to verify
     * @return {@code true} if the rule has already been assigned for this sprint,
     *         {@code false} otherwise
     */
    public boolean isRuleAssigned(ArrayList<StudentAward> studentAwards,AwardRule rule) {
        for (StudentAward studentAward : studentAwards) {
            if(studentAward.getSprint() != null && studentAward.getRule() != null) {
                if(studentAward.getSprint().getSprintId() == this.sprintId &&
                        studentAward.getRule().getRuleId() == rule.getRuleId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Automatically assigns awards to students based on project progress and
     * award rules.
     *
     * The method evaluates each available {@link Award} and its associated
     * {@link AwardRule} objects. If the rule matches the current project's ID and
     * the project's completion percentage meets the rule's requirements, a new
     * {@link StudentAward} is created for each team member—provided the rule has
     * not already been assigned.
     *
     * <p>Each newly created {@link StudentAward} is persisted to the database.
     * The method returns a success status or an error message if persistence fails.</p>
     *
     * @param awards the list of available awards
     * @param studentAwards the list of existing student awards
     * @return "Success" if awards are assigned or no action is needed;
     *         otherwise an error message if persistence fails
     */
    public String assignAutomaticAward(ArrayList<Award> awards, ArrayList<StudentAward> studentAwards) {
        for(Award award : awards){
            ArrayList<AwardRule> rules = award.getAwardRules();
            if(rules==null || rules.isEmpty()) continue;
            for(AwardRule rule : rules){
                if(rule.getProject().getProjectId() == this.project.getProjectId()) {
                    double completion = calcCompletionByScore();
                    if (completion >= rule.getCompletionPercent() ||
                            (rule.isAllGoalsCompleted() && completion == 100.0)) {
//                        System.out.println("Automatic award assigned successfully!" + rule.getRuleId());
                        if(!isRuleAssigned(studentAwards, rule)) {
                            try {

                                for(TeamMember teamMember : project.getTeam().getTeamMember()) {
                                    StudentAward studentAward = new StudentAward(
                                            award,
                                            teamMember.getStudent(),
                                            rule.getTeacher(),
                                            this.project,
                                            this.project.getTeam(),
                                            Date.from(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).atZone(ZoneId.systemDefault()).toInstant()),
                                            award.getPointsValue(),
                                            this,
                                            rule);

                                    studentAwards.add(studentAward);

                                    DatabaseHelper databaseHelper = new DatabaseHelper();
                                    databaseHelper.setup();
                                    Session session = databaseHelper.getSessionFactory().openSession();
                                    session.beginTransaction();

                                    session.persist(studentAward);

                                    session.getTransaction().commit();
                                    session.close();
                                    databaseHelper.exit();

//                                    return "Success";
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                return "ERROR: Failed to persist StudentAward - " + e.getMessage();
                            }
                        }
                    }
                }
            }
        }
        return "Success";
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
