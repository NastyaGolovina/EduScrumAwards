package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Project;
import UPT_SQ.EduScrumAwards.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/projects")
public class ProjectController {

    private final Global global;

    @Autowired
    public ProjectController(Global global) {

        this.global = global;
    }

    // list projects for a course
    @GetMapping
    public Object listProjects(@PathVariable int courseId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        // Do NOT reload from DB (singleton global already loaded)
        return c.getProjects();
    }

    // get a single project
    @GetMapping("/{projectId}")
    public Object getProject(@PathVariable int courseId, @PathVariable int projectId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.findProjectById(projectId); // uses Course method
        if (p == null) return "Project not found!";
        return p;
    }

    // create project (teamId required)
    @PostMapping("/create")
    public String createProject(@PathVariable int courseId,
                                @RequestParam String projectName,
                                @RequestParam int teamId) {

        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";

        // find team in Global (method name may differ: adapt if needed)
        Team team = global.searchTeam(teamId); // replace with your actual method if it's findTeam(...)
        if (team == null) return "Team not found!";

        return c.createProject(projectName, team, c);
    }

    // update project name
    @PutMapping("/{projectId}/update")
    public String updateProject(@PathVariable int courseId,
                                @PathVariable int projectId,
                                @RequestParam String projectName) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        return c.updateProject(projectId, projectName);
    }

    // delete project
    @DeleteMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable int courseId,
                                @PathVariable int projectId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        return c.deleteProject(projectId);
    }
}
