package UPT_SQ.EduScrumAwards.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Estudante no sistema EduScrum Awards
 * Um estudante pode participar de múltiplas equipes e acumular awards
 *
 * @author [Ana Souto]
 * @version 29/10/2025
 */
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    // 🎯 CAMPOS ESPECÍFICOS DO STUDENT
    @Column(name = "student_number", unique = true, length = 20)
    private String studentNumber;

    @Column(name = "current_semester")
    private Integer currentSemester;

    // SCORE (transient - calculado dinamicamente)
    @Transient
    private Integer totalScore = 0;

    // RELAÇÕES (comentadas até as outras classes estarem prontas)

    // Relacionamento com TeamMember (será implementado pela Lily)
    // @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<TeamMember> teamMemberships = new ArrayList<>();

    // Relacionamento com StudentAward (será implementado pela Anastasia)
    // @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<StudentAward> studentAwards = new ArrayList<>();

    // Construtores
    public Student() {
        super();
        this.setRole(UserRole.STUDENT);
    }

    public Student(String name, String login, String password) {
        super(name, login, password, UserRole.STUDENT);
    }

    public Student(String name, String login, String password, String studentNumber, Integer currentSemester) {
        super(name, login, password, UserRole.STUDENT);
        this.studentNumber = studentNumber;
        this.currentSemester = currentSemester;
    }

    //MÉTODOS DE NEGÓCIO

    /**
     * Calcula o score total do estudante baseado nos awards recebidos
     * TODO: Implementar cálculo real quando StudentAward estiver pronto
     * @return Score total do estudante
     */
    public Integer calculateTotalScore() {
        // TODO: Implementar cálculo baseado nos awards quando StudentAward estiver pronto
        // Por enquanto, retornamos um valor placeholder
        System.out.println("Calculating total score for student: " + this.getName());

        // Futuramente: percorrer studentAwards e somar os pontos
        // for (StudentAward award : studentAwards) {
        //     this.totalScore += award.getAward().getPointsValue();
        // }

        return this.totalScore;
    }

    /**
     * Retorna a lista de awards do estudante
     * TODO: Implementar quando StudentAward estiver pronto
     * @return Lista de awards do estudante
     */
    public List<StudentAward> getStudentAwards() {
        // TODO: Implementar quando StudentAward estiver pronto
        System.out.println("Retrieving student awards...");
        return new ArrayList<>();
    }

    /**
     * Método para o estudante receber um award
     * TODO: Implementar quando StudentAward estiver pronto
     * @param award Award a ser recebido
     */
    public void earnAward(Award award) {
        // TODO: Implementar quando Award e StudentAward estiverem prontos
        System.out.println("Student " + this.getName() + " earned award: " + award.getAwardName());
        // Futuramente: criar StudentAward e adicionar à lista
    }

    /**
     * Gera dashboard pessoal do estudante
     * TODO: Implementar geração de dashboard do estudante
     */
    public void generateDashboard() {
        // TODO: Implementar geração de dashboard do estudante
        System.out.println("Generating student dashboard for: " + this.getName());
        // Futuramente: retornará progresso, awards, ranking, etc.
    }

    /**
     * Verifica se o estudante está em um curso específico
     * TODO: Implementar quando as relações estiverem completas
     * @param course Curso a verificar
     * @return true se o estudante está no curso
     */
    public boolean isEnrolledInCourse(Course course) {
        // TODO: Implementar via Team -> Project -> Course
        System.out.println("Checking if student is enrolled in course: " + course.getCourseName());
        return false;
    }

    // Getters e Setters
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    // Getters para relacionamentos (serão descomentados quando as classes estiverem prontas)
    /*
    public List<TeamMember> getTeamMemberships() {
        return teamMemberships;
    }

    public void setTeamMemberships(List<TeamMember> teamMemberships) {
        this.teamMemberships = teamMemberships;
    }
    */

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", currentSemester=" + currentSemester +
                ", totalScore=" + totalScore +
                '}';
    }
}