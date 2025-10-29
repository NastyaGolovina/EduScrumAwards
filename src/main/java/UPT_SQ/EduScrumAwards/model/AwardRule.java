package UPT_SQ.EduScrumAwards.model;


import jakarta.persistence.*;

//@Entity
//@Table(name = "Award_Rules")
public class AwardRule {
//    @Id
//    @Column(name = "rule_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ruleId;
//    @ManyToOne
//    @JoinColumn(name = "award_id")
    private Award award;
//    @ManyToOne
//    @JoinColumn(name = "project_id")
    private Project project;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Teacher  teacher;
//    @Column(name = "is_All_Goals_Completed")
    private boolean isAllGoalsCompleted;
//    @Column(name = "completion_percent")
    private double completionPercent;

    public AwardRule(double completionPercent, boolean isAllGoalsCompleted, Teacher teacher, Project project, Award award, int ruleId) {
        this.completionPercent = completionPercent;
        this.isAllGoalsCompleted = isAllGoalsCompleted;
        this.teacher = teacher;
        this.project = project;
        this.award = award;
        this.ruleId = ruleId;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isAllGoalsCompleted() {
        return isAllGoalsCompleted;
    }

    public void setAllGoalsCompleted(boolean allGoalsCompleted) {
        isAllGoalsCompleted = allGoalsCompleted;
    }

    public double getCompletionPercent() {
        return completionPercent;
    }

    public void setCompletionPercent(double completionPercent) {
        this.completionPercent = completionPercent;
    }
}
