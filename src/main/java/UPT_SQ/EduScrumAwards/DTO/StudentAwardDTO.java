package UPT_SQ.EduScrumAwards.DTO;

import UPT_SQ.EduScrumAwards.model.*;


import java.util.Date;

public class StudentAwardDTO {
    private int studentAwardId;
    private String awardName;
    private String projectName;
    private Date date;
    private int points;

    public StudentAwardDTO(int studentAwardId, String awardName, String projectName, Date date, int points) {
        this.studentAwardId = studentAwardId;
        this.awardName = awardName;
        this.projectName = projectName;
        this.date = date;
        this.points = points;
    }


    public int getStudentAwardId() {
        return studentAwardId;
    }

    public void setStudentAwardId(int studentAwardId) {
        this.studentAwardId = studentAwardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
