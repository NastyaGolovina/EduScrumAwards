package UPT_SQ.EduScrumAwards.model;

import java.util.ArrayList;

public class Project {

    private int projectId;
    private String projectName;
    private Team team;
    private Course course;
    private ArrayList<Sprint>sprints;

    public Project(int projectId, String projectName, Team team, Course course, ArrayList<Sprint> sprints) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.team = team;
        this.course = course;
        this.sprints = sprints;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(ArrayList<Sprint> sprints) {
        this.sprints = sprints;
    }
}
