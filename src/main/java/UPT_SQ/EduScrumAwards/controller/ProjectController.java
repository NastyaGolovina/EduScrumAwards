package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Project;
import UPT_SQ.EduScrumAwards.model.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

    @RestController
    @RequestMapping("/projects")
    public class ProjectController {

        @Autowired
        private Global global;

        // List all sprints for a project
        @GetMapping("/{projectId}/sprints")
        public Object getSprints(@PathVariable int projectId) {
            Project p = global.findProject(projectId);
            if (p == null) return "Project not found!";
            p.retrieveSprints();
            return p.getSprints();
        }

        // Create a sprint
        @PostMapping("/{projectId}/sprints")
        public String createSprint(@PathVariable int projectId,
                                   @RequestParam Date startDate,
                                   @RequestParam Date endDate) {
            Project p = global.findProject(projectId);
            if (p == null) return "Project not found!";
            return p.createSprint(startDate, endDate);
        }

        // Update a sprint
        @PutMapping("/{projectId}/sprints/{sprintId}")
        public String updateSprint(@PathVariable int projectId,
                                   @PathVariable int sprintId,
                                   @RequestParam Date startDate,
                                   @RequestParam Date endDate) {
            Project p = global.findProject(projectId);
            if (p == null) return "Project not found!";
            return p.updateSprint(sprintId, startDate, endDate);
        }

        // Delete a sprint
        @DeleteMapping("/{projectId}/sprints/{sprintId}")
        public String deleteSprint(@PathVariable int projectId,
                                   @PathVariable int sprintId) {
            Project p = global.findProject(projectId);
            if (p == null) return "Project not found!";
            return p.deleteSprint(sprintId);
        }
    }
