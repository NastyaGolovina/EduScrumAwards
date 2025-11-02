package UPT_SQ.EduScrumAwards.model;
import jakarta.persistence.*;
import java.util.ArrayList;

/**
 * Represents a team entity in the EduScrum Awards system.
 * Each team has a unique identifier, a name, and a list of members associated with it.
 * The relationship between Team and TeamMember is one-to-many, meaning that
 * a single team can have multiple team members.
 *
 * Cascade and orphan removal options ensure that when a team is modified or deleted,
 * its related team members are automatically updated or removed as well.
 *
 * @author Ly Dieu Nguyen
 * @version 1.0
 * @since 2025-11-02
 */

@Entity
@Table(name = "Team")
public class Team {
    @Id  // Annotated with @Id to mark it as the primary key
    @Column(name = "Team_ID", length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamID;

    @Column(name = "Team_Name", nullable = false, length = 100)
    private String teamName;

    /**
     * A list of all members who belong to this team.
     * mappedBy = "team" means that the "team" field inside TeamMember
     * is the owning side of the relationship (it holds the foreign key).
     *
     * CascadeType.ALL ensures that when a Team is saved or deleted,
     * its members are also saved or deleted.
     *
     * orphanRemoval = true removes team members automatically if they
     * are no longer referenced by this team.
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<TeamMember> membersList;

    /**
     * Empty constructor for creating a team.
     */
    public Team() {
    }

    /**
     * Full constructor for creating a team with all attributes specified.
     *
     * @param teamID   the unique ID of the team
     * @param teamName the name of the team
     * @param membersList A list of all members who belong to this team.
     */
    public Team (int teamID, String teamName, ArrayList<TeamMember> membersList) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.membersList = membersList;
    }

    /**
     * a constructor for creating a team without specifying an ID or member list
     * Initializes an empty list of team member list.
     *
     * @param teamName the name of the team
     *
     */
    public Team (String teamName) {
        this.teamName = teamName;
        this.membersList = new ArrayList<TeamMember>();
    }


    /** @return the unique ID of the team */
    public int getTeamID() {
        return teamID;
    }

    /** @param teamID sets the unique ID of the team */
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    /** @return the name of the team */
    public String getTeamName() {
        return teamName;
    }

    /** @param teamName sets the name of the team */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    /** @return the list of team members associated with this team */
    public ArrayList<TeamMember> getMembersList() {
        return membersList;
    }

    /** @param membersList sets the list of team members for this team */
    public void setMembersList(ArrayList<TeamMember> membersList) {
        this.membersList = membersList;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID='" + teamID + '\'' +
                ", teamName='" + teamName + '\'' +
                ", membersList=" + membersList +
                '}';
    }
}
