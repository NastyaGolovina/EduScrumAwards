package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team-member")
public class TeamMemberController {

    private final Global global;

    @Autowired
    public TeamMemberController(Global global) {
        this.global = global;
    }

    /**
     * Returns all members of a given team.
     *
     * @param teamId the ID of the team
     * @return list of TeamMember; empty list if team not found or has no members
     */
    @GetMapping("/all/{teamId}")
    public List<TeamMember> getAllTeamMembers(
            @PathVariable int teamId
    ) {
        Team team = global.searchTeam(teamId);
        if (team != null) {
            // make sure members are loaded from DB
            team.readAllTeamMemberWithJplq();
            return team.getTeamMember();
        }
        return new ArrayList<TeamMember>();
    }

    /**
     * Returns a single team member from a given team.
     *
     * @param teamId   the ID of the team
     * @param memberId the ID of the team member
     * @return TeamMember if found, otherwise null
     */
    @GetMapping("/one")
    public TeamMember getTeamMember(
            @RequestParam int teamId,
            @RequestParam int memberId
    ) {
        Team team = global.searchTeam(teamId);
        if (team != null) {
            team.readAllTeamMemberWithJplq();
            return team.searchTeamMember(memberId);
        }
        return null;
    }

    /**
     * Creates a new TeamMember for a team.
     *
     * @param teamId    the ID of the team the member belongs to
     * @param studentId the ID of the Student user
     * @param role      the role of the team member
     * @return "Success" or an "ERROR: ..." message
     */
    @PostMapping("/create")
    public String createTeamMember(
            @RequestParam int teamId,
            @RequestParam long studentId,
            @RequestParam TeamMember.Role role
    ) {
        Team team = global.searchTeam(teamId);
        if (team != null) {
            User user = global.searchUser(studentId);
            if (user != null && user instanceof Student) {
                Student student = (Student) user;
                return team.createTeamMember(student, role);
            }
        }

        return "ERROR: One of the field is empty or null";
    }

    /**
     * Updates the role of an existing TeamMember.
     *
     * @param teamId   the ID of the team
     * @param memberId the ID of the team member
     * @param role     the new role to set
     * @return "Success" or an "ERROR: ..." message
     */
    @PostMapping("/update")
    public String updateTeamMember(
            @RequestParam int teamId,
            @RequestParam int memberId,
            @RequestParam TeamMember.Role role
    ) {
        Team team = global.searchTeam(teamId);
        if (team != null) {
            return team.updateTeamMember(memberId, role);
        }
        return "ERROR: One of the field is empty or null";
    }

    /**
     * Deletes a TeamMember from a team.
     *
     * @param teamId   the ID of the team
     * @param memberId the ID of the team member
     * @return HTTP 200 with success message, or 404/400 with error message
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTeamMember(
            @RequestParam int teamId,
            @RequestParam int memberId
    ) {
        Team team = global.searchTeam(teamId);
        if (team == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR: Team not found");
        }

        String result = team.deleteTeamMember(memberId);

        if (result.startsWith("Team Member successfully")) {
            return ResponseEntity.ok(result);
        } else if (result.startsWith("ERROR: TeamMember not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}