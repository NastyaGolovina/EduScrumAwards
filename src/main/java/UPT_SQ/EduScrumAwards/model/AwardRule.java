package UPT_SQ.EduScrumAwards.model;


import jakarta.persistence.*;
/**
 * Represents the rules associated with an award in the system.
 * Each rule defines criteria such as completion percentage,
 * goal completion status, and related entities like the award,
 * project, and teacher.
 *
 * @author Anastasiia Holovina
 * @version 1.0
 * @since 2025-10-31
 */
@Entity
@Table(name = "Award_Rules")
public class AwardRule {
    @Id
    @Column(name = "rule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ruleId;
    @ManyToOne
    @JoinColumn(name = "award_id")
    private Award award;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Teacher  teacher;
    @Column(name = "is_All_Goals_Completed")
    private boolean isAllGoalsCompleted;
    @Column(name = "completion_percent")
    private double completionPercent;

    /**
     * Empty constructor for creating an AwardRule.
     */
    public AwardRule() {}

    /**
     * Constructs a new {@code AwardRule} instance with the specified parameters.
     *
     * @param completionPercent the required completion percentage for the rule
     * @param isAllGoalsCompleted whether all project goals must be completed
     * @param teacher the teacher associated with this rule
     * @param project the project associated with this rule
     * @param award the award associated with this rule
     */
    public AwardRule(double completionPercent, boolean isAllGoalsCompleted, Teacher teacher, Project project, Award award) {
        this.completionPercent = completionPercent;
        this.isAllGoalsCompleted = isAllGoalsCompleted;
        this.teacher = teacher;
        this.project = project;
        this.award = award;
    }

    /**
     * Returns the unique identifier of this rule.
     *
     * @return the rule ID
     */
    public int getRuleId() {
        return ruleId;
    }

    /**
     * Sets the unique identifier of this rule.
     *
     * @param ruleId the new rule ID
     */
    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * Returns the award associated with this rule.
     *
     * @return the award
     */
    public Award getAward() {
        return award;
    }

    /**
     * Sets the award associated with this rule.
     *
     * @param award the award to associate
     */
    public void setAward(Award award) {
        this.award = award;
    }

    /**
     * Returns the project to which this rule applies.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project to which this rule applies.
     *
     * @param project the project to associate
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Returns the teacher associated with this rule.
     *
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher associated with this rule.
     *
     * @param teacher the teacher to associate
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Returns whether all project goals are completed for this rule.
     *
     * @return {@code true} if all goals are completed, {@code false} otherwise
     */
    public boolean isAllGoalsCompleted() {
        return isAllGoalsCompleted;
    }

    /**
     * Sets whether all project goals are completed for this rule.
     *
     * @param allGoalsCompleted {@code true} if all goals are completed, {@code false} otherwise
     */
    public void setAllGoalsCompleted(boolean allGoalsCompleted) {
        isAllGoalsCompleted = allGoalsCompleted;
    }

    /**
     * Returns the completion percentage required for the award.
     *
     * @return the completion percentage
     */
    public double getCompletionPercent() {
        return completionPercent;
    }

    /**
     * Sets the completion percentage required for the award.
     *
     * @param completionPercent the new completion percentage
     */
    public void setCompletionPercent(double completionPercent) {
        this.completionPercent = completionPercent;
    }



    /**
     * Returns a string representation of the {@link AwardRule} object.
     *
     * The returned string includes information such as the rule ID, associated award,
     * project, teacher, completion status, and completion percentage.
     *
     * @return a string representation of the {@link AwardRule}
     */
    @Override
    public String toString() {
        return "AwardRule{" +
                "ruleId=" + ruleId +
                ", award=" + award +
                ", project=" + project +
                ", teacher=" + teacher +
                ", isAllGoalsCompleted=" + isAllGoalsCompleted +
                ", completionPercent=" + completionPercent +
                '}';
    }
}
