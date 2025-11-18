package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Global;
import org.springframework.beans.factory.annotation.Autowired;
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
}
