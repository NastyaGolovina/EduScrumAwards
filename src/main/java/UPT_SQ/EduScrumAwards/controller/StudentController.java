package UPT_SQ.EduScrumAwards.controller;


import UPT_SQ.EduScrumAwards.DTO.SimpleUserDTO;
import UPT_SQ.EduScrumAwards.DTO.StudentDetailDTO;
import UPT_SQ.EduScrumAwards.DTO.UserDTO;
import UPT_SQ.EduScrumAwards.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final Global global;

    @Autowired
    public StudentController(Global global) {
        this.global = global;
    }

    // Criar novo estudante
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String studentNumber,
            @RequestParam Integer currentSemester) {

        // Validações
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Name is required");
        }
        if (login == null || login.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Login is required");
        }
        if (password == null || password.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Password is required");
        }
        if (studentNumber == null || studentNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Student number is required");
        }
        if (currentSemester == null || currentSemester < 1) {
            return ResponseEntity.badRequest().body("ERROR: Current semester must be at least 1");
        }

        if (name.length() > 100) {
            return ResponseEntity.badRequest().body("ERROR: Name too long (max 100 characters)");
        }
        if (login.length() > 50) {
            return ResponseEntity.badRequest().body("ERROR: Login too long (max 50 characters)");
        }
        if (password.length() > 255) {
            return ResponseEntity.badRequest().body("ERROR: Password too long (max 255 characters)");
        }
        if (studentNumber.length() > 20) {
            return ResponseEntity.badRequest().body("ERROR: Student number too long (max 20 characters)");
        }

        // Verificar se login já existe
        for (User user : global.getUsers()) {
            if (user.getLogin().equals(login)) {
                return ResponseEntity.badRequest().body("ERROR: Login already exists");
            }
        }

        String result = global.createStudent(name, login, password, studentNumber, currentSemester);

        if (result.equals("Success")) {
            return ResponseEntity.ok("SUCCESS: Student created successfully");
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Obter todos os estudantes
    @GetMapping("/all")
    public List<SimpleUserDTO> getAllStudents() {
        List<SimpleUserDTO> students = new ArrayList<>();
        for (User user : global.getUsers()) {
            if (user.getRole() == UserRole.STUDENT) {
                students.add(new SimpleUserDTO(user.getUserId(), user.getName()));
            }
        }
        return students;
    }

    // Obter estudantes detalhados
    @GetMapping("/all/detailed")
    public List<StudentDetailDTO> getAllStudentsDetailed() {
        List<StudentDetailDTO> students = new ArrayList<>();
        // Obtenha a lista uma vez para reutilizar
        ArrayList<StudentAward> studentAwards = global.getStudentsAwards();

        for (User user : global.getUsers()) {
            if (user.getRole() == UserRole.STUDENT) {
                Student student = (Student) user;
                // Calcule os pontos passando a lista
                int totalScore = student.calculatePoints(studentAwards);

                students.add(new StudentDetailDTO(
                        student.getUserId(),
                        student.getName(),
                        student.getLogin(),
                        student.getStudentNumber(),
                        student.getCurrentSemester(),
                        totalScore  // Pontuação calculada
                ));
            }
        }
        return students;
    }

    // Obter estudante por ID
    @GetMapping("/{userId}")
    public ResponseEntity<StudentDetailDTO> getStudentById(@PathVariable Long userId) {
        User user = global.searchUser(userId);
        if (user != null && user.getRole() == UserRole.STUDENT) {
            Student student = (Student) user;
            StudentDetailDTO dto = new StudentDetailDTO(
                    student.getUserId(),
                    student.getName(),
                    student.getLogin(),
                    student.getStudentNumber(),
                    student.getCurrentSemester(),
                    student.getTotalScore()
            );
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // Atualizar estudante
    @PostMapping("/update")
    public ResponseEntity<String> updateStudent(
            @RequestParam Long userId,
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam(required = false) String password,
            @RequestParam String studentNumber,
            @RequestParam Integer currentSemester) {

        // Validações
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Name is required");
        }
        if (login == null || login.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Login is required");
        }
        if (studentNumber == null || studentNumber.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Student number is required");
        }
        if (currentSemester == null || currentSemester < 1) {
            return ResponseEntity.badRequest().body("ERROR: Current semester must be at least 1");
        }

        if (name.length() > 100) {
            return ResponseEntity.badRequest().body("ERROR: Name too long (max 100 characters)");
        }
        if (login.length() > 50) {
            return ResponseEntity.badRequest().body("ERROR: Login too long (max 50 characters)");
        }
        if (studentNumber.length() > 20) {
            return ResponseEntity.badRequest().body("ERROR: Student number too long (max 20 characters)");
        }

        // Verificar se login já existe para outro usuário
        for (User user : global.getUsers()) {
            if (user.getLogin().equals(login) && !user.getUserId().equals(userId)) {
                return ResponseEntity.badRequest().body("ERROR: Login already exists for another user");
            }
        }

        String result = global.updateStudent(userId, name, login, password,
                studentNumber, currentSemester);

        if (result.equals("Success")) {
            return ResponseEntity.ok("SUCCESS: Student updated successfully");
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Deletar estudante
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long userId) {
        String result = global.deleteUser(userId);

        if (result.equals("Success")) {
            return ResponseEntity.ok("SUCCESS: Student deleted successfully");
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Calcular pontuação total do estudante
    @GetMapping("/{userId}/score")
    public ResponseEntity<Integer> calculateStudentScore(@PathVariable Long userId) {
        User user = global.searchUser(userId);
        if (user != null && user.getRole() == UserRole.STUDENT) {
            Student student = (Student) user;
            // Passe a lista de StudentAward do global como argumento
            Integer score = student.calculatePoints(global.getStudentsAwards());
            return ResponseEntity.ok(score);
        }
        return ResponseEntity.notFound().build();
    }
}
