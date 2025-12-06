package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Courses")
public class CourseController {
    private final Global global;

    @Autowired
    public CourseController(Global global) {
        this.global = global;
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return global.getCourses();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@RequestParam("name") String courseName) {
        String result = global.createCourse(courseName);
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestParam int id, @RequestParam("name") String courseName) {
        String result = global.updateCourse(id, courseName);
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam int id) {
        String result = global.deleteCourse(id);
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(result);
    }


    // Project CRUD

    // List all projects for a course
    @GetMapping("/{courseId}/projects")
    public Object getProjects(@PathVariable int courseId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        c.retrieveProjects();
        return c.getProjects();
    }

    // Get single project
    @GetMapping("/{courseId}/projects/{projectId}")
    public Object getProject(@PathVariable int courseId, @PathVariable int projectId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        Project p = c.searchProject(projectId);
        if (p == null) return "Project not found!";
        return p;
    }

    // Create project
    @PostMapping("/{courseId}/projects/create")
    public String createProject(@PathVariable int courseId,
                                @RequestParam String name,
                                @RequestParam int teamId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        return c.createProject(name, null, c);
    }

    // Update project
    @PutMapping("/{courseId}/projects/{projectId}/update")
    public String updateProject(@PathVariable int courseId,
                                @PathVariable int projectId,
                                @RequestParam String name) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        return c.updateProject(projectId, name);
    }

    // Delete project
    @DeleteMapping("/{courseId}/projects/{projectId}/delete")
    public String deleteProject(@PathVariable int courseId,
                                @PathVariable int projectId) {
        Course c = global.searchCourse(courseId);
        if (c == null) return "Course not found!";
        return c.deleteProject(projectId);
    }
}
