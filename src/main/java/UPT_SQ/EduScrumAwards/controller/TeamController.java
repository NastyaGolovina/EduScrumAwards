package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.StudentAward;
import UPT_SQ.EduScrumAwards.model.Team;
import UPT_SQ.EduScrumAwards.model.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final Global global;

    @Autowired
    public TeamController(Global global) {
        this.global = global;
    }

    @GetMapping("/all")
    public List<Team> getAllTeams() {
        return global.getTeams();
    }

    @GetMapping("/{teamId}")
    public Team getTeam(
            @PathVariable int teamId
    ) {
        Team team = global.searchTeam(teamId);
        if (team != null) {
            return team;
        }
        return null;
    }

    @PostMapping("/create")
    public String createTeam(
            @RequestParam String teamName
    ) {
        return global.createTeam(teamName);
    }

    @PostMapping("/update")
    public String updateTeam(
            @RequestParam int teamId,
            @RequestParam String teamName
    ) {
        return global.updateTeam(teamId, teamName);
    }

    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<String> deleteTeam(
            @PathVariable int teamId
    ) {
        String result = global.deleteTeam(teamId);

        if (result.startsWith("Record successfully")) {
            return ResponseEntity.ok(result);
        } else if (result.startsWith("ERROR: Team not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            // any other ERROR (in StudentAward/Project, DB, etc.)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

}