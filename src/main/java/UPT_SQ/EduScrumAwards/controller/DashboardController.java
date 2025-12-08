package UPT_SQ.EduScrumAwards.controller;



import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final Global global;

    @Autowired
    public DashboardController(Global global) {
        this.global = global;
    }

    @GetMapping("/students/points")
    public HashMap<Long, Integer> getAllStudents() {
        HashMap<Long, Integer> students = new HashMap<>();
        for(User user : global.getUsers()) {
            if(UserRole.STUDENT == user.getRole()) {
                Student student = (Student) user;
                if (students.containsKey(student.getUserId())) {
                    students.put(user.getUserId(), students.get(student.getUserId())
                            + student.calculatePoints(global.getStudentsAwards()));
                } else {
                    students.put(user.getUserId(), student.calculatePoints(global.getStudentsAwards()));
                }
            }
        }
        return students;
    }

//    @GetMapping("/teams/points")
//    public HashMap<Integer, Integer> getAllTeams() {
//        HashMap<Integer, Integer> teams = new HashMap<>();
//        for(Team team : global.getTeams()) {
////                if (students.containsKey(student.getUserId())) {
////                    students.put(user.getUserId(), students.get(student.getUserId())
////                            + student.calculatePoints(global.getStudentsAwards()));
////                } else {
////                    students.put(user.getUserId(), student.calculatePoints(global.getStudentsAwards()));
////                }
//        }
//        return teams;
//    }


}
