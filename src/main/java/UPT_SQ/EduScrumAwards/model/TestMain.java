package UPT_SQ.EduScrumAwards.model;

// import org.hibernate.Session;
// import org.hibernate.Transaction;
import java.util.List;

// import java.util.ArrayList;
//import java.util.Date;
import java.util.Scanner;

public class TestMain {
    //public static void main(String[] args) {
//
//        DatabaseHelper databaseHelper = new DatabaseHelper();
//        databaseHelper.setup();


//        just to test
//         we will remove this class later



//        long ts = System.currentTimeMillis();
//        Award award = new Award(
//                "Top Performer " + ts,
//                "Awarded for outstanding performance",
//                100,
//                AwardType.AUTOMATIC,
//                AssignMode.TEAM
//        );
//
//        Team team = new Team(
//                "IronMan-" + ts
//        );
//
//        Student student = new Student();
//        student.setName("Alice " + ts);
//        student.setLogin("alice_" + ts);
//        student.setPassword("pass123");
//
//        TeamMember mem = new TeamMember("Alice Wonderland", TeamMember.Role.PRODUCT_OWNER);
//        mem.setTeam(team);
//        mem.setStudent(student);
//
//        Teacher teacher = new Teacher();
//        teacher.setName("Prof. Smith " + ts);
//        teacher.setLogin("prof_smith_" + ts);
//        teacher.setPassword("pass123");
//
//        Course course = new Course("Test Course " + ts);
//        CourseTeacher courseTeacher = new CourseTeacher(course, teacher, true);
//
//        Project project = new Project();
//        project.setProjectName("AI Development " + ts);
//        project.setTeam(team);
//        project.setCourse(course);
//
//        Sprint sprint1 = new Sprint();
//        sprint1.setStartDate(new Date()); // today
//        sprint1.setEndDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)); // +7 days
//        sprint1.setProject(project);
//
//
//        Goal goal1 = new Goal("Implement login feature", 10, false);
//        goal1.setSprint(sprint1);
//
//        AwardRule awardRule = new AwardRule();
//        awardRule.setAward(award);
//        awardRule.setProject(project);
//        awardRule.setTeacher(teacher);
//        awardRule.setAllGoalsCompleted(false);
//        awardRule.setCompletionPercent(75.0);
//
//        StudentAward studentAward = new StudentAward();
//        studentAward.setAward(award);
//        studentAward.setStudent(student);
//        studentAward.setTeacher(teacher);
//        studentAward.setProject(project);
//        studentAward.setTeam(team);
//        studentAward.setDate(new Date());
//        studentAward.setPoints(100);
//
//        DatabaseHelper databaseHelper = new DatabaseHelper();
//        databaseHelper.setup();
//        Session session = databaseHelper.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        session.persist(award);
//        session.persist(team);
//        session.persist(student);
//        session.persist(mem);
//        session.persist(teacher);
//        session.persist(course);
//        session.persist(courseTeacher);
//        session.persist(project);
//        session.persist(sprint1);
//        session.persist(goal1);
//
//        session.persist(awardRule);
//        session.persist(studentAward);
//
//
//        session.flush();
//        session.getTransaction().commit();
//        session.close();
//

//
//        // =============================================
//        // NEW TESTS - USER, TEACHER AND STUDENT
//        // =============================================
//        System.out.println("===  STARTING THE TESTS - USER/TEACHER/STUDENT ===");
//
//        try {
//            // Execute new tests using the same DatabaseHelper
//            UserTeacherStudentCRUD(databaseHelper);
//        } catch (Exception e) {
//            System.err.println("Error during new tests: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            databaseHelper.exit();
//        }
//
//        System.out.println("=== ALL TESTS COMPLETED ===");



//        ////        TEST Global
    // Global g = new Global();
//        g.createCourse("###### TEST COURSE 2");
//        g.readAllCourseWithJplq();
//        for (Course courses : g.getCourses()) {
//            System.out.println("###### " + courses.getCourseName());
//        }
//        System.out.println("###### Searched course: " + g.searchCourse(1).getCourseName());
//        g.updateCourse(1, "NEW NAME");
//        System.out.println("###### Searched course: " + g.searchCourse(1).getCourseName());
//
//
//        course.createCourseTeacher(teacher, false);
//        course.readAllCourseTeacherWithJplq();
//        for (CourseTeacher ct : course.getTeachers()) {
//            System.out.println("###### " + ct.getTeacher().getName());
//        }
//        int teacherId = course.getTeachers().get(0).getCourseTeacherID();
//        System.out.println("###### Searched teacher: " + course.searchCourseTeacher(teacherId).getTeacher().getName() + ". Is responsible Teacher: " + (course.searchCourseTeacher(teacherId).isResponsible() ? "Yes" : "No"));
//        course.updateCourseTeacher(teacherId, true);
//        System.out.println("###### Searched teacher: " + course.searchCourseTeacher(teacherId).getTeacher().getName() + ". Is responsible Teacher: " + (course.searchCourseTeacher(teacherId).isResponsible() ? "Yes" : "No"));
//        course.readAllCourseTeacherWithJplq();
//        System.out.println(("###### Size of ct's: " + course.getTeachers().size()));
//        course.deleteCourseTeacher(teacherId);
//        course.readAllCourseTeacherWithJplq();
//        System.out.println(("###### Size of ct's: " + course.getTeachers().size()));
//
//


//       g.createAward("Top Performer",
//                "Awarded for outstanding performance",
//                100,
//                "AUTOMATIC",
//                 "TEAM");
//        g.readAllAwardWithJplq();
//        for(int i = 0; i < g.getAwards().size(); i++)
//        {
//            System.out.println("Award ID: " + g.getAwards().get(i));
//        }
//        System.out.println(g.updateAward(1,"Dragon Rider",
//                "Awarded for fearlessness and strategy",
//                100));
//        g.readAllStudentAwardWithJplq();
//        for(StudentAward sa : g.getStudentsAwards()) {
//            System.out.println(sa);
//        }

//        g.readAllTeamWithJplq();
//        System.out.println();
//        for (Team team : g.getTeams()) {
//
//            System.out.println(team.getTeamName() + "\n");
//
//            System.out.println(team.getTeamMember().get(0).getName() + "\n");
//
//        }

    //}


    private static Global global = new Global();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🎯 ===========================================");
        System.out.println("🎯 TESTE CRUD - USER, TEACHER E STUDENT");
        System.out.println("🎯 Classe Global Atualizada");
        System.out.println("🎯 ===========================================");

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n📋 MENU DE TESTES CRUD:");
            System.out.println("1️⃣  - CREATE (Criar Teachers e Students)");
            System.out.println("2️⃣  - READ (Ler todos os Users)");
            System.out.println("3️⃣  - READ TEACHERS (Ler apenas Teachers)");
            System.out.println("4️⃣  - READ STUDENTS (Ler apenas Students)");
            System.out.println("5️⃣  - UPDATE (Atualizar Users)");
            System.out.println("6️⃣  - DELETE (Deletar Users)");
            System.out.println("7️⃣  - TESTAR VALIDAÇÕES");
            System.out.println("8️⃣  - EXECUTAR TODOS OS TESTES");
            System.out.println("9   - TEST CREATE COURSE-TEACHER");
            System.out.println("10   - TEST DELETE COURSE-TEACHER");
            System.out.println("0️⃣  - SAIR");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    testarCreate();
                    break;
                case 2:
                    testarReadAllUsers();
                    break;
                case 3:
                    testarReadTeachers();
                    break;
                case 4:
                    testarReadStudents();
                    break;
                case 5:
                    testarUpdate();
                    break;
                case 6:
                    testarDelete();
                    break;
                case 7:
                    testarValidacoes();
                    break;
                case 8:
                    executarTodosTestes();
                    break;
                case 9:
                    createCourseTeacher();
                    break;
                case 10:
                    testDeleteCourseTeacher();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("👋 Saindo do programa...");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }

            if (continuar && opcao != 8) {
                System.out.print("\n⏸️  Pressione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void testarCreate() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO CREATE (CRIAÇÃO)");
        System.out.println("🎯 ===========================================");

        // Criar Teachers
        System.out.println("\n📝 CRIANDO TEACHERS:");
        System.out.println("-------------------");

        String result1 = global.createTeacher("Dr. Carlos Santos", "carlos.santos", "teacher123");
        System.out.println("✅ createTeacher 1: " + result1);

        String result2 = global.createTeacher("Prof. Maria Oliveira", "maria.oliveira", "teach456");
        System.out.println("✅ createTeacher 2: " + result2);

        // Criar Students
        System.out.println("\n📝 CRIANDO STUDENTS:");
        System.out.println("-------------------");

        String result3 = global.createStudent("Ana Silva", "ana.silva", "password123", "2023001", 2);
        System.out.println("✅ createStudent 1: " + result3);

        String result4 = global.createStudent("João Pereira", "joao.pereira", "pass456", "2023002", 3);
        System.out.println("✅ createStudent 2: " + result4);

        String result5 = global.createStudent("Maria Costa", "maria.costa", "student789", "2023003", 1);
        System.out.println("✅ createStudent 3: " + result5);

        System.out.println("\n🎉 CREATE TESTADO COM SUCESSO!");
    }

    private static void testarReadAllUsers() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO READ ALL USERS");
        System.out.println("🎯 ===========================================");

        System.out.println("\n👥 CARREGANDO TODOS OS USERS DO BANCO:");
        System.out.println("-------------------------------------");
        global.readAllUserWithJplq();

        if (global.getUsers().isEmpty()) {
            System.out.println("❌ Nenhum user encontrado no banco!");
            return;
        }

        System.out.println("✅ Total de users carregados: " + global.getUsers().size());
        exibirUsersDetalhados(global.getUsers());
    }

    private static void testarReadTeachers() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO READ TEACHERS");
        System.out.println("🎯 ===========================================");

        System.out.println("\n👨‍🏫 CARREGANDO APENAS TEACHERS DO BANCO:");
        System.out.println("--------------------------------------");
        global.readAllTeacherWithJplq();

        long teacherCount = global.getUsers().stream().filter(u -> u instanceof Teacher).count();
        System.out.println("✅ Total de Teachers carregados: " + teacherCount);

        // Mostrar apenas teachers
        List<User> teachers = global.getUsers().stream()
                .filter(u -> u instanceof Teacher)
                .toList();
        exibirUsersDetalhados(teachers);
    }

    private static void testarReadStudents() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO READ STUDENTS");
        System.out.println("🎯 ===========================================");

        System.out.println("\n👨‍🎓 CARREGANDO APENAS STUDENTS DO BANCO:");
        System.out.println("--------------------------------------");
        global.readAllStudentWithJplq();

        long studentCount = global.getUsers().stream().filter(u -> u instanceof Student).count();
        System.out.println("✅ Total de Students carregados: " + studentCount);

        // Mostrar apenas students
        List<User> students = global.getUsers().stream()
                .filter(u -> u instanceof Student)
                .toList();
        exibirUsersDetalhados(students);
    }

    private static void testarUpdate() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO UPDATE (ATUALIZAÇÃO)");
        System.out.println("🎯 ===========================================");

        // Primeiro carrega os users
        global.readAllUserWithJplq();

        if (global.getUsers().isEmpty()) {
            System.out.println("❌ Nenhum user encontrado para atualizar!");
            return;
        }

        // Atualizar um Student
        Student studentParaAtualizar = null;
        for (User user : global.getUsers()) {
            if (user instanceof Student) {
                studentParaAtualizar = (Student) user;
                break;
            }
        }

        if (studentParaAtualizar != null) {
            System.out.println("\n📝 ATUALIZANDO STUDENT:");
            System.out.println("----------------------");
            System.out.println("📋 Antes da atualização:");
            System.out.println("   Nome: " + studentParaAtualizar.getName());
            System.out.println("   Login: " + studentParaAtualizar.getLogin());
            System.out.println("   Student Number: " + studentParaAtualizar.getStudentNumber());
            System.out.println("   Semestre: " + studentParaAtualizar.getCurrentSemester());

            String result = global.updateStudent(
                    studentParaAtualizar.getUserId(),
                    studentParaAtualizar.getName() + " [ATUALIZADO]",
                    studentParaAtualizar.getLogin() + ".atualizado",
                    "novasenha123",
                    studentParaAtualizar.getStudentNumber(),
                    studentParaAtualizar.getCurrentSemester() + 1
            );
            System.out.println("✅ updateStudent: " + result);
        } else {
            System.out.println("❌ Nenhum Student encontrado para atualizar!");
        }

        // Atualizar um Teacher
        Teacher teacherParaAtualizar = null;
        for (User user : global.getUsers()) {
            if (user instanceof Teacher) {
                teacherParaAtualizar = (Teacher) user;
                break;
            }
        }

        if (teacherParaAtualizar != null) {
            System.out.println("\n📝 ATUALIZANDO TEACHER:");
            System.out.println("----------------------");
            System.out.println("📋 Antes da atualização:");
            System.out.println("   Nome: " + teacherParaAtualizar.getName());
            System.out.println("   Login: " + teacherParaAtualizar.getLogin());

            String result = global.updateTeacher(
                    teacherParaAtualizar.getUserId(),
                    teacherParaAtualizar.getName() + " [ATUALIZADO]",
                    teacherParaAtualizar.getLogin() + ".atualizado",
                    "novasenha456"
            );
            System.out.println("✅ updateTeacher: " + result);
        } else {
            System.out.println("❌ Nenhum Teacher encontrado para atualizar!");
        }

        // Mostrar resultado das atualizações
        System.out.println("\n📊 APÓS AS ATUALIZAÇÕES:");
        global.readAllUserWithJplq();
        System.out.println("✅ Total de users: " + global.getUsers().size());
        exibirUsersResumido(global.getUsers());
    }

    private static void testarDelete() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO DELETE (EXCLUSÃO)");
        System.out.println("🎯 ===========================================");

        global.readAllUserWithJplq();

        if (global.getUsers().isEmpty()) {
            System.out.println("❌ Nenhum user encontrado para deletar!");
            return;
        }

        System.out.println("\n📊 ANTES DA EXCLUSÃO:");
        System.out.println("Total de users: " + global.getUsers().size());
        exibirUsersResumido(global.getUsers());

        // Deletar o último user
        User userParaDeletar = global.getUsers().get(global.getUsers().size() - 1);
        System.out.println("\n🗑️  EXCLUINDO USER:");
        System.out.println("-----------------");
        System.out.println("User a ser excluído: " + userParaDeletar.getName() +
                " (ID: " + userParaDeletar.getUserId() + ", Tipo: " +
                (userParaDeletar instanceof Teacher ? "TEACHER" : "STUDENT") + ")");

        String result = global.deleteUser(userParaDeletar.getUserId());
        System.out.println("✅ deleteUser: " + result);

        System.out.println("\n📊 APÓS A EXCLUSÃO:");
        global.readAllUserWithJplq();
        System.out.println("Total de users: " + global.getUsers().size());
        exibirUsersResumido(global.getUsers());
    }

    private static void testarValidacoes() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTANDO VALIDAÇÕES (ERROS ESPERADOS)");
        System.out.println("🎯 ===========================================");

        System.out.println("\n❌ TESTANDO VALIDAÇÕES DE ERRO:");
        System.out.println("------------------------------");

        // Tentar criar com dados vazios
        System.out.println("\n1. Dados vazios:");
        String erro1 = global.createStudent("", "", "", "", -1);
        System.out.println("   Resultado: " + erro1);

        // Tentar criar com login duplicado
        System.out.println("\n2. Login duplicado:");
        String erro2 = global.createStudent("Novo Student", "ana.silva", "pass123", "2023099", 1);
        System.out.println("   Resultado: " + erro2);

        // Tentar criar com student number duplicado
        System.out.println("\n3. Student number duplicado:");
        String erro3 = global.createStudent("Novo Student", "novo.login", "pass123", "2023001", 1);
        System.out.println("   Resultado: " + erro3);

        // Tentar criar teacher com login duplicado
        System.out.println("\n4. Teacher com login duplicado:");
        String erro4 = global.createTeacher("Novo Teacher", "carlos.santos", "pass123");
        System.out.println("   Resultado: " + erro4);

        // Tentar atualizar user que não existe
        System.out.println("\n5. Atualizar user inexistente:");
        String erro5 = global.updateTeacher(999999L, "Nome", "login", "senha");
        System.out.println("   Resultado: " + erro5);

        // Tentar deletar user que não existe
        System.out.println("\n6. Deletar user inexistente:");
        String erro6 = global.deleteUser(999999L);
        System.out.println("   Resultado: " + erro6);
    }

    private static void executarTodosTestes() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 EXECUTANDO TODOS OS TESTES EM SEQUÊNCIA");
        System.out.println("🎯 ===========================================");

        testarCreate();
        pausa();

        testarReadAllUsers();
        pausa();

        testarReadTeachers();
        pausa();

        testarReadStudents();
        pausa();

        testarUpdate();
        pausa();

        testarDelete();
        pausa();

        testarValidacoes();

        System.out.println("\n🎉 ===========================================");
        System.out.println("🎯 TODOS OS TESTES FORAM CONCLUÍDOS!");
        System.out.println("🎉 ===========================================");
    }

    private static void exibirUsersDetalhados(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("❌ Nenhum user encontrado!");
            return;
        }

        for (User user : users) {
            String tipo = user instanceof Teacher ? "TEACHER" : "STUDENT";
            System.out.println("\n   👤 " + user.getName() + " (" + tipo + ")");
            System.out.println("      ID: " + user.getUserId());
            System.out.println("      Login: " + user.getLogin());

            if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println("      Student Number: " + student.getStudentNumber());
                System.out.println("      Semestre: " + student.getCurrentSemester());
            }
        }
    }

    private static void exibirUsersResumido(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("❌ Nenhum user encontrado!");
            return;
        }

        for (User user : users) {
            String tipo = user instanceof Teacher ? "TEACHER" : "STUDENT";
            String infoExtra = "";

            if (user instanceof Student) {
                Student student = (Student) user;
                infoExtra = " | Nº: " + student.getStudentNumber() + " | Semestre: " + student.getCurrentSemester();
            }

            System.out.println("   👉 " + user.getName() + " (" + tipo + ")" + infoExtra);
        }
    }

    private static void pausa() {
        System.out.print("\n⏸️  Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void createCourseTeacher() {
        global.readAllCourseWithJplq();
        if (global.getCourses().isEmpty()) {
            global.createCourse("COURSE_EXAMPLE");
        }
        Course course = global.searchCourse(1);
        global.readAllTeacherWithJplq();
        if (global.getUsers().isEmpty()) {
            global.createTeacher("TEST_TEACHER", "TEST_LOGIN_TEACHER", "TEST_TEACHER_PASSWORD");
        }
        Teacher teacher = (Teacher) global.searchUser(1);
        course.createCourseTeacher(teacher, true);
    }

    private static void testDeleteCourseTeacher() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(1);
        if (course != null) {
            try {
                course.readAllCourseTeacherWithJplq();
                course.deleteCourseTeacher(1);
            } catch (Exception e) {
                System.out.print("\nCould not delete the CT!");
            }
        } else {
            System.out.print("\nCourse not found!");
        }
    }

}


// Test create/update award
//        Global global = new Global();
//
//        global.getUsers().add(student);
//        global.getUsers().add(teacher);
//        global.getTeams().add(team);
//        ArrayList<AwardRule> awardRules =new  ArrayList<>();
//        awardRules.add(awardRule);
//        award.setAwardRules(awardRules);
//        global.getAwards().add(award);
//        global.getStudentsAwards().add(studentAward);
//        ArrayList<Project> projects = new ArrayList<>();
//        projects.add(project);
//        ArrayList<CourseTeacher> courseTeachers = new ArrayList<>();
//        courseTeachers.add(courseTeacher);
//        course.setCourseTeachers(courseTeachers);
//        course.setProjects(projects);
//        global.getCourses().add(course);
//        global.getCourseTeachers().add(courseTeacher);
//
//
//        System.out.println(project.getProjectId());
//
//        System.out.println(global.createAwardRule(30,true,2,1,1));
//        System.out.println(global.updateAwardRule(1,25,true,1));
//
//
//


//package UPT_SQ.EduScrumAwards.model;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.Date;
//
//public class TestMain {
//
//    public static void main(String[] args) {
//        // Setup Hibernate
//        DatabaseHelper dbHelper = new DatabaseHelper();
//        dbHelper.setup();
//        Session session = dbHelper.getSessionFactory().openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//
//            //  Create Course
//            Course course = new Course("Software Quality");
//            session.persist(course); // persist first so Project can reference it
//
//            // Create Team
//            Team team = new Team();
//            team.setTeamName("Demo Team"); // set non-null fields
//            session.persist(team); // persist team first
//
//            // Create Project and assign course & team
//            Project project = new Project();
//            project.setProjectName("EduScrum Demo Project");
//            project.setCourse(course);
//            project.setTeam(team);
//            session.persist(project);
//
//            // Create Sprint and assign to project
//            Date start = new Date();
//            Date end = new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000); // +7 days
//            Sprint sprint = new Sprint(start, end);
//            sprint.setProject(project);
//            session.persist(sprint);
//
//            //Create Goals and assign to Sprint
//            Goal g1 = new Goal("Implement authentication", 8);
//            Goal g2 = new Goal("Write unit tests", 5);
//            Goal g3 = new Goal("Prepare demo", 2);
//
//            g2.completeGoal();
//            g3.setCompleted(true);
//
//            g1.setSprint(sprint); session.persist(g1);
//            g2.setSprint(sprint); session.persist(g2);
//            g3.setSprint(sprint); session.persist(g3);
//
//            tx.commit();
//
//            System.out.println("All entities saved successfully!");
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//            dbHelper.exit();
//        }
//    }
//}
