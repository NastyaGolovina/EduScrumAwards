package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Project;
import UPT_SQ.EduScrumAwards.model.Sprint;
import UPT_SQ.EduScrumAwards.model.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/{courseId}/projects/{projectId}/sprints/{sprintId}/goals")
public class GoalController {

    private final Global global;

    @Autowired
    public GoalController(Global global) {
        this.global = global;
    }

    private Sprint getSprint(int courseId, int projectId, int sprintId) {
        Course course = global.searchCourse(courseId);
        if (course == null) return null;
        Project project = course.findProjectById(projectId);
        if (project == null) return null;
        return project.findSprintById(sprintId);
    }

    @GetMapping
    public Object listGoals(@PathVariable int courseId,
                            @PathVariable int projectId,
                            @PathVariable int sprintId) {
        Sprint sprint = getSprint(courseId, projectId, sprintId);
        return sprint != null ? sprint.getGoals() : "Sprint not found!";
    }

    @GetMapping("/{goalId}")
    public Object getGoal(@PathVariable int courseId,
                          @PathVariable int projectId,
                          @PathVariable int sprintId,
                          @PathVariable int goalId) {
        Sprint sprint = getSprint(courseId, projectId, sprintId);
        if (sprint == null) return "Sprint not found!";
        Goal goal = sprint.findGoalById(goalId);
        return goal != null ? goal : "Goal not found!";
    }

    @PostMapping("/create")
    public String createGoal(@PathVariable int courseId,
                             @PathVariable int projectId,
                             @PathVariable int sprintId,
                             @RequestParam String description,
                             @RequestParam int score) {
        Sprint sprint = getSprint(courseId, projectId, sprintId);


        //Assign Automatic Award
        if (sprint == null) return "Sprint not found!";

        String result =  sprint.createGoal(description, score);

        if (result.contains("Goal added successfully!")) {
            return sprint.assignAutomaticAward(global.getAwards(),global.getStudentsAwards());
        } else {
            return result;
        }
        //


//        return sprint != null ? sprint.createGoal(description, score) : "Sprint not found!";
    }

    @PutMapping("/{goalId}/update")
    public String updateGoal(@PathVariable int courseId,
                             @PathVariable int projectId,
                             @PathVariable int sprintId,
                             @PathVariable int goalId,
                             @RequestParam String description,
                             @RequestParam int score,
                             @RequestParam boolean completed) {
        Sprint sprint = getSprint(courseId, projectId, sprintId);

        //Assign Automatic Award
        if (sprint == null) return "Sprint not found!";

        String result =  sprint.updateGoal(goalId, description, score, completed);

        if (result.contains("Goal updated successfully!")) {
            return sprint.assignAutomaticAward(global.getAwards(),global.getStudentsAwards());
        } else {
            return result;
        }
        //

//
//        if (sprint == null) return "Sprint not found!";
//        return sprint.updateGoal(goalId, description, score, completed);
    }

    @DeleteMapping("/{goalId}/delete")
    public String deleteGoal(@PathVariable int courseId,
                             @PathVariable int projectId,
                             @PathVariable int sprintId,
                             @PathVariable int goalId) {
        Sprint sprint = getSprint(courseId, projectId, sprintId);

        //Assign Automatic Award
        if (sprint == null) return "Sprint not found!";

        String result =  sprint.deleteGoal(goalId);

        if (result.contains("Goal deleted successfully!")) {
            return sprint.assignAutomaticAward(global.getAwards(),global.getStudentsAwards());
        } else {
            return result;
        }
        //
//
//        if (sprint == null) return "Sprint not found!";
//        return sprint.deleteGoal(goalId);
    }
}
