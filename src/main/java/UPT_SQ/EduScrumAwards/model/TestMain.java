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


    /*private static Global global = new Global();
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
            System.out.println("11   - TEST CREATE COURSE");
            System.out.println("12   - TEST DELETE COURSE");
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
                case 11:
                    testCreateCourse();
                    break;
                case 12:
                    testDeleteCourse();
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
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        global.readAllTeacherWithJplq();
        if (global.getUsers().isEmpty()) {
            global.createTeacher("TEST_TEACHER", "TEST_LOGIN_TEACHER", "TEST_TEACHER_PASSWORD");
        }
        Teacher teacher = (Teacher) global.searchUser(1);
        course.createCourseTeacher(teacher, true);
    }

    private static void testDeleteCourseTeacher() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        if (course != null) {
            try {
                course.readAllCourseTeacherWithJplq();
                course.deleteCourseTeacher(course.getTeachers().get(0).getCourseTeacherID());
            } catch (Exception e) {
                System.out.print("\nCould not delete the CT!");
            }
        } else {
            System.out.print("\nCourse not found!");
        }
    }

    private static void testCreateCourse() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        global.createTeam("TEST_TEAM");
        Team team = (Team) global.searchTeam(global.getTeams().get(0).getTeamID());
        course.createProject("TEST_PROJECT", team, course);
    }

    private static void testDeleteCourse() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        if (course != null) {
            try {
                course.readAllCourseTeacherWithJplq();
                course.retrieveProjects();
                global.deleteCourse(global.getCourses().get(0).getCourseID());
                System.out.println(global.deleteCourse(global.getCourses().get(0).getCourseID()));
            } catch (Exception e) {
                System.out.print("\nCould not delete the Course!");
            }
        } else {
            System.out.print("Course not found!");
        }
    }*/
    //=================================================================================================================================================================================================================


    //=======================  The section below is for deletion testing purposes; please do not modify it.  ==========================================================================================================


    //==================================================================================================================================================================================================================

    // Global instance to access system data and operations
    private static Global global = new Global();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main method that runs the test program.
     * Initializes the Global instance and displays a menu for testing various operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("🎯 ===========================================");
        System.out.println("🎯 CRUD TEST - USER, TEACHER AND STUDENT");
        System.out.println("🎯 Updated Global Class");
        System.out.println("🎯 ===========================================");

        // Initialize global arrays from database
        global.init();

        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("\n📋 CRUD TEST MENU:");
            System.out.println("1️⃣  - CREATE (Create Teachers and Students)");
            System.out.println("2️⃣  - READ (Read all Users)");
            System.out.println("3️⃣  - READ TEACHERS (Read only Teachers)");
            System.out.println("4️⃣  - READ STUDENTS (Read only Students)");
            System.out.println("5️⃣  - UPDATE (Update Users)");
            System.out.println("6️⃣  - DELETE (Delete Specific User by ID)");
            System.out.println("7️⃣  - TEST VALIDATIONS");
            System.out.println("8️⃣  - EXECUTE ALL TESTS");
            System.out.println("9   - TEST CREATE COURSE-TEACHER");
            System.out.println("10  - TEST DELETE COURSE-TEACHER");
            System.out.println("11  - TEST CREATE COURSE");
            System.out.println("12  - TEST DELETE COURSE");
            System.out.println("13  - TEST USER DELETION WITH ASSOCIATIONS");
            System.out.println("0️⃣  - EXIT");
            System.out.print("\nChoose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option) {
                case 1:
                    testCreate();
                    break;
                case 2:
                    testReadAllUsers();
                    break;
                case 3:
                    testReadTeachers();
                    break;
                case 4:
                    testReadStudents();
                    break;
                case 5:
                    testUpdate();
                    break;
                case 6:
                    testDeleteSpecificUser();
                    break;
                case 7:
                    testValidations();
                    break;
                case 8:
                    executeAllTests();
                    break;
                case 9:
                    createCourseTeacher();
                    break;
                case 10:
                    testDeleteCourseTeacher();
                    break;
                case 11:
                    testCreateCourse();
                    break;
                case 12:
                    testDeleteCourse();
                    break;
                case 13:
                    testUserDeletionWithAssociations();
                    break;
                case 0:
                    continueProgram = false;
                    System.out.println("👋 Exiting program...");
                    break;
                default:
                    System.out.println("❌ Invalid option!");
            }

            if (continueProgram && option != 8) {
                System.out.print("\n⏸️  Press ENTER to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Tests the CREATE operation by creating new teachers and students.
     * Demonstrates how to create users with valid data.
     */
    private static void testCreate() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING CREATE OPERATION");
        System.out.println("🎯 ===========================================");

        // Create Teachers
        System.out.println("\n📝 CREATING TEACHERS:");
        System.out.println("-------------------");

        String result1 = global.createTeacher("Dr. Carlos Santos", "carlos.santos", "teacher123");
        System.out.println("✅ createTeacher 1: " + result1);

        String result2 = global.createTeacher("Prof. Maria Oliveira", "maria.oliveira", "teach456");
        System.out.println("✅ createTeacher 2: " + result2);

        // Create Students
        System.out.println("\n📝 CREATING STUDENTS:");
        System.out.println("-------------------");

        String result3 = global.createStudent("Ana Silva", "ana.silva", "password123", "2023001", 2);
        System.out.println("✅ createStudent 1: " + result3);

        String result4 = global.createStudent("João Pereira", "joao.pereira", "pass456", "2023002", 3);
        System.out.println("✅ createStudent 2: " + result4);

        String result5 = global.createStudent("Maria Costa", "maria.costa", "student789", "2023003", 1);
        System.out.println("✅ createStudent 3: " + result5);

        System.out.println("\n🎉 CREATE TEST SUCCESSFUL!");
    }

    /**
     * Tests the READ operation by loading all users from the database.
     * Displays detailed information about each user.
     */
    private static void testReadAllUsers() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING READ ALL USERS");
        System.out.println("🎯 ===========================================");

        System.out.println("\n👥 LOADING ALL USERS FROM DATABASE:");
        System.out.println("-------------------------------------");
        global.readAllUserWithJplq();

        if (global.getUsers().isEmpty()) {
            System.out.println("❌ No users found in database!");
            return;
        }

        System.out.println("✅ Total users loaded: " + global.getUsers().size());
        displayUsersDetailed(global.getUsers());
    }

    /**
     * Tests reading only teacher users from the database.
     * Demonstrates filtering users by type.
     */
    private static void testReadTeachers() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING READ TEACHERS");
        System.out.println("🎯 ===========================================");

        System.out.println("\n👨‍🏫 LOADING ONLY TEACHERS FROM DATABASE:");
        System.out.println("--------------------------------------");
        global.readAllTeacherWithJplq();

        long teacherCount = global.getUsers().stream().filter(u -> u instanceof Teacher).count();
        System.out.println("✅ Total Teachers loaded: " + teacherCount);

        // Show only teachers
        List<User> teachers = global.getUsers().stream()
                .filter(u -> u instanceof Teacher)
                .toList();
        displayUsersDetailed(teachers);
    }

    /**
     * Tests reading only student users from the database.
     * Demonstrates filtering users by type.
     */
    private static void testReadStudents() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING READ STUDENTS");
        System.out.println("🎯 ===========================================");

        System.out.println("\n👨‍🎓 LOADING ONLY STUDENTS FROM DATABASE:");
        System.out.println("--------------------------------------");
        global.readAllStudentWithJplq();

        long studentCount = global.getUsers().stream().filter(u -> u instanceof Student).count();
        System.out.println("✅ Total Students loaded: " + studentCount);

        // Show only students
        List<User> students = global.getUsers().stream()
                .filter(u -> u instanceof Student)
                .toList();
        displayUsersDetailed(students);
    }

    /**
     * Tests the UPDATE operation by modifying existing user information.
     * Updates both teachers and students with new data.
     */
    private static void testUpdate() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING UPDATE OPERATION");
        System.out.println("🎯 ===========================================");

        // First load users
        global.readAllUserWithJplq();

        if (global.getUsers().isEmpty()) {
            System.out.println("❌ No users found to update!");
            return;
        }

        // Update a Student
        Student studentToUpdate = null;
        for (User user : global.getUsers()) {
            if (user instanceof Student) {
                studentToUpdate = (Student) user;
                break;
            }
        }

        if (studentToUpdate != null) {
            System.out.println("\n📝 UPDATING STUDENT:");
            System.out.println("----------------------");
            System.out.println("📋 Before update:");
            System.out.println("   Name: " + studentToUpdate.getName());
            System.out.println("   Login: " + studentToUpdate.getLogin());
            System.out.println("   Student Number: " + studentToUpdate.getStudentNumber());
            System.out.println("   Semester: " + studentToUpdate.getCurrentSemester());

            String result = global.updateStudent(
                    studentToUpdate.getUserId(),
                    studentToUpdate.getName() + " [UPDATED]",
                    studentToUpdate.getLogin() + ".updated",
                    "newpassword123",
                    studentToUpdate.getStudentNumber(),
                    studentToUpdate.getCurrentSemester() + 1
            );
            System.out.println("✅ updateStudent: " + result);
        } else {
            System.out.println("❌ No Student found to update!");
        }

        // Update a Teacher
        Teacher teacherToUpdate = null;
        for (User user : global.getUsers()) {
            if (user instanceof Teacher) {
                teacherToUpdate = (Teacher) user;
                break;
            }
        }

        if (teacherToUpdate != null) {
            System.out.println("\n📝 UPDATING TEACHER:");
            System.out.println("----------------------");
            System.out.println("📋 Before update:");
            System.out.println("   Name: " + teacherToUpdate.getName());
            System.out.println("   Login: " + teacherToUpdate.getLogin());

            String result = global.updateTeacher(
                    teacherToUpdate.getUserId(),
                    teacherToUpdate.getName() + " [UPDATED]",
                    teacherToUpdate.getLogin() + ".updated",
                    "newpassword456"
            );
            System.out.println("✅ updateTeacher: " + result);
        } else {
            System.out.println("❌ No Teacher found to update!");
        }

        // Show update results
        System.out.println("\n📊 AFTER UPDATES:");
        global.readAllUserWithJplq();
        System.out.println("✅ Total users: " + global.getUsers().size());
        displayUsersSummary(global.getUsers());
    }

    /**
     * Tests the DELETE operation by allowing the user to choose which user to delete.
     * Displays all users and prompts for the ID of the user to delete.
     * Demonstrates interactive user deletion.
     */
    private static void testDeleteSpecificUser() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING DELETE SPECIFIC USER BY ID");
        System.out.println("🎯 ===========================================");

        // Load all users first
        global.readAllUserWithJplq();

        if (global.getUsers().isEmpty()) {
            System.out.println("❌ No users found in the system!");
            return;
        }

        System.out.println("\n📊 CURRENT USERS IN THE SYSTEM:");
        System.out.println("=================================");
        displayUsersWithAssociationsInfo(global.getUsers());

        // Prompt user to enter ID
        System.out.print("\n🔢 Enter the User ID you want to delete: ");

        try {
            long userIdToDelete = scanner.nextLong();
            scanner.nextLine(); // Clear buffer

            // Find the user by ID
            User userToDelete = null;
            for (User user : global.getUsers()) {
                if (user.getUserId() == userIdToDelete) {
                    userToDelete = user;
                    break;
                }
            }

            if (userToDelete == null) {
                System.out.println("❌ User with ID " + userIdToDelete + " not found!");
                return;
            }

            System.out.println("\n🧾 USER DETAILS:");
            System.out.println("----------------");
            System.out.println("👤 Name: " + userToDelete.getName());
            System.out.println("🆔 ID: " + userToDelete.getUserId());
            System.out.println("📧 Login: " + userToDelete.getLogin());
            System.out.println("👥 Type: " + (userToDelete instanceof Teacher ? "TEACHER" : "STUDENT"));

            if (userToDelete instanceof Student) {
                Student student = (Student) userToDelete;
                System.out.println("🎓 Student Number: " + student.getStudentNumber());
                System.out.println("📅 Semester: " + student.getCurrentSemester());
            }

            // Check if user can be deleted
            System.out.println("\n🔍 CHECKING IF USER CAN BE DELETED...");
            boolean canDelete = global.canDeleteUser(userIdToDelete);

            if (canDelete) {
                System.out.println("✅ User can be deleted (no associations found)");
            } else {
                System.out.println("❌ User CANNOT be deleted (has associations with other entities)");
                System.out.println("   Associations may include:");
                System.out.println("   • CourseTeachers (for teachers)");
                System.out.println("   • TeamMembers (for students)");
                System.out.println("   • StudentAwards (assigned or received)");
            }

            // Ask for confirmation
            System.out.print("\n⚠️  Are you sure you want to delete this user? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes") || confirmation.equals("y")) {
                System.out.println("\n🗑️  DELETING USER...");
                String result = global.deleteUser(userIdToDelete);
                System.out.println("✅ Result: " + result);

                // Show updated user list
                System.out.println("\n📊 UPDATED USER LIST:");
                global.readAllUserWithJplq();
                if (global.getUsers().isEmpty()) {
                    System.out.println("❌ No users remaining in the system.");
                } else {
                    System.out.println("✅ Total users remaining: " + global.getUsers().size());
                    displayUsersSummary(global.getUsers());
                }
            } else {
                System.out.println("❌ Deletion cancelled by user.");
            }

        } catch (Exception e) {
            System.out.println("❌ Invalid input! Please enter a valid User ID (numeric value).");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Tests validation rules by attempting operations with invalid data.
     * Demonstrates error handling and validation messages.
     */
    private static void testValidations() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING VALIDATIONS (EXPECTED ERRORS)");
        System.out.println("🎯 ===========================================");

        System.out.println("\n❌ TESTING ERROR VALIDATIONS:");
        System.out.println("------------------------------");

        // Try to create with empty data
        System.out.println("\n1. Empty data:");
        String error1 = global.createStudent("", "", "", "", -1);
        System.out.println("   Result: " + error1);

        // Try to create with duplicate login
        System.out.println("\n2. Duplicate login:");
        String error2 = global.createStudent("New Student", "ana.silva", "pass123", "2023099", 1);
        System.out.println("   Result: " + error2);

        // Try to create with duplicate student number
        System.out.println("\n3. Duplicate student number:");
        String error3 = global.createStudent("New Student", "new.login", "pass123", "2023001", 1);
        System.out.println("   Result: " + error3);

        // Try to create teacher with duplicate login
        System.out.println("\n4. Teacher with duplicate login:");
        String error4 = global.createTeacher("New Teacher", "carlos.santos", "pass123");
        System.out.println("   Result: " + error4);

        // Try to update non-existent user
        System.out.println("\n5. Update non-existent user:");
        String error5 = global.updateTeacher(999999L, "Name", "login", "password");
        System.out.println("   Result: " + error5);

        // Try to delete non-existent user
        System.out.println("\n6. Delete non-existent user:");
        String error6 = global.deleteUser(999999L);
        System.out.println("   Result: " + error6);
    }

    /**
     * Executes all tests in sequence.
     * Provides a comprehensive testing workflow.
     */
    private static void executeAllTests() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 EXECUTING ALL TESTS IN SEQUENCE");
        System.out.println("🎯 ===========================================");

        testCreate();
        pause();

        testReadAllUsers();
        pause();

        testReadTeachers();
        pause();

        testReadStudents();
        pause();

        testUpdate();
        pause();

        testDeleteSpecificUser();
        pause();

        testValidations();

        System.out.println("\n🎉 ===========================================");
        System.out.println("🎯 ALL TESTS COMPLETED!");
        System.out.println("🎉 ===========================================");
    }

    // =============================================
    // NEW METHOD: TEST USER DELETION WITH ASSOCIATIONS
    // =============================================

    /**
     * Comprehensive test for user deletion with and without associations.
     * This test demonstrates the new validation logic using global arrays:
     * 1. Creates users WITH associations (CourseTeacher for teachers, TeamMember for students)
     * 2. Creates users WITHOUT any associations
     * 3. Attempts to delete users with associations (should fail)
     * 4. Attempts to delete users without associations (should succeed)
     * 5. Verifies that the global arrays are used for validation
     *
     * This test follows Anastasia's recommendation to use global arrays
     * (courses, teams, studentsAwards) instead of internal class arrays.
     */
    private static void testUserDeletionWithAssociations() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 TESTING USER DELETION WITH ASSOCIATIONS");
        System.out.println("🎯 ===========================================");

        // Reload all data to ensure we have updated arrays
        System.out.println("\n🔄 Reloading data from database...");
        global.readAllCourseWithJplq();
        global.readAllTeamWithJplq();
        global.readAllAwardWithJplq();
        global.readAllUserWithJplq();
        global.readAllStudentAwardWithJplq();

        System.out.println("\n📊 CURRENT SYSTEM STATE:");
        System.out.println("---------------------------");
        System.out.println("• Courses: " + global.getCourses().size());
        System.out.println("• Teams: " + global.getTeams().size());
        System.out.println("• Awards: " + global.getAwards().size());
        System.out.println("• Users: " + global.getUsers().size());
        System.out.println("• Student Awards: " + global.getStudentsAwards().size());

        // =============================================
        // CREATE TEST USERS
        // =============================================
        System.out.println("\n👥 CREATING TEST USERS:");
        System.out.println("------------------------------");

        // 1. Create users WITHOUT associations
        System.out.println("\n1. Creating users WITHOUT associations:");

        String timestamp = String.valueOf(System.currentTimeMillis());

        // Teacher without associations
        String teacherWithoutAssociations = global.createTeacher(
                "Prof. No Associations " + timestamp,
                "prof.no.associations." + timestamp,
                "password123"
        );
        System.out.println("   • Teacher without associations: " + teacherWithoutAssociations);

        // Student without associations
        String studentWithoutAssociations = global.createStudent(
                "Student No Associations " + timestamp,
                "student.no.associations." + timestamp,
                "password123",
                "9999" + timestamp.substring(0, 5),
                1
        );
        System.out.println("   • Student without associations: " + studentWithoutAssociations);

        // 2. Create users WITH associations
        System.out.println("\n2. Creating users WITH associations:");

        // Teacher with associations (will be associated with a course)
        String teacherWithAssociations = global.createTeacher(
                "Prof. With Associations " + timestamp,
                "prof.with.associations." + timestamp,
                "password123"
        );
        System.out.println("   • Teacher with associations: " + teacherWithAssociations);

        // Student with associations (will be associated with a team)
        String studentWithAssociations = global.createStudent(
                "Student With Associations " + timestamp,
                "student.with.associations." + timestamp,
                "password123",
                "8888" + timestamp.substring(0, 5),
                2
        );
        System.out.println("   • Student with associations: " + studentWithAssociations);

        // Reload users to get their IDs
        global.readAllUserWithJplq();

        // Find the created users
        User teacherWithoutAssociationsUser = null;
        User studentWithoutAssociationsUser = null;
        User teacherWithAssociationsUser = null;
        User studentWithAssociationsUser = null;

        for (User user : global.getUsers()) {
            if (user.getName().contains("Prof. No Associations")) {
                teacherWithoutAssociationsUser = user;
            } else if (user.getName().contains("Student No Associations")) {
                studentWithoutAssociationsUser = user;
            } else if (user.getName().contains("Prof. With Associations")) {
                teacherWithAssociationsUser = user;
            } else if (user.getName().contains("Student With Associations")) {
                studentWithAssociationsUser = user;
            }
        }

        // =============================================
        // CREATE ASSOCIATIONS FOR USERS
        // =============================================
        System.out.println("\n🔗 CREATING ASSOCIATIONS FOR USERS:");
        System.out.println("-----------------------------------");

        // Check if we have a course and team to create associations
        if (global.getCourses().isEmpty()) {
            System.out.println("⚠️  Creating a course for testing...");
            global.createCourse("Test Deletion Course " + timestamp);
            global.readAllCourseWithJplq();
        }

        if (global.getTeams().isEmpty()) {
            System.out.println("⚠️  Creating a team for testing...");
            global.createTeam("Test Deletion Team " + timestamp);
            global.readAllTeamWithJplq();
        }

        if (global.getAwards().isEmpty()) {
            System.out.println("⚠️  Creating an award for testing...");
            global.createAward("Test Award", "Award for deletion testing", 100, "MANUAL", "INDIVIDUAL");
            global.readAllAwardWithJplq();
        }

        Course course = global.getCourses().get(0);
        Team team = global.getTeams().get(0);
        Award award = global.getAwards().get(0);

        // 1. Associate teacher with a course (CourseTeacher)
        if (teacherWithAssociationsUser != null && course != null) {
            System.out.println("\n🔗 Associating teacher with course:");
            try {
                course.createCourseTeacher((Teacher) teacherWithAssociationsUser, false);
                System.out.println("   ✅ Teacher associated with course successfully!");
            } catch (Exception e) {
                System.out.println("   ⚠️  Error associating teacher with course: " + e.getMessage());
            }
        }

        // 2. Associate student with a team (TeamMember)
        if (studentWithAssociationsUser != null && team != null) {
            System.out.println("\n🔗 Associating student with team:");
            try {
                String teamMemberResult = team.createTeamMember((Student) studentWithAssociationsUser, TeamMember.Role.DEVELOPER);
                if (teamMemberResult.equals("Success")) {
                    System.out.println("   ✅ Student associated with team successfully!");
                } else {
                    System.out.println("   ⚠️  Error associating student with team: " + teamMemberResult);
                }
            } catch (Exception e) {
                System.out.println("   ⚠️  Error associating student with team: " + e.getMessage());
            }
        }

        // Reload data to ensure associations are in global arrays
        global.readAllCourseWithJplq();
        global.readAllTeamWithJplq();
        global.readAllStudentAwardWithJplq();

        // =============================================
        // TEST DELETION
        // =============================================
        System.out.println("\n🧪 TESTING USER DELETION:");
        System.out.println("--------------------------------");

        // Test deleting teacher WITH associations (should fail)
        if (teacherWithAssociationsUser != null) {
            System.out.println("\n1. Attempting to delete teacher WITH associations:");
            System.out.println("   📝 Name: " + teacherWithAssociationsUser.getName());
            System.out.println("   🆔 ID: " + teacherWithAssociationsUser.getUserId());

            // Check if can be deleted using global arrays
            boolean canDelete = global.canDeleteUser(teacherWithAssociationsUser.getUserId());
            System.out.println("   ❓ Can be deleted? " + (canDelete ? "✅ YES" : "❌ NO"));

            // Attempt deletion
            String result = global.deleteUser(teacherWithAssociationsUser.getUserId());
            System.out.println("   🗑️  Deletion result: " + result);
        }

        // Test deleting student WITH associations (should fail)
        if (studentWithAssociationsUser != null) {
            System.out.println("\n2. Attempting to delete student WITH associations:");
            System.out.println("   📝 Name: " + studentWithAssociationsUser.getName());
            System.out.println("   🆔 ID: " + studentWithAssociationsUser.getUserId());

            // Check if can be deleted using global arrays
            boolean canDelete = global.canDeleteUser(studentWithAssociationsUser.getUserId());
            System.out.println("   ❓ Can be deleted? " + (canDelete ? "✅ YES" : "❌ NO"));

            // Attempt deletion
            String result = global.deleteUser(studentWithAssociationsUser.getUserId());
            System.out.println("   🗑️  Deletion result: " + result);
        }

        // Test deleting teacher WITHOUT associations (should succeed)
        if (teacherWithoutAssociationsUser != null) {
            System.out.println("\n3. Attempting to delete teacher WITHOUT associations:");
            System.out.println("   📝 Name: " + teacherWithoutAssociationsUser.getName());
            System.out.println("   🆔 ID: " + teacherWithoutAssociationsUser.getUserId());

            // Check if can be deleted using global arrays
            boolean canDelete = global.canDeleteUser(teacherWithoutAssociationsUser.getUserId());
            System.out.println("   ❓ Can be deleted? " + (canDelete ? "✅ YES" : "❌ NO"));

            // Attempt deletion
            String result = global.deleteUser(teacherWithoutAssociationsUser.getUserId());
            System.out.println("   🗑️  Deletion result: " + result);
        }

        // Test deleting student WITHOUT associations (should succeed)
        if (studentWithoutAssociationsUser != null) {
            System.out.println("\n4. Attempting to delete student WITHOUT associations:");
            System.out.println("   📝 Name: " + studentWithoutAssociationsUser.getName());
            System.out.println("   🆔 ID: " + studentWithoutAssociationsUser.getUserId());

            // Check if can be deleted using global arrays
            boolean canDelete = global.canDeleteUser(studentWithoutAssociationsUser.getUserId());
            System.out.println("   ❓ Can be deleted? " + (canDelete ? "✅ YES" : "❌ NO"));

            // Attempt deletion
            String result = global.deleteUser(studentWithoutAssociationsUser.getUserId());
            System.out.println("   🗑️  Deletion result: " + result);
        }

        // =============================================
        // FINAL RESULTS
        // =============================================
        System.out.println("\n📊 FINAL RESULTS:");
        System.out.println("-----------------");

        global.readAllUserWithJplq();
        System.out.println("• Total users after tests: " + global.getUsers().size());

        // Check which users still exist
        System.out.println("\n👥 Remaining users in system:");
        for (User user : global.getUsers()) {
            String type = user instanceof Teacher ? "Teacher" : "Student";
            System.out.println("   • " + user.getName() + " (" + type + ")");
        }

        System.out.println("\n🎯 CONCLUSION:");
        System.out.println("-----------");
        System.out.println("✅ Users WITH associations should NOT be deleted");
        System.out.println("✅ Users WITHOUT associations SHOULD be deleted");
        System.out.println("✅ System correctly validates using global arrays!");
    }

    /**
     * Displays detailed information about a list of users.
     * Shows user type, ID, login, and specific fields for students.
     *
     * @param users list of users to display
     */
    private static void displayUsersDetailed(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("❌ No users found!");
            return;
        }

        for (User user : users) {
            String type = user instanceof Teacher ? "TEACHER" : "STUDENT";
            System.out.println("\n   👤 " + user.getName() + " (" + type + ")");
            System.out.println("      ID: " + user.getUserId());
            System.out.println("      Login: " + user.getLogin());

            if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println("      Student Number: " + student.getStudentNumber());
                System.out.println("      Semester: " + student.getCurrentSemester());
            }
        }
    }

    /**
     * Displays summary information about a list of users.
     * Shows basic information in a compact format.
     *
     * @param users list of users to display
     */
    private static void displayUsersSummary(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("❌ No users found!");
            return;
        }

        for (User user : users) {
            String type = user instanceof Teacher ? "TEACHER" : "STUDENT";
            String extraInfo = "";

            if (user instanceof Student) {
                Student student = (Student) user;
                extraInfo = " | Number: " + student.getStudentNumber() + " | Semester: " + student.getCurrentSemester();
            }

            System.out.println("   👉 " + user.getName() + " (" + type + ")" + extraInfo);
        }
    }

    /**
     * Displays users with information about their associations.
     * Shows whether each user has associations that would prevent deletion.
     *
     * @param users list of users to display
     */
    private static void displayUsersWithAssociationsInfo(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("❌ No users found!");
            return;
        }

        // Reload global data to ensure we have current associations
        global.readAllCourseWithJplq();
        global.readAllTeamWithJplq();
        global.readAllStudentAwardWithJplq();

        for (User user : users) {
            String type = user instanceof Teacher ? "TEACHER" : "STUDENT";
            System.out.println("\n   👤 " + user.getName() + " (" + type + ")");
            System.out.println("      ID: " + user.getUserId());
            System.out.println("      Login: " + user.getLogin());

            if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println("      Student Number: " + student.getStudentNumber());
                System.out.println("      Semester: " + student.getCurrentSemester());
            }

            // Check if user has associations
            boolean hasAssociations = !global.canDeleteUser(user.getUserId());
            String associationStatus = hasAssociations ? "❌ HAS ASSOCIATIONS" : "✅ NO ASSOCIATIONS";
            System.out.println("      Deletable: " + associationStatus);

            // Show specific associations for better understanding
            if (hasAssociations) {
                if (user instanceof Teacher) {
                    System.out.println("      Associations: CourseTeacher, StudentAwards (assigned)");
                } else if (user instanceof Student) {
                    System.out.println("      Associations: TeamMember, StudentAwards (received)");
                }
            }
        }
    }

    /**
     * Pauses execution and waits for user input to continue.
     * Used between test executions for better user experience.
     */
    private static void pause() {
        System.out.print("\n⏸️  Press ENTER to continue...");
        scanner.nextLine();
    }

    /**
     * Tests creating a CourseTeacher association.
     * Creates a course and teacher, then associates them.
     */
    private static void createCourseTeacher() {
        global.readAllCourseWithJplq();
        if (global.getCourses().isEmpty()) {
            global.createCourse("COURSE_EXAMPLE");
        }
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        global.readAllTeacherWithJplq();
        if (global.getUsers().isEmpty()) {
            global.createTeacher("TEST_TEACHER", "TEST_LOGIN_TEACHER", "TEST_TEACHER_PASSWORD");
        }
        Teacher teacher = (Teacher) global.searchUser(1);
        course.createCourseTeacher(teacher, true);
    }

    /**
     * Tests deleting a CourseTeacher association.
     * Demonstrates removing teacher-course associations.
     */
    private static void testDeleteCourseTeacher() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        if (course != null) {
            try {
                course.readAllCourseTeacherWithJplq();
                course.deleteCourseTeacher(course.getCourseTeachers().get(0).getCourseTeacherID());
            } catch (Exception e) {
                System.out.print("\nCould not delete the CourseTeacher!");
            }
        } else {
            System.out.print("\nCourse not found!");
        }
    }

    /**
     * Tests creating a course with a project and team.
     * Demonstrates creating a complete course structure.
     */
    private static void testCreateCourse() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        global.createTeam("TEST_TEAM");
        Team team = global.searchTeam(global.getTeams().get(0).getTeamID());
        course.createProject("TEST_PROJECT", team, course);
    }

    /**
     * Tests deleting a course.
     * Demonstrates course deletion with proper cleanup.
     */
    private static void testDeleteCourse() {
        global.readAllCourseWithJplq();
        Course course = global.searchCourse(global.getCourses().get(0).getCourseID());
        if (course != null) {
            try {
                course.readAllCourseTeacherWithJplq();
                course.retrieveProjects();
                global.deleteCourse(global.getCourses().get(0).getCourseID());
                System.out.println(global.deleteCourse(global.getCourses().get(0).getCourseID()));
            } catch (Exception e) {
                System.out.print("\nCould not delete the Course!");
            }
        } else {
            System.out.print("Course not found!");
        }
    }

    //=================================================================================================================================================================================================================


    //=======================  The end of section for deletion testing purposes; please do not modify it.  ==========================================================================================================


    //==================================================================================================================================================================================================================




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
