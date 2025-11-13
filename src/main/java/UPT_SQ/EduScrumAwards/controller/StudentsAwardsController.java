package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.StudentAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students-awards")
public class StudentsAwardsController {
    private final Global global;

    @Autowired
    public StudentsAwardsController(Global global) {
        this.global = global;
    }
//
//    @GetMapping("/all")
//    public List<StudentAward> getAllStudentAwards() {
//        return global.getStudentsAwards();
//    }


    @PostMapping("/assignStudentAward")
    public String assignAwardStudent(
            @RequestParam int awardId,
            @RequestParam long studentId,
            @RequestParam long teacherId,
            @RequestParam int projectId) {

        return global.assignAwardStudent(awardId, studentId, teacherId, projectId);
    }


    @PostMapping("/assignTeamAward")
    public String assignTeamAward(
            @RequestParam int awardId,
            @RequestParam long teacherId,
            @RequestParam int teamId) {

        return global.assignTeamAward(awardId, teacherId, teamId);
    }
}
