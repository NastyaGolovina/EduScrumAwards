package UPT_SQ.EduScrumAwards.model;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

/**
 * Represents a team member entity in the EduScrum Awards system.
 * Each team member has a unique identifier, a name, and a specific role within a team.
 * The role can be SCRUM_MASTER, PRODUCT_OWNER, DEVELOPER, or QA_ENGINEER.
 *
 * Each TeamMember is associated with exactly one Student (the person filling that role)
 * and belongs to one Team.
 *
 * The relationships ensure that multiple team members can reference the same student or team
 * if required by the data model.
 *
 * @author Ly Dieu Nguyen
 * @version 1.0
 * @since 2025-11-02
 */

@Entity
@Table(name = "team_members")
public class TeamMember {

    /**
     * Enum representing the different roles a team member can have within a team.
     */
    public enum Role { SCRUM_MASTER, PRODUCT_OWNER, DEVELOPER, QA_ENGINEER }

    @Id
    @Column(name = "team_member_id", length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamMemberId;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    /**
     * The student associated with this team member.
     *
     * Many TeamMember entities can reference the same Student.
     * The "student_id" foreign key column stores the reference to the Student entity.
     */
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /**
     * The team that this team member belongs to.
     *
     * Many TeamMember entities can be linked to the same Team.
     * The "team_id" foreign key column stores the reference to the Team entity.
     */
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    /**
     * Empty constructor for creating a team member.
     */
    public TeamMember() {

    }

    /**
     * Full constructor for creating a team member with all key attributes specified.
     *
     * @param teamMemberId the unique ID of the team member
     * @param name the name of the team member
     * @param role the role of the team member
     */
    public TeamMember( int teamMemberId, String name, Role role) {
        this.teamMemberId = teamMemberId;
        this.name = name;
        this.role = role;
    }

    // getters/setters

    /** @return the unique ID of the team member */
    public int getTeamMemberId() {
        return teamMemberId;
    }

    /** @return the name of the team member */
    public String getName() {
        return name;
    }

    /** @param name sets the name of the team member */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the role of the team member */
    public Role getRole() {
        return role;
    }

    /** @param role sets the role of the team member */
    public void setRole(Role role) {
        this.role = role;
    }

    /** @return the student associated with this team member */
    public Student getStudent() {
        return student;
    }

    /** @param student sets the associated student for this team member */
    public void setStudent(Student student) {
        this.student = student;
    }

    /** @return the team this team member belongs to */
    public Team getTeam() {
        return team;
    }

    /** @param team sets the team this team member belongs to */
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "teamMemberId='" + teamMemberId + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", student=" + student +
                ", team=" + team +
                '}';
    }
}