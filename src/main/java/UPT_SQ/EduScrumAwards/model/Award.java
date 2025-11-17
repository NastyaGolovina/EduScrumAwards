package UPT_SQ.EduScrumAwards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import javax.management.relation.Role;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private AssignMode assignMode;
//    @JsonIgnore
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
     *
     * @param awardID          the unique ID of the award
     * @param awardName        the name of the award
     * @param awardDescription the description of the award
     * @param pointsValue      the point value associated with the award
     * @param assignType       the assignment type (e.g., automatic or manual)
     * @param assignMode       the mode that defines how the award is applied
     * @param awardRules       a list of rules that determine eligibility for this award
     */
    public Award(int awardID, String awardName, String awardDescription, int pointsValue, AwardType assignType,AssignMode assignMode, ArrayList<AwardRule> awardRules) {
        this.awardID = awardID;
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.assignMode = assignMode;
        this.awardRules = awardRules;
    }


    /**
     * Constructor for creating an award without specifying an ID or rules list.
     * Initializes an empty list of award rules.
     *
     * @param awardName        the name of the award
     * @param awardDescription the description of the award
     * @param pointsValue      the point value associated with the award
     * @param assignType       the assignment type (e.g., automatic or manual)
     * @param assignMode       the mode that defines how the award is applied
     */
    public Award(String awardName, String awardDescription, int pointsValue, AwardType assignType,AssignMode assignMode) {
        this.awardName = awardName;
        this.awardDescription = awardDescription;
        this.pointsValue = pointsValue;
        this.assignType = assignType;
        this.assignMode = assignMode;
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

    /**
     * @return the assignment mode
     */
    public AssignMode getAssignMode() {
        return assignMode;
    }

    /**
     * @param assignMode the assignment mode to set
     */
    public void setAssignMode(AssignMode assignMode) {
        this.assignMode = assignMode;
    }

    /**
     * Searches for an {@link AwardRule} in the list of award rules by its rule ID.
     *
     * This method iterates through the {@code awardRules} list until it finds
     * an {@link AwardRule} whose {@code ruleId} matches the provided {@code ruledId}.
     * If a matching rule is found, it is returned; otherwise, {@code null} is returned.
     *
     * @param ruledId the unique identifier of the {@link AwardRule} to search for
     * @return the {@link AwardRule} with the specified ID, or {@code null} if not found
     */
    public AwardRule searchAwardRule(int ruledId) {
        int i = 0;
        while (i < awardRules.size() && awardRules.get(i).getRuleId() != ruledId) {
            i++;
        }
        if (i != awardRules.size()) {
            return awardRules.get(i);
        }
        return null;
    }


    /**
     * Reads all {@link AwardRule} entities from the database that are associated with the current {@code awardID}.
     *
     * This method establishes a Hibernate session using {@link DatabaseHelper}, executes an HQL query
     * to fetch all award rules where the {@code awardId} matches the current instance's {@code awardID},
     * and stores the resulting list in {@code this.awardRules}.
     *
     *
     * Note: The method opens and closes its own Hibernate session and DatabaseHelper resources.
     *
     * @throws org.hibernate.HibernateException if there is an error executing the query
     * @see DatabaseHelper
     * @see AwardRule
     */
    public void readAllAwardRuleWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<AwardRule> awardRuleList = session.createQuery("SELECT ar FROM AwardRule ar WHERE  ar.award.awardID = :awardId", AwardRule.class)
                .setParameter("awardId", this.awardID)
                .getResultList();
        this.awardRules = (ArrayList<AwardRule>)awardRuleList;
        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Creates and persists a new {@link AwardRule} for a given teacher and project.
     *
     * This method validates that the completion percentage is within the valid range (0–100),
     * verifies that the provided teacher has the {@code TEACHER} role, and ensures the teacher
     * is associated with the course of the given project. If all validations pass, a new
     * {@link AwardRule} is created, added to the in-memory list, and persisted in the database.
     *
     * @param completionPercent the percentage of project completion required to trigger the award rule (0–100)
     * @param isAllGoalsCompleted {@code true} if all project goals must be completed to trigger the award; {@code false} otherwise
     * @param teacher the {@link Teacher} creating the award rule
     * @param project the {@link Project} for which the award rule is being created
     * @return a status message:

     */
    public String createAwardRule(double completionPercent, boolean isAllGoalsCompleted, User teacher, Project project) {
            if(completionPercent>= 0 && completionPercent <= 100) {
                if(teacher.getRole() == UserRole.TEACHER) {
                    if (project.getCourse().isCourseTeacher((Teacher) teacher)) {
                        AwardRule awardRule = new AwardRule(completionPercent, isAllGoalsCompleted,
                                (Teacher) teacher, project, this);
                        awardRules.add(awardRule);
                        DatabaseHelper DatabaseHelper = new DatabaseHelper();
                        DatabaseHelper.setup();
                        Session session = DatabaseHelper.getSessionFactory().openSession();
                        session.beginTransaction();

                        session.persist(awardRule);

                        session.getTransaction().commit();
                        session.close();
                        DatabaseHelper.exit();
                        return "Success";
                    } else {
                    return "ERROR: teacher is not related to this course";
                    }
                } else  {
                    return "ERROR: user isn't teacher";
                }
            } else {
                return "ERROR: Completion Percent out of the range";
            }
    }



    /**
     * Updates an existing {@link AwardRule} with new parameters and persists the changes.
     *
     * This method searches for an existing award rule by its ID, then updates
     * its completion percentage and goal completion requirement. If the rule exists
     * and the completion percentage is valid (0–100), the updated rule is merged into
     * the database.
     *
     * @param id the unique identifier of the award rule to update
     * @param completionPercent the new completion percentage required to trigger the award (0–100)
     * @param isAllGoalsCompleted {@code true} if all project goals must be completed to trigger the award; {@code false} otherwise
     * @return a status message:
     */
    public String updateAwardRule(int id,double completionPercent, boolean isAllGoalsCompleted) {
        if(completionPercent>= 0 && completionPercent <= 100) {
            AwardRule rule = searchAwardRule(id);
            if(rule != null) {
                rule.setCompletionPercent(completionPercent);
                rule.setAllGoalsCompleted(isAllGoalsCompleted);

                DatabaseHelper DatabaseHelper = new DatabaseHelper();
                DatabaseHelper.setup();
                Session session = DatabaseHelper.getSessionFactory().openSession();
                session.beginTransaction();

                session.merge(rule);

                session.getTransaction().commit();
                session.close();
                DatabaseHelper.exit();
                return "Success";
            } else {
                return "ERROR: Rule does not exist";
            }
        } else {
            return "ERROR: Completion Percent out of the range";
        }
    }




    @Override
    public String toString() {
        return "Award{" +
                "awardID=" + awardID +
                ", awardName='" + awardName + '\'' +
                ", awardDescription='" + awardDescription + '\'' +
                ", pointsValue=" + pointsValue +
                ", assignType=" + assignType +
                ", assignMode=" + assignMode +
                '}';
    }
}


