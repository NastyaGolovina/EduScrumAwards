package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.DTO.*;
import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller responsible for handling operations related to awards,
 * including listing, creating, updating, and deleting awards.
 * It also exposes utility endpoints for retrieving teachers, students, and projects.
 */
@RestController
@RequestMapping("/awards")
public class AwardController {

    private final Global global;

    /**
     * Constructor that injects the Global service.
     *
     * @param global the global in-memory datastore containing awards, users, and courses
     */
    @Autowired
    public AwardController(Global global) {
        this.global = global;
    }

    /**
     * Retrieves all Award objects.
     *
     * @return a list of all awards
     */
    @GetMapping("/all")
    public List<Award> getAllAwards() {
        return global.getAwards();
    }

    /**
     * Retrieves all awards and maps them to Data Transfer Objects (DTOs).
     *
     * @return a list of AwardDTO objects containing limited award information
     */
    @GetMapping("/all/DTO")
    public List<AwardDTO> getAllDTOAwards() {
        List<AwardDTO> awardDTOS = new ArrayList<>();
        for (Award award : global.getAwards()) {
            awardDTOS.add(new AwardDTO(award.getAwardID(),award.getAwardName(),award.getPointsValue()));
        }
        return awardDTOS;
    }


//    @GetMapping("/projects/all")
//    public List<Project> getAllProject() {
//        List<Project> projects = new ArrayList<>();
//        for(Course course : global.getCourses()) {
//            projects.addAll(course.getProjects());
//        }
//        return projects;
//    }

    /**
     * Retrieves all projects across all courses and maps them to ProjectDTO objects.
     *
     * @return a list of ProjectDTO instances containing basic project info
     */
    @GetMapping("/projects/all")
    public List<ProjectDTO> getAllProject() {
        List<ProjectDTO> projects = new ArrayList<>();
        for(Course course : global.getCourses()) {
            for(Project project : course.getProjects()) {
                projects.add(new ProjectDTO(project.getProjectId(), project.getProjectName()));
            }
        }
        return projects;
    }



    /**
     * Retrieves all teachers from the global user list.
     *
     * @return a list of SimpleUserDTO objects representing teachers
     */
    @GetMapping("/teachers/all")
    public List<SimpleUserDTO> getAllTeachers() {
        List<SimpleUserDTO> teachers = new ArrayList<>();
        for(User user : global.getUsers()) {
            if(UserRole.TEACHER == user.getRole()) {
                teachers.add(new SimpleUserDTO(user.getUserId(), user.getName()));
            }
        }
        return teachers;
    }

    /**
     * Retrieves all students from the global user list.
     *
     * @return a list of SimpleUserDTO objects representing students
     */
    @GetMapping("/students/all")
    public List<SimpleUserDTO> getAllStudents() {
        List<SimpleUserDTO> students = new ArrayList<>();
        for(User user : global.getUsers()) {
            if(UserRole.STUDENT == user.getRole()) {
                students.add(new SimpleUserDTO(user.getUserId(), user.getName()));
            }
        }
        return students;
    }



//    @GetMapping("/teachers/all")
//    public List<Teacher> getAllTeachers() {
//        List<Teacher> teachers = new ArrayList<>();
//        for(User user : global.getUsers()) {
//            if(UserRole.TEACHER == user.getRole()) {
//                teachers.add((Teacher)user);
//            }
//        }
//        return teachers;
//    }
//
//    @GetMapping("/students/all")
//    public List<Student> getAllStudents() {
//        List<Student> students = new ArrayList<>();
//        for(User user : global.getUsers()) {
//            if(UserRole.STUDENT == user.getRole()) {
//                students.add((Student)user);
//            }
//        }
//        return students;
//    }



    /**
     * Creates a new award.
     *
     * @param name        the name of the award
     * @param description a short description of the award
     * @param points      the points value associated with the award
     * @param assignType  how the award is assigned (e.g., TEACHER, SYSTEM)
     * @param assignMode  the assignment mode (e.g., SINGLE, MULTIPLE)
     * @return a status message indicating success or failure
     */
    @PostMapping("/create")
    public String createAward(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int points,
            @RequestParam String assignType,
            @RequestParam String assignMode) {

        return global.createAward(name, description, points, assignType, assignMode);
    }


    /**
     * Updates an existing award.
     *
     * @param id          the ID of the award to update
     * @param name        the new name of the award
     * @param description the new description
     * @return a message indicating the result of the operation
     */
    @PostMapping("/update")
    public String updateAward(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description) {

        return global.updateAward(id,name,description);
    }


    /**
     * Deletes an award by its ID.
     *
     * @param awardId the ID of the award to delete
     * @return a ResponseEntity containing the result message and HTTP status code
     */
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
