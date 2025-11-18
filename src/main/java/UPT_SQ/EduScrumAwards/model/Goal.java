package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

/**
 * Represents a Goal within a Sprint in the EduScrum Awards system.
 *
 * A Goal has a description, a score value, and a completion status.
 * Each Goal belongs to a specific Sprint.
 *
 * Author: Sania Fatima
 * Version: 1.0
 * Since: 2025-10-31
 */
@Entity
@Table(name = "goals")
public class Goal {

    /** Primary key of the Goal, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private int goalId;

    /** Description of the goal (cannot be null). */
    @Column(name = "description", nullable = false)
    private String description;

    /** Score value associated with this goal (cannot be null). */
    @Column(name = "score", nullable = false)
    private int score;

    /** Completion status of the goal. */
    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    /** The Sprint this Goal belongs to (many goals can belong to one sprint). */
    @ManyToOne
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    /** Default no-argument constructor required by JPA. */
    public Goal() {}

    /** Constructor for creating a goal with description, score, and completion status. */
    public Goal(String description, int score, boolean isCompleted) {
        this.description = description;
        this.score = score;
        this.isCompleted = isCompleted;
    }

    /** Constructor for creating a goal with description and score */
    public Goal(String description, int score) {
        this.description = description;
        this.score = score;
        this.isCompleted = false;
    }

    /** Constructor with all fields including goalId */
    public Goal(int goalId, String description, int score, boolean isCompleted) {
        this.goalId = goalId;
        this.description = description;
        this.score = score;
        this.isCompleted = isCompleted;
    }

    // ---------------- Getters and Setters ----------------

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    /** Marks this goal as completed. */
    public void completeGoal() {
        this.isCompleted = true;
    }


    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", isCompleted=" + isCompleted +
                ", sprintId=" + (sprint != null ? sprint.getSprintId() : null) +
                '}';
    }
}
