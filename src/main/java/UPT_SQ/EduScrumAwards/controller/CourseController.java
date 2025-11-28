package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Courses")
public class CourseController {
    private final Global global;

    @Autowired
    public CourseController(Global global) {
        this.global = global;
    }

    @PostMapping("/create")
    public String createCourse(@RequestParam String courseName) {
        return global.createCourse(courseName);
    }

    @PostMapping("/update")
    public String updateCourse(@RequestParam int id, @RequestParam String courseName) {
        return global.updateCourse(id, courseName);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam int id) {
        String result = global.deleteCourse(id);

        if (result.startsWith("Record successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
