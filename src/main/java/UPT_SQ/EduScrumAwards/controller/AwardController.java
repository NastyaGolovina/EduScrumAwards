package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:63342")
//@CrossOrigin(origins = "http://localhost:*")
@RestController
@RequestMapping("/awards")
public class AwardController {

    private final Global global;

    @Autowired
    public AwardController(Global global) {
        this.global = global;
    }

    @GetMapping("/all")
    public List<Award> getAllAwards() {
        return global.getAwards();
    }


    @GetMapping("/projects/all")
    public List<Project> getAllProject() {
        List<Project> projects = new ArrayList<>();
        for(Course course : global.getCourses()) {
            projects.addAll(course.getProjects());
        }
        return projects;
    }


    @GetMapping("/teachers/all")
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for(User user : global.getUsers()) {
            if(UserRole.TEACHER == user.getRole()) {
                teachers.add((Teacher)user);
            }
        }
        return teachers;
    }

    @GetMapping("/students/all")
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for(User user : global.getUsers()) {
            if(UserRole.STUDENT == user.getRole()) {
                students.add((Student)user);
            }
        }
        return students;
    }



    @PostMapping("/create")
    public String createAward(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int points,
            @RequestParam String assignType,
            @RequestParam String assignMode) {

        return global.createAward(name, description, points, assignType, assignMode);
    }


    @PostMapping("/update")
    public String updateAward(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description) {

        return global.updateAward(id,name,description);
    }


    @DeleteMapping("/delete/{awardId}")
    public ResponseEntity<String> deleteAward(
            @PathVariable int awardId) {

        String result = global.deleteAward(awardId);

        if (result.startsWith("Record successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
