package UPT_SQ.EduScrumAwards.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "Team_ID")
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
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMembers = new ArrayList<>();


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
     * @param teamMembers A list of all members who belong to this team.
     */
    public Team (int teamID, String teamName, List<TeamMember> teamMembers) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamMembers = teamMembers;
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
        this.teamMembers = new ArrayList<TeamMember>();
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
    @JsonIgnore
    public List<TeamMember> getTeamMember() {
        return teamMembers;
    }

    /** @param teamMembers sets the list of team members for this team */
    public void setTeamMember(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID='" + teamID + '\'' +
                ", teamName='" + teamName + '\'' +
                ", TeamMembers=" + teamMembers +
                '}';
    }

    /**
     * Creates a new TeamMember and saves it to the database.
    * @param student The {@link Student} entity associated with this team member. Must not be {@code null}.
     * @param role The {@link TeamMember.Role} assigned to the team member. Must not be {@code null}.
     * @return "Success" if the team member was successfully created and saved;
    *  otherwise, an error message describing the validation or database issue.
    */
    public String createTeamMember(Student student, TeamMember.Role role) {
        // --- Validation checks ---
        if (student == null) return "ERROR: Student cannot be null!";
        if (role == null) return "ERROR: Role is required!";
        if (student.getName() == null || student.getName().isEmpty()) return "ERROR: Student name is empty!";
        if (student.getName().length() > 100) return "ERROR: Name is too long!";

        // --- Database setup ---
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        // Ensure the team exists in DB
        Team managedTeam = session.get(Team.class, this.teamID);
        if (managedTeam == null) {
            session.getTransaction().rollback();
            session.close();
            DatabaseHelper.exit();
            return "ERROR: Team not found!";
        }

        // Ensure the student exist in DB
        Student managedStudent = session.get(Student.class, student.getUserId());
        if (managedStudent == null) {
            session.getTransaction().rollback();
            session.close();
            DatabaseHelper.exit();
            return "ERROR: Student not found in database!";
        }

        // Create and persist the new TeamMember
        TeamMember tm = new TeamMember(student.getName(), role, managedStudent, managedTeam);
        managedTeam.getTeamMember().add(tm); // maintain bidirectional consistency

        session.persist(tm);         // insert TeamMember
        session.merge(managedTeam);  // update owning side

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();

        // --- Update in-memory list (for immediate access) ---
        this.teamMembers.add(tm);

        return "Success";
    }
    /**
     * Loads all {@link TeamMember} entities associated with the current {@link Team}
     * from the database and stores them in {@code this.membersList}.
     *
     * <p>This method uses a JPQL query to fetch all team members whose foreign key
     * {@code team.teamID} matches the current team's ID.</p>
     *
     * <p>Note: This method opens and closes its own Hibernate session
     * and {@link DatabaseHelper} resources.</p>
     *
     * @throws org.hibernate.HibernateException if there is an error executing the query
     * @see DatabaseHelper
     * @see TeamMember
     */
    public void readAllTeamMemberWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<TeamMember> teamMemberList = session.createQuery(
                        "SELECT m FROM TeamMember m WHERE m.team.teamID = :tid", TeamMember.class)
                .setParameter("tid", this.teamID)
                .getResultList();

        this.teamMembers = new ArrayList<>(teamMemberList);

        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for a {@link TeamMember} within this team's local list
     * based on the provided member ID.
     *
     * <p>This method searches the in-memory {@code membersList} that belongs
     * to the current team. If the list is not yet loaded, call
     * {@link #readAllTeamMemberWithJplq()} before searching.</p>
     *
     * @param teamMemberId The unique ID of the team member to find.
     * @return The matching {@link TeamMember} if found, otherwise {@code null}.
     */
    public TeamMember searchTeamMember(int teamMemberId) {
        int i = 0;
        while (i < teamMembers.size() && teamMembers.get(i).getTeamMemberId() != teamMemberId)
            i++;
        if (i != teamMembers.size()) {
            return teamMembers.get(i);
        }
        return null;
    }

    /**
     * Updates the {@link TeamMember.Role} of an existing {@link TeamMember} that belongs to this {@link Team}.
     *
     * <p><b>Notes:</b>
     * <ul>
     *   <li>Only the role is updated. The primary key ({@code teamMemberId}), student linkage, and name remain unchanged.</li>
     *   <li>Fails if the member does not exist or does not belong to this team.</li>
     * </ul>
     *
     * @param targetMemberId the ID of the {@link TeamMember} to update (used only for lookup; never modified)
     * @param newRole        the new role to set (must not be {@code null})
     * @return "Success" if the update was applied; otherwise an "ERROR: ..." message
     */
    public String updateTeamMember(int targetMemberId, TeamMember.Role newRole) {
        // Validate inputs against in-memory cache first
        TeamMember memberToUpdate = searchTeamMember(targetMemberId);
        if (memberToUpdate == null)
            return "ERROR: TeamMember with this ID does not exist";
        if (newRole == null)
            return "ERROR: Role is required!";

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            // Load managed entity from DB
            TeamMember managed = session.get(TeamMember.class, targetMemberId);
            if (managed == null) {
                session.getTransaction().rollback();
                return "ERROR: TeamMember not found in database!";
            }

            // Ensure this member actually belongs to THIS team
            if (managed.getTeam() == null || managed.getTeam().getTeamID() != this.teamID) {
                session.getTransaction().rollback();
                return "ERROR: TeamMember does not belong to this team!";
            }

            // Update only the role
            managed.setRole(newRole);

            // Persist change
            session.merge(managed);
            session.getTransaction().commit();

            // Sync in-memory representation
            memberToUpdate.setRole(newRole);

            return "Success";
        } catch (Exception e) {
            session.getTransaction().rollback();
            return "ERROR: " + e.getMessage();
        } finally {
            session.close();
            DatabaseHelper.exit();
        }
    }

    /**
     * Retrieves all {@link StudentAward} entities that are associated with this {@link Team}
     * and returns them as a local list.
     *
     * <p>Uses JPQL to select awards where {@code sa.team.teamID = this.teamID}.</p>
     *
     * @return an {@link ArrayList} of {@link StudentAward} linked to this team (may be empty, never null)
     * @throws org.hibernate.HibernateException on query errors
     */
    public ArrayList<StudentAward> studentsAwards() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<StudentAward> list = session.createQuery(
                        "SELECT sa FROM StudentAward sa WHERE sa.team.teamID = :teamId",
                        StudentAward.class)
                .setParameter("teamId", this.teamID)
                .getResultList();

        session.close();
        DatabaseHelper.exit();
        return new ArrayList<>(list);
    }
    /**
     * Deletes a single team member from the database.
     *
     * The method performs the following steps:
     * 1. Checks if the team member exists in this team.
     * 2. Removes the team member from the database using a Hibernate session.
     * 3. Updates the in-memory teamMembers list.
     *
     *
     * @param memberId the ID of the team member to delete
     * @return a message indicating success or the reason for failure
     */
    public String deleteTeamMember(int memberId) {

        TeamMember teamMember = searchTeamMember(memberId);
        if (teamMember != null) {

            DatabaseHelper databaseHelper = new DatabaseHelper();
            Session session = null;

            try {
                databaseHelper.setup();
                session = databaseHelper.getSessionFactory().openSession();
                session.beginTransaction();
                session.remove(teamMember); //delete in db
                session.getTransaction().commit();

                // delete in local list
                teamMembers.remove(teamMember);

                return "Team Member successfully deleted.";

            } catch (Exception e) {

                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }

                return "ERROR: Failed to delete the team member. Reason: " + e.getMessage();

            } finally {
                if (session != null) {
                    session.close();
                }
                databaseHelper.exit();
            }

        } else {
            return "ERROR: TeamMember not found";
        }
    }

    /**
     * Calculates the total points earned by this {@link Team}
     * by summing the points of all {@link StudentAward} records
     * that belong to the team’s members.
     *
     * <p>This method retrieves all {@code StudentAward} objects linked
     * to this team (via {@link #studentsAwards()}) and sums their
     * {@code points} values. Returns 0 if there are no awards.</p>
     *
     * @return total points earned by all team members
     */
    public int earnAward() {

        // get all student awards linked to this team
        ArrayList<StudentAward> awards = this.studentsAwards();

        int total = 0;
        for (StudentAward sa : awards) {
            if (sa != null && sa.getTeam() != null
                    && sa.getTeam().getTeamID() == this.teamID) {
                total += sa.getPoints();
            }
        }

        return total;
    }



    /**
     * Calculates the total award points earned by this team.
     *
     * <p>This method iterates over the provided list of {@link StudentAward}
     * objects and sums the points of all awards that are associated with
     * the current team (based on matching team IDs).</p>
     *
     * @param awards the list of student awards to evaluate; may contain null entries
     * @return the total number of points earned by this team
     */
    public int earnAward(ArrayList<StudentAward> awards) {

        // get all student awards linked to this team
//        ArrayList<StudentAward> awards = this.studentsAwards();

        int total = 0;
        for (StudentAward sa : awards) {
            if (sa != null && sa.getTeam() != null
                    && sa.getTeam().getTeamID() == this.teamID) {
                total += sa.getPoints();
            }
        }

        return total;
    }

    /**
     * Checks if a student with the given ID is a member of the team.
     *
     * @param studentMemberId The unique ID of the student to check.
     * @return The TeamMember object if the student is found in the team, otherwise null.
     */
    public TeamMember isTeamMember(long studentMemberId) {
        int i = 0;
        while (i < teamMembers.size() && teamMembers.get(i).getStudent().getUserId() != studentMemberId)
            i++;
        if (i != teamMembers.size()) {
            return teamMembers.get(i);
        }
        return null;
    }



}
