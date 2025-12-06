package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprints")
public class SprintController {

    @Autowired
    private Global global;


    // List all goals
    @GetMapping("/{sprintId}/goals")
    public Object getGoals(@PathVariable int sprintId) {
        Sprint s = global.findSprint(sprintId);
        if (s == null) return "Sprint not found!";
        s.retrieveGoals();
        return s.getGoals();
    }

    // Create goal
    @PostMapping("/{sprintId}/goals")
    public String createGoal(@PathVariable int sprintId,
                             @RequestParam String description,
                             @RequestParam int score) {
        Sprint s = global.findSprint(sprintId);
        if (s == null) return "Sprint not found!";
        return s.createGoal(description, score);
    }

    // Update goal
    @PutMapping("/{sprintId}/goals/{goalId}")
    public String updateGoal(@PathVariable int sprintId,
                             @PathVariable int goalId,
                             @RequestParam String description,
                             @RequestParam int score,
                             @RequestParam boolean completed) {
        Sprint s = global.findSprint(sprintId);
        if (s == null) return "Sprint not found!";
        s.retrieveGoals();
        return s.updateGoal(goalId, description, score, completed);
    }

    // Delete goal
    @DeleteMapping("/{sprintId}/goals/{goalId}")
    public String deleteGoal(@PathVariable int sprintId,
                             @PathVariable int goalId) {
        Sprint s = global.findSprint(sprintId);
        if (s == null) return "Sprint not found!";
        s.retrieveGoals();
        return s.deleteGoal(goalId);
    }
}
