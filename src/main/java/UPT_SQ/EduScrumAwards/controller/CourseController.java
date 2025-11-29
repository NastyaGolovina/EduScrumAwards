package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Global;
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
}
