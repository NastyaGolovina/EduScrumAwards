package UPT_SQ.EduScrumAwards.DTO;

public class ProjectsProgressDTO {

    private int projectId;
    private String projectName;
    private double progress;

    public ProjectsProgressDTO(int projectId, String projectName, double progress) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.progress = progress;
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

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
