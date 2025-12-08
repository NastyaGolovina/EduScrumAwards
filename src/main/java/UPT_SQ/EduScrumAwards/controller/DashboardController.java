package UPT_SQ.EduScrumAwards.controller;



import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final Global global;

    @Autowired
    public DashboardController(Global global) {
        this.global = global;
    }

    @GetMapping("/students/points")
    public Map<Long, Integer> getAllStudents() {
        Map<Long, Integer> students = new HashMap<>();
        for(User user : global.getUsers()) {
            if(UserRole.STUDENT == user.getRole()) {
                Student student = (Student) user;
                if (students.containsKey(student.getUserId())) {
                    students.put(user.getUserId(), students.get(student.getUserId())
                            + student.calculatePoints(global.getStudentsAwards()));
                } else {
                    students.put(user.getUserId(), student.calculatePoints(global.getStudentsAwards()));
                }
            }
        }
        Map<Long, Integer> sorted =
                students.entrySet()
                        .stream()
                        .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));

        return sorted;
    }

    @GetMapping("/teams/points")
    public Map<Integer, Integer> getAllTeams() {
        Map<Integer, Integer> teams = new HashMap<>();
        for(Team team : global.getTeams()) {
            int teamId = team.getTeamID();
                if (teams.containsKey(teamId)) {
                    teams.put(teamId, teams.get(teamId)
                            + team.earnAward(global.getStudentsAwards()));
                } else {
                    teams.put(teamId, team.earnAward(global.getStudentsAwards()));
                }
        }
        Map<Integer, Integer> sorted =
                teams.entrySet()
                        .stream()
                        .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));
        return sorted;
    }


}
