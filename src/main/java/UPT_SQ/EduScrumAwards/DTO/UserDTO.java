package UPT_SQ.EduScrumAwards.DTO;

public class UserDTO {
    private Long userId;
    private String name;
    private String login;
    private String role;

    public UserDTO() {}

    public UserDTO(Long userId, String name, String login, String role) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.role = role;
    }

    // Getters e Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
