package UPT_SQ.EduScrumAwards.model;

public class AwardRule {
    private int ruleId;
    private Award award;
    private Project project;
    private Teacher  teacher;
    private boolean isAllGoalsCompleted;
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
