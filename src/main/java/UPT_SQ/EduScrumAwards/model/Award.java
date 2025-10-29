package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Awards")
public class Award {


    @Id
    @Column(name = "award_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int awardID;
    @Column(name = "award_name",length = 100, nullable = false)
    private String awardName;
    @Column(name = "award_description",length = 500, nullable = false)
    private String awardDescription;
    @Column(name = "points_value")
    private int pointsValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AwardType assignType;
    @Transient
    private ArrayList<AwardRule> awardRules;

    public Award(int awardID, String awardName, String awardDescription, int pointsValue, AwardType assignType, ArrayList<AwardRule> awardRules) {
        this.awardID = awardID;
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.awardRules = awardRules;
    }

    public Award(String awardName, String awardDescription, int pointsValue, AwardType assignType) {
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.awardRules = new ArrayList<AwardRule>();
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

    public AwardType getAssignType() {
        return assignType;
    }

    public void setAssignType(AwardType assignType) {
        this.assignType = assignType;
    }

    public ArrayList<AwardRule> getAwardRules() {
        return awardRules;
    }

    public void setAwardRules(ArrayList<AwardRule> awardRules) {
        this.awardRules = awardRules;
    }
}


