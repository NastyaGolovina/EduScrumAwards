package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/CourseTeachers")
//public class CourseTeacherController {
//    private final Course course;
//
//    @Autowired
//    public CourseTeacherController(Course course) {
//        this.course = course;
//    }
//
//    @PostMapping("/create")
//    public String createCourse(
//            @RequestParam Teacher teacher,
//            @RequestParam boolean isResponsible) {
//
//        if (teacher != null) {
//            return course.createCourseTeacher(teacher, isResponsible);
//        }
//        return "ERROR: Teacher is null";
//    }
//
//    @PostMapping("/update")
//    public String updateCourse(
//            @RequestParam int id,
//            @RequestParam boolean isResponsible) {
//
//        return course.updateCourseTeacher(id, isResponsible);
//    }
//}