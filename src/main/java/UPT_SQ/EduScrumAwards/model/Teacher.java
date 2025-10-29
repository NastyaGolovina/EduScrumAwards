package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Professor no sistema EduScrum Awards
 * Um professor pode lecionar múltiplos cursos e criar projetos
 *
 * @author [Ana Souto]
 * @version 29/10/2025
 */
@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    // 📚 RELAÇÃO COM COURSES: Um Teacher pode lecionar múltiplos Courses
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseTeacher> courseTeachers = new ArrayList<>();

    // Construtores
    public Teacher() {
        super();
        this.setRole(UserRole.TEACHER);
    }

    public Teacher(String name, String login, String password) {
        super(name, login, password, UserRole.TEACHER);
    }

    // 🔄 MÉTODOS PARA GERENCIAR RELAÇÃO COM COURSES

    /**
     * Adiciona um curso ao professor com indicação de responsabilidade
     * @param course Curso a ser adicionado
     * @param isResponsible true se o professor é o responsável pelo curso
     */
    public void addCourse(Course course, Boolean isResponsible) {
        CourseTeacher courseTeacher = new CourseTeacher(course, this, isResponsible);
        this.courseTeachers.add(courseTeacher);
        // Mantém a consistência bidirecional
        if (course != null && course.getCourseTeachers() != null) {
            course.getCourseTeachers().add(courseTeacher);
        }
    }

    /**
     * Remove um curso do professor
     * @param course Curso a ser removido
     */
    public void removeCourse(Course course) {
        CourseTeacher courseTeacherToRemove = null;
        for (CourseTeacher ct : this.courseTeachers) {
            if (ct.getCourse() != null && ct.getCourse().equals(course)) {
                courseTeacherToRemove = ct;
                break;
            }
        }
        if (courseTeacherToRemove != null) {
            this.courseTeachers.remove(courseTeacherToRemove);
            courseTeacherToRemove.setCourse(null);
            courseTeacherToRemove.setTeacher(null);
        }
    }

    /**
     * Retorna todos os cursos que o professor leciona
     * @return Lista de cursos
     */
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        for (CourseTeacher ct : this.courseTeachers) {
            if (ct.getCourse() != null) {
                courses.add(ct.getCourse());
            }
        }
        return courses;
    }

    /**
     * Verifica se o professor é responsável por um curso específico
     * @param course Curso a verificar
     * @return true se for responsável
     */
    public boolean isResponsibleForCourse(Course course) {
        for (CourseTeacher ct : this.courseTeachers) {
            if (ct.getCourse() != null &&
                    ct.getCourse().equals(course) &&
                    Boolean.TRUE.equals(ct.getIsResponsible())) {
                return true;
            }
        }
        return false;
    }

    // 💼 MÉTODOS DE NEGÓCIO (a serem implementados)

    /**
     * Método para criar um novo curso
     * TODO: Implementar quando a classe Course estiver pronta
     */
    public void createCourse() {
        // Lógica será implementada posteriormente
        System.out.println("Creating new course...");
    }

    /**
     * Método para criar um novo projeto
     * TODO: Implementar quando a classe Project estiver pronta
     */
    public void createProject() {
        // Lógica será implementada posteriormente
        System.out.println("Creating new project...");
    }

    /**
     * Método para atribuir um award manualmente
     * TODO: Implementar quando a classe Award estiver pronta
     * @param student Estudante que receberá o award
     * @param award Award a ser atribuído
     */
    public void assignAward(Student student, Award award) {
        // TODO: Implementar quando a classe Award estiver pronta
        System.out.println("Assigning award to student: " + student.getName());
    }

    /**
     * Método para gerar dashboard de performance
     * TODO: Implementar geração de dashboard
     */
    public void generateDashboard() {
        // TODO: Implementar geração de dashboard
        System.out.println("Generating teacher dashboard...");
        // Futuramente: retornará dados de performance dos cursos e projetos
    }

    // Getters e Setters
    public List<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    public void setCourseTeachers(List<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", coursesCount=" + (courseTeachers != null ? courseTeachers.size() : 0) +
                '}';
    }
}