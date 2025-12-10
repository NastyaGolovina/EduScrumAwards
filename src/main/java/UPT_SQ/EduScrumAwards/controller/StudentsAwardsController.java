package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.StudentAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller responsible for managing awards assigned to students or teams.
 * Provides endpoints to retrieve, assign, and delete student and team awards.
 */
@RestController
@RequestMapping("/students-awards")
public class StudentsAwardsController {
    private final Global global;

    /**
     * Constructor for dependency injection.
     *
     * @param global the global in-memory datastore containing awards, users, teams, and student-award assignments
     */
    @Autowired
    public StudentsAwardsController(Global global) {
        this.global = global;
    }



    /**
     * Retrieves all student award assignments.
     *
     * @return a list of all StudentAward entries
     */
    @GetMapping("/all")
    public List<StudentAward> getAllStudentAwards() {
        return global.getStudentsAwards();
    }


    /**
     * Assigns an award to a specific student.
     *
     * @param awardId   ID of the award being assigned
     * @param studentId ID of the student receiving the award
     * @param teacherId ID of the teacher assigning the award
     * @param projectId ID of the project related to the award assignment
     * @return a status message indicating success or failure
     */
    @PostMapping("/assignStudentAward")
    public String assignAwardStudent(
            @RequestParam int awardId,
            @RequestParam long studentId,
            @RequestParam long teacherId,
            @RequestParam int projectId) {

        return global.assignAwardStudent(awardId, studentId, teacherId, projectId);
    }


    /**
     * Assigns an award to an entire team.
     *
     * @param awardId   ID of the award being assigned
     * @param teacherId ID of the teacher assigning the award
     * @param teamId    ID of the team receiving the award
     * @return a status message indicating success or failure
     */
    @PostMapping("/assignTeamAward")
    public String assignTeamAward(
            @RequestParam int awardId,
            @RequestParam long teacherId,
            @RequestParam int teamId) {

        return global.assignTeamAward(awardId, teacherId, teamId);
    }

    /**
     * Deletes a student award assignment.
     *
     * @param studentAwardId the ID of the student award record to delete
     * @return HTTP 200 OK if deletion was successful, otherwise 404 NOT FOUND
     */
    @DeleteMapping("/delete/{studentAwardId}")
    public ResponseEntity<String> deleteStudentAward(
            @PathVariable int studentAwardId) {

        String result = global.deleteStudentAward(studentAwardId);

        if (result.startsWith("Record successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
