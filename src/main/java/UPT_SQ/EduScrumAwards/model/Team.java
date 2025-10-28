package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;

import java.util.ArrayList;
@Entity
@Table(name = "Team")
public class Team {
    @Id  // Annotated with @Id to mark it as the primary key
    @Column(name = "Team_ID", length = 36)
    private String teamID;

    @Column(name = "Team_Name", nullable = false, length = 100)
    private String teamName;

    /**
     * A list of all members who belong to this team.
     *
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


    public Team (String teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
        membersList = new ArrayList<>();
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<TeamMember> getMembersList() {
        return membersList;
    }

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
