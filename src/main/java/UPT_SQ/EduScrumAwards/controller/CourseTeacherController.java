package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Course;
import UPT_SQ.EduScrumAwards.model.CourseTeacher;
import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Teacher;
import UPT_SQ.EduScrumAwards.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CourseTeachers")
public class CourseTeacherController {
    private final Global global;

    @Autowired
    public CourseTeacherController(Global global) {
        this.global = global;
    }

    @GetMapping("/all")
    public List<CourseTeacher> getAllCoursesTeachers(
        @RequestParam int courseId
    ) {
        Course course = global.searchCourse(courseId);
        return course.getCourseTeachers();
    }


    // Create a CourseTeacher for a given course and teacher IDs
    @PostMapping("/create")
    public ResponseEntity<String> createCourseTeacher(
            @RequestParam int courseId,
            @RequestParam long teacherId,
            @RequestParam boolean isResponsible) {

        Course course = global.searchCourse(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Course with id " + courseId + " does not exist");
        }
        User user = global.searchUser(teacherId);
        if (!(user instanceof Teacher)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Teacher with id " + teacherId + " does not exist");
        }
        Teacher teacher = (Teacher) user;

        String result = course.createCourseTeacher(teacher, isResponsible);
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(result);
    }

    // Update isResponsible of a CourseTeacher by id within a course
    @PutMapping("/update")
    public ResponseEntity<String> updateCourseTeacher(
            @RequestParam int courseId,
            @RequestParam int id,
            @RequestParam boolean isResponsible) {
        Course course = global.searchCourse(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Course with id " + courseId + " does not exist");
        }
        String result = course.updateCourseTeacher(id, isResponsible);
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(result);
    }

    // Delete a CourseTeacher by id within a course
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourseTeacher(
            @RequestParam int courseId,
            @RequestParam int id) {
        Course course = global.searchCourse(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Course with id " + courseId + " does not exist");
        }
        String result = course.deleteCourseTeacher(id);
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(result);
    }
}