package UPT_SQ.EduScrumAwards.model;

import java.util.ArrayList;

public class Award {
    private int awardID;
    private String awardName;
    private String awardDescription;
    private int pointsValue;
    //Enum automatic, manual !!!!!
    private String assignType;
    private ArrayList<AwardRule> awardRules;

    public Award(int awardID, String awardName, String awardDescription, int pointsValue, String assignType, ArrayList<AwardRule> awardRules) {
        this.awardID = awardID;
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.awardRules = awardRules;
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardDescription() {
        return awardDescription;
    }

    public void setAwardDescription(String awardDescription) {
        this.awardDescription = awardDescription;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(int pointsValue) {
        this.pointsValue = pointsValue;
    }

    public String getAssignType() {
        return assignType;
    }

    public void setAssignType(String assignType) {
        this.assignType = assignType;
    }

    public ArrayList<AwardRule> getAwardRules() {
        return awardRules;
    }

    public void setAwardRules(ArrayList<AwardRule> awardRules) {
        this.awardRules = awardRules;
    }
}


