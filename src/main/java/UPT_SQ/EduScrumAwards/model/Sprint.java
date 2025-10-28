package UPT_SQ.EduScrumAwards.model;

import java.util.ArrayList;
import java.util.Date;

public class Sprint {
    private int sprintId;
    private Date startDate;
    private Date endDate;
    private ArrayList<Goal> goals;

    public Sprint(int sprintId, Date startDate, Date endDate, ArrayList<Goal> goals) {
        this.sprintId = sprintId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goals = goals;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;

    }
}
