package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an award entity in the EduScrum Awards system.
 * Each award has a name, description, point value, and type,
 * and may be associated with one or more award rules.
 *
 *
 * @author Anastasiia Holovina
 * @version 1.0
 * @since 2025-10-31
 */
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

    /**
     * Empty constructor for creating an award.
     */
    public Award() {
    }

    /**
     * Full constructor for creating an award with all attributes specified.
     *
     * @param awardID the unique ID of the award
     * @param awardName the name of the award
     * @param awardDescription the description of the award
     * @param pointsValue the point value of the award
     * @param assignType the assignment type of the award
     * @param awardRules a list of rules associated with this award
     */
    public Award(int awardID, String awardName, String awardDescription, int pointsValue, AwardType assignType, ArrayList<AwardRule> awardRules) {
        this.awardID = awardID;
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.awardRules = awardRules;
    }


    /**
     * Constructor for creating an award without specifying an ID or rules list.
     * Initializes an empty list of award rules.
     *
     * @param awardName the name of the award
     * @param awardDescription the description of the award
     * @param pointsValue the point value of the award
     * @param assignType the assignment type of the award
     */
    public Award(String awardName, String awardDescription, int pointsValue, AwardType assignType) {
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.awardRules = new ArrayList<AwardRule>();
    }

    /** @return the unique ID of the award */
    public int getAwardID() {
        return awardID;
    }

    /** @param awardID sets the unique ID of the award */
    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    /** @return the name of the award */
    public String getAwardName() {
        return awardName;
    }

    /** @param awardName sets the name of the award */
    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    /** @return the description of the award */
    public String getAwardDescription() {
        return awardDescription;
    }

    /** @param awardDescription sets the description of the award */
    public void setAwardDescription(String awardDescription) {
        this.awardDescription = awardDescription;
    }

    /** @return the number of points associated with this award */
    public int getPointsValue() {
        return pointsValue;
    }

    /** @param pointsValue sets the point value of the award */
    public void setPointsValue(int pointsValue) {
        this.pointsValue = pointsValue;
    }

    /** @return the assignment type of the award */
    public AwardType getAssignType() {
        return assignType;
    }

    /** @param assignType sets the assignment type of the award */
    public void setAssignType(AwardType assignType) {
        this.assignType = assignType;
    }

    /** @return the list of award rules (not persisted in the database) */
    public ArrayList<AwardRule> getAwardRules() {
        return awardRules;
    }

    /** @param awardRules sets the list of award rules */
    public void setAwardRules(ArrayList<AwardRule> awardRules) {
        this.awardRules = awardRules;
    }

    @Override
    public String toString() {
        return "Award{" +
                "awardID=" + awardID +
                ", awardName='" + awardName + '\'' +
                ", awardDescription='" + awardDescription + '\'' +
                ", pointsValue=" + pointsValue +
                ", assignType=" + assignType +
                '}';
    }
}


