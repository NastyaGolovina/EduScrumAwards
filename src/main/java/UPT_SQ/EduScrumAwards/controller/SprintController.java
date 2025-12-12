package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Project;
import UPT_SQ.EduScrumAwards.model.Sprint;
import UPT_SQ.EduScrumAwards.model.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/projects/{projectId}/sprints")
public class SprintController {

    private final Global global;

    @Autowired
    public SprintController(Global global) {

        this.global = global;
    }

    // list sprints for project
    @GetMapping
    public Object listSprints(@PathVariable int courseId, @PathVariable int projectId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.findProjectById(projectId);
        if (p == null) return "Project not found!";
        return p.getSprints();
    }

    // get sprint
    @GetMapping("/{sprintId}")
    public Object getSprint(@PathVariable int courseId, @PathVariable int projectId, @PathVariable int sprintId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.findProjectById(projectId);
        if (p == null) return "Project not found!";
        Sprint s = p.findSprintById(sprintId);
        if (s == null) return "Sprint not found!";
        return s;
    }


    // CREATE
    @PostMapping("/create")
    public String createSprint(
            @PathVariable int courseId,
            @PathVariable int projectId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.findProjectById(projectId);
        if (p == null) return "Project not found!";
        return p.createSprint(startDate, endDate);
    }

    // UPDATE
    @PutMapping("/{sprintId}/update")
    public String updateSprint(
            @PathVariable int courseId,
            @PathVariable int projectId,
            @PathVariable int sprintId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.findProjectById(projectId);
        if (p == null) return "Project not found!";
        return p.updateSprint(sprintId, startDate, endDate);
    }

    // delete sprint
    @DeleteMapping("/{sprintId}/delete")
    public String deleteSprint(@PathVariable int courseId,
                               @PathVariable int projectId,
                               @PathVariable int sprintId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.findProjectById(projectId);
        if (p == null) return "Project not found!";
        return p.deleteSprint(sprintId);
    }
}
