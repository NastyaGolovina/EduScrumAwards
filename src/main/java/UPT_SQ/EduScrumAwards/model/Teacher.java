package UPT_SQ.EduScrumAwards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    public Teacher() {
        super();
        this.setRole(UserRole.TEACHER);
    }

    public Teacher(String name, String login, String password) {
        super(name, login, password, UserRole.TEACHER);
    }

    // Getters e Setters

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                '}';
    }
}