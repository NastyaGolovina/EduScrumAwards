package UPT_SQ.EduScrumAwards.controller;



import UPT_SQ.EduScrumAwards.DTO.*;
import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final Global global;

    @Autowired
    public DashboardController(Global global) {
        this.global = global;
    }

//    @GetMapping("/students/points")
//    public Map<Long, Integer> getAllStudents() {
//        Map<Long, Integer> students = new HashMap<>();
//        for(User user : global.getUsers()) {
//            if(UserRole.STUDENT == user.getRole()) {
//                Student student = (Student) user;
//                if (students.containsKey(student.getUserId())) {
//                    students.put(user.getUserId(), students.get(student.getUserId())
//                            + student.calculatePoints(global.getStudentsAwards()));
//                } else {
//                    students.put(user.getUserId(), student.calculatePoints(global.getStudentsAwards()));
//                }
//            }
//        }
//        Map<Long, Integer> sorted =
//                students.entrySet()
//                        .stream()
//                        .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
//                        .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                Map.Entry::getValue,
//                                (a, b) -> a,
//                                LinkedHashMap::new
//                        ));
//
//        return sorted;
//    }

    @GetMapping("/students/points")
    public List<StudentPointsDTO> getAllStudents() {
        List<StudentPointsDTO> students = new ArrayList<>();
        for (User user : global.getUsers()) {
            if (UserRole.STUDENT == user.getRole()) {
                Student student = (Student) user;
                int points = student.calculatePoints(global.getStudentsAwards());
                students.add(new StudentPointsDTO(student.getUserId(),
                        student.getName(),student.getStudentNumber(), points));
            }
        }

        students.sort((a, b) -> b.getPoints().compareTo(a.getPoints()));
        return students;
    }


//    @GetMapping("/teams/points")
//    public Map<Integer, Integer> getAllTeams() {
//        Map<Integer, Integer> teams = new HashMap<>();
//        for(Team team : global.getTeams()) {
//            int teamId = team.getTeamID();
//                if (teams.containsKey(teamId)) {
//                    teams.put(teamId, teams.get(teamId)
//                            + team.earnAward(global.getStudentsAwards()));
//                } else {
//                    teams.put(teamId, team.earnAward(global.getStudentsAwards()));
//                }
//        }
//        Map<Integer, Integer> sorted =
//                teams.entrySet()
//                        .stream()
//                        .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
//                        .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                Map.Entry::getValue,
//                                (a, b) -> a,
//                                LinkedHashMap::new
//                        ));
//        return sorted;
//    }

    @GetMapping("/teams/points")
    public List<TeamPointsDTO> getAllTeams() {
        List<TeamPointsDTO> teams = new ArrayList<>();
        for (Team team : global.getTeams()) {
            int points = team.earnAward(global.getStudentsAwards());
            teams.add(new TeamPointsDTO(team.getTeamID(), team.getTeamName(), points));
        }

        teams.sort((a, b) -> b.getPoints().compareTo(a.getPoints()));
        return teams;
    }



    @GetMapping("/projects/progress")
    public List<ProjectsProgressDTO> getAllProjectsProgress() {
        List<ProjectsProgressDTO> progress = new ArrayList<>();
        for(Course course : global.getCourses()) {
            for(Project project : course.getProjects()) {
                double sum = 0;
                ArrayList<Sprint> sprints = (ArrayList<Sprint>) project.getSprints();
                if (!sprints.isEmpty())  {
                    for (Sprint sprint :sprints) {
                        sum += sprint.calcCompletionByScore();
                    }
                    double completion = sum / sprints.size();
                    progress.add(new ProjectsProgressDTO(project.getProjectId(),
                            project.getProjectName(),
                            completion));
                }

            }
        }
        return progress;
    }



    @GetMapping("/students-awards/{studentId}")
    public List<StudentAwardDTO> getStudentsAwards(
            @PathVariable long studentId) {
        List<StudentAwardDTO> studentAwards = new ArrayList<>();
        for(StudentAward studentAward : global.getStudentsAwards()) {
            if(studentAward.getStudent().getUserId() == studentId) {
                studentAwards.add(new StudentAwardDTO(studentAward.getStudentAwardId(),
                        studentAward.getAward().getAwardName(),
                        studentAward.getProject().getProjectName(),
                        studentAward.getDate(),
                        studentAward.getPoints()));
            }
        }
        return studentAwards;
    }

    @GetMapping("/course_average/{studentId}")
    public List<CourseAverageDTO> getCourseAverage(
            @PathVariable long studentId) {
        List<CourseAverageDTO> courseAverages = new ArrayList<>();
        for(Course course : global.getCourses()) {
            Map<Long, Integer> students = new HashMap<>();
            for(StudentAward studentAward : global.getStudentsAwards()) {
                if(studentAward.getProject().getCourse().getCourseID() == course.getCourseID()) {
                    long userId = studentAward.getStudent().getUserId();
                    if (students.containsKey(userId)) {
                        students.put(userId, students.get(userId)
                                + studentAward.getPoints());
                    } else {
                        students.put(userId, studentAward.getPoints());
                    }
                }
            }
            if(!students.isEmpty()) {
                int sum = 0;
                for (Map.Entry<Long, Integer> entry : students.entrySet()) {
                    sum += entry.getValue();
                }
                courseAverages.add(new CourseAverageDTO(course.getCourseID(),
                        course.getCourseName(),
                        (double) sum /students.size(),
                        global.getCourseStudentPointValue(course.getCourseID() , studentId)));
            }
        }
        return courseAverages;
    }









}
