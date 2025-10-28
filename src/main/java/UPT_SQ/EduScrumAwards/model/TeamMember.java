package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "team_members")
public class TeamMember {

    public enum Role { SCRUM_MASTER, PRODUCT_OWNER, DEVELOPER, QA_ENGINEER }

    @Id
    @Column(name = "team_member_id", length = 36)
    private String teamMemberId;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    // Many TeamMember rows can point to one Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Many TeamMember rows belong to one Team
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public TeamMember() {}
    public TeamMember(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    // getters/setters
    public String getTeamMemberId() {
        return teamMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "teamMemberId='" + teamMemberId + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", student=" + student +
                ", team=" + team +
                '}';
    }
}