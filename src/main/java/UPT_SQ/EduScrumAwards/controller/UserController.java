package UPT_SQ.EduScrumAwards.controller;

import UPT_SQ.EduScrumAwards.DTO.UserDTO;
import UPT_SQ.EduScrumAwards.model.Global;
import UPT_SQ.EduScrumAwards.model.User;
import UPT_SQ.EduScrumAwards.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Global global;

    @Autowired
    public UserController(Global global) {
        this.global = global;
    }

    // 1. Login de usuário (usando o método validateLogin da Global)
    // 1. User login (using the validateLogin method of Global)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String login,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        // Usar o método validateLogin da Global que já faz todas as validações
        // Use the validateLogin method from Global, which already handles all validations.
        User user = global.validateLogin(login, password);

        if (user != null) {
            response.put("success", true);
            response.put("message", "SUCCESS: Login successful");
            response.put("user", createUserResponse(user));
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "ERROR: Invalid login or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 2. Verificar disponibilidade de login
    //2. Check login availability
    @GetMapping("/check-login/{login}")
    public ResponseEntity<Map<String, Object>> checkLoginAvailability(@PathVariable String login) {
        Map<String, Object> response = new HashMap<>();

        if (login == null || login.trim().isEmpty()) {
            response.put("available", false);
            response.put("message", "ERROR: Login is required");
            return ResponseEntity.badRequest().body(response);
        }

        boolean isAvailable = !global.isLoginAlreadyUsed(login);
        response.put("available", isAvailable);
        response.put("message", isAvailable ? "Login is available" : "Login already exists");
        response.put("login", login);

        return ResponseEntity.ok(response);
    }

    // 3. Obter todos os usuários
    //3. Get all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : global.getUsers()) {
            userDTOs.add(convertToDTO(user));
        }
        return ResponseEntity.ok(userDTOs);
    }

    // 4. Obter usuário por ID
    //4. Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        User user = global.searchUser(userId);
        if (user != null) {
            return ResponseEntity.ok(convertToDTO(user));
        }
        return ResponseEntity.notFound().build();
    }

    // 5. Alterar senha (versão melhorada)
    //5. Change password (improved version)
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestParam Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {

        Map<String, Object> response = new HashMap<>();

        // Verificar se usuário existe
        // Check if user exists
        User user = global.searchUser(userId);
        if (user == null) {
            response.put("success", false);
            response.put("message", "ERROR: User not found");
            return ResponseEntity.badRequest().body(response);
        }

        // Verificar senha atual usando validateLogin
        // Verify current password using validateLogin
        User validatedUser = global.validateLogin(user.getLogin(), oldPassword);
        if (validatedUser == null) {
            response.put("success", false);
            response.put("message", "ERROR: Current password is incorrect");
            return ResponseEntity.badRequest().body(response);
        }

        // Validar nova senha
        // Validate new password
        if (newPassword == null || newPassword.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "ERROR: New password is required");
            return ResponseEntity.badRequest().body(response);
        }

        // Verificar se as senhas coincidem
        // Check if the passwords match
        if (!newPassword.equals(confirmPassword)) {
            response.put("success", false);
            response.put("message", "ERROR: New passwords do not match");
            return ResponseEntity.badRequest().body(response);
        }

        // Usar validação de senha da Global
        //Use Global password validation.
        if (!global.isPasswordValid(newPassword)) {
            response.put("success", false);
            response.put("message", "ERROR: Password must be at least 6 characters and maximum 255");
            return ResponseEntity.badRequest().body(response);
        }

        // Verificar se a nova senha é igual à antiga
        // Check if the new password is the same as the old one.
        if (oldPassword.equals(newPassword)) {
            response.put("success", false);
            response.put("message", "ERROR: New password must be different from current password");
            return ResponseEntity.badRequest().body(response);
        }

        // Atualizar senha (em produção, usar hash!)
        // Update password (in production, use hash!)
        user.setPassword(newPassword);

        // Atualizar no banco baseado no tipo de usuário
        // Update the database based on the user type.
        String updateResult;
        if (user.getRole() == UserRole.TEACHER) {
            updateResult = global.updateTeacher(userId, user.getName(), user.getLogin(), newPassword);
        } else if (user.getRole() == UserRole.STUDENT) {
            // Para estudante, precisamos dos outros campos
            // Como é apenas mudança de senha, mantemos os outros dados

            // For students, we need the other fields.
            // Since it's just a password change, we'll keep the other data.
            updateResult = global.updateStudent(userId, user.getName(), user.getLogin(),
                    newPassword, "", 1); // Campos vazios serão ignorados
        } else {
            response.put("success", false);
            response.put("message", "ERROR: Unknown user role");
            return ResponseEntity.badRequest().body(response);
        }

        if (updateResult.equals("Success")) {
            response.put("success", true);
            response.put("message", "SUCCESS: Password changed successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", updateResult);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 6. Validar formato de email
    // 6. Validate email format
    @GetMapping("/validate-email/{email}")
    public ResponseEntity<Map<String, Object>> validateEmailFormat(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();

        boolean isValid = global.isValidEmailFormat(email);
        response.put("valid", isValid);
        response.put("message", isValid ? "Valid email format" : "Invalid email format");
        response.put("email", email);

        return ResponseEntity.ok(response);
    }

    // 7. Validar força da senha
    // 7. Validate password strength
    @GetMapping("/validate-password/{password}")
    public ResponseEntity<Map<String, Object>> validatePasswordStrength(@PathVariable String password) {
        Map<String, Object> response = new HashMap<>();

        boolean isValid = global.isPasswordValid(password);
        response.put("valid", isValid);
        response.put("message", isValid ? "Password meets requirements" :
                "Password must be at least 6 characters and maximum 255");
        response.put("requirements", "Password must be 6-255 characters");

        return ResponseEntity.ok(response);
    }

    // 8. Obter estatísticas de usuários
    // 8. Obtain user statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        Map<String, Object> stats = new HashMap<>();
        List<User> users = global.getUsers();

        int totalUsers = users.size();
        int teachers = 0;
        int students = 0;

        for (User user : users) {
            if (user.getRole() == UserRole.TEACHER) {
                teachers++;
            } else if (user.getRole() == UserRole.STUDENT) {
                students++;
            }
        }

        stats.put("totalUsers", totalUsers);
        stats.put("teachers", teachers);
        stats.put("students", students);
        stats.put("lastUpdated", new java.util.Date());

        return ResponseEntity.ok(stats);
    }

    // 9. Obter usuário pelo login (sem senha)
    // 9. Get user by login (without password)
    @GetMapping("/by-login/{login}")
    public ResponseEntity<UserDTO> getUserByLogin(@PathVariable String login) {
        User user = global.findUserByLoginInMemory(login);
        if (user != null) {
            return ResponseEntity.ok(convertToDTO(user));
        }
        return ResponseEntity.notFound().build();
    }

    // 10. Verificar se usuário pode ser deletado
    // 10. Check if the user can be deleted.
    @GetMapping("/can-delete/{userId}")
    public ResponseEntity<Map<String, Object>> canDeleteUser(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();

        boolean canDelete = global.canDeleteUser(userId);
        User user = global.searchUser(userId);

        response.put("canDelete", canDelete);
        response.put("userId", userId);
        response.put("userName", user != null ? user.getName() : "Unknown");
        response.put("message", canDelete ?
                "User can be deleted" :
                "User cannot be deleted (linked to courses, teams, or awards)");

        return ResponseEntity.ok(response);
    }

    // 11. Reset de senha (simulação - em produção enviaria email)
    // 11. Password reset (simulation - in production, it would send an email)
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam String login) {
        Map<String, Object> response = new HashMap<>();

        User user = global.findUserByLoginInMemory(login);
        if (user == null) {
            response.put("success", false);
            response.put("message", "ERROR: User not found with this login");
            return ResponseEntity.badRequest().body(response);
        }

        // Em produção: gerar token, enviar email, etc.
        // Por enquanto, apenas simulação
        response.put("success", true);
        response.put("message", "Password reset instructions sent to " + login);
        response.put("userId", user.getUserId());
        response.put("userName", user.getName());

        return ResponseEntity.ok(response);
    }

    // =============================================
    // MÉTODOS AUXILIARES PRIVADOS
    // (PRIVATE AUXILIARY METHODS)
    // =============================================

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getLogin(),
                user.getRole().toString()
        );
    }

    private Map<String, Object> createUserResponse(User user) {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("userId", user.getUserId());
        userResponse.put("name", user.getName());
        userResponse.put("login", user.getLogin());
        userResponse.put("role", user.getRole().toString());
        userResponse.put("loginTime", new java.util.Date().toString());

        // Informações específicas por tipo de usuário
        // Specific information by user type
        if (user.getRole() == UserRole.STUDENT) {
            userResponse.put("userType", "STUDENT");
        } else if (user.getRole() == UserRole.TEACHER) {
            userResponse.put("userType", "TEACHER");
        }

        return userResponse;
    }
}
/*
        /login - Autenticação de usuário ✓
        /check-login/{login} - Verifica se login está disponível ✓
        /all - Lista todos os usuários ✓
        /{userId} - Obtém usuário por ID ✓
        /change-password - Altera senha (com validações) ✓
        /validate-email/{email} - Valida formato de email ✓
        /validate-password/{password} - Valida força da senha ✓
        /stats - Estatísticas de usuários ✓
        /by-login/{login} - Busca usuário pelo login ✓
        /can-delete/{userId} - Verifica se pode deletar usuário ✓
*/
