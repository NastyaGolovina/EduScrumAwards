package UPT_SQ.EduScrumAwards.model;

public class Goal {
    private int goalId;
    private String description;
    private int score;
    private boolean isCompleted;

    public Goal(int goalId, String description, int score, boolean isCompleted) {
        this.goalId = goalId;
        this.description = description;
        this.score = score;
        this.isCompleted = isCompleted;
    }

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
        isCompleted = completed;
    }

}
