package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.DTO.SimpleUserDTO;
import UPT_SQ.EduScrumAwards.DTO.UserDTO;
import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.Teacher;
import UPT_SQ.EduScrumAwards.model.User;
import UPT_SQ.EduScrumAwards.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final Global global;

    @Autowired
    public TeacherController(Global global) {
        this.global = global;
    }

    // Criar novo professor
    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password) {

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

        if (name.length() > 100) {
            return ResponseEntity.badRequest().body("ERROR: Name too long (max 100 characters)");
        }
        if (login.length() > 50) {
            return ResponseEntity.badRequest().body("ERROR: Login too long (max 50 characters)");
        }
        if (password.length() > 255) {
            return ResponseEntity.badRequest().body("ERROR: Password too long (max 255 characters)");
        }

        // Verificar se login já existe
        for (User user : global.getUsers()) {
            if (user.getLogin().equals(login)) {
                return ResponseEntity.badRequest().body("ERROR: Login already exists");
            }
        }

        String result = global.createTeacher(name, login, password);

        if (result.equals("Success")) {
            return ResponseEntity.ok("SUCCESS: Teacher created successfully");
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Obter todos os professores
    @GetMapping("/all")
    public List<SimpleUserDTO> getAllTeachers() {
        List<SimpleUserDTO> teachers = new ArrayList<>();
        for (User user : global.getUsers()) {
            if (user.getRole() == UserRole.TEACHER) {
                teachers.add(new SimpleUserDTO(user.getUserId(), user.getName()));
            }
        }
        return teachers;
    }

    // Obter professores detalhados
    @GetMapping("/all/detailed")
    public List<UserDTO> getAllTeachersDetailed() {
        List<UserDTO> teachers = new ArrayList<>();
        for (User user : global.getUsers()) {
            if (user.getRole() == UserRole.TEACHER) {
                teachers.add(new UserDTO(
                        user.getUserId(),
                        user.getName(),
                        user.getLogin(),
                        user.getRole().toString()
                ));
            }
        }
        return teachers;
    }

    // Atualizar professor
    @PostMapping("/update")
    public ResponseEntity<String> updateTeacher(
            @RequestParam Long userId,
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam(required = false) String password) {

        // Validações
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Name is required");
        }
        if (login == null || login.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ERROR: Login is required");
        }

        if (name.length() > 100) {
            return ResponseEntity.badRequest().body("ERROR: Name too long (max 100 characters)");
        }
        if (login.length() > 50) {
            return ResponseEntity.badRequest().body("ERROR: Login too long (max 50 characters)");
        }

        // Verificar se login já existe para outro usuário
        for (User user : global.getUsers()) {
            if (user.getLogin().equals(login) && !user.getUserId().equals(userId)) {
                return ResponseEntity.badRequest().body("ERROR: Login already exists for another user");
            }
        }

        // Se senha for fornecida, validar
        if (password != null && !password.trim().isEmpty()) {
            if (password.length() > 255) {
                return ResponseEntity.badRequest().body("ERROR: Password too long (max 255 characters)");
            }
            if (password.length() < 6) {
                return ResponseEntity.badRequest().body("ERROR: Password must be at least 6 characters");
            }
        }

        String result = global.updateTeacher(userId, name, login,
                password != null ? password : "");

        if (result.equals("Success")) {
            return ResponseEntity.ok("SUCCESS: Teacher updated successfully");
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Deletar professor
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long userId) {
        String result = global.deleteUser(userId);

        if (result.equals("Success")) {
            return ResponseEntity.ok("SUCCESS: Teacher deleted successfully");
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
