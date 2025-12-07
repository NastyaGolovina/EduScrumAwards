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
    }
    //=================================================================================================================================================================================================================


    //=======================  The section below is for deletion testing purposes; please do not modify it.  ==========================================================================================================


    //==================================================================================================================================================================================================================
    /*
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
            System.out.println("10  - TEST DELETE COURSE-TEACHER");
            System.out.println("11  - TEST CREATE COURSE");
            System.out.println("12  - TEST DELETE COURSE");
            System.out.println("13  - CRIAR USUÁRIOS TESTE (COM E SEM VÍNCULOS)");
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
                case 13:
                    criarUsuariosTesteParaDelecao();
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

        System.out.println("\n📊 LISTA DE USUÁRIOS DISPONÍVEIS:");
        System.out.println("--------------------------------");

        // Mostrar apenas usuários que podem ser deletados primeiro
        System.out.println("\n✅ USUÁRIOS QUE PODEM SER DELETADOS (sem vínculos):");
        boolean encontrouDeletavel = false;

        for (User user : global.getUsers()) {
            if (global.canDeleteUser(user.getUserId())) {
                System.out.println("   👤 " + user.getName() +
                        " (ID: " + user.getUserId() +
                        ", Tipo: " + (user instanceof Teacher ? "TEACHER" : "STUDENT") + ")");
                encontrouDeletavel = true;
            }
        }

        if (!encontrouDeletavel) {
            System.out.println("   Nenhum usuário pode ser deletado (todos têm vínculos)");
        }

        System.out.println("\n❌ USUÁRIOS QUE NÃO PODEM SER DELETADOS (com vínculos):");
        for (User user : global.getUsers()) {
            if (!global.canDeleteUser(user.getUserId())) {
                System.out.println("   👤 " + user.getName() +
                        " (ID: " + user.getUserId() +
                        ", Tipo: " + (user instanceof Teacher ? "TEACHER" : "STUDENT") + ")");
            }
        }

        System.out.print("\n🔢 Digite o ID do usuário que deseja deletar (ou 0 para cancelar): ");
        long userId = scanner.nextLong();
        scanner.nextLine(); // Limpar buffer

        if (userId == 0) {
            System.out.println("✅ Operação cancelada.");
            return;
        }

        User user = global.searchUser(userId);
        if (user == null) {
            System.out.println("❌ Usuário com ID " + userId + " não encontrado!");
            return;
        }

        System.out.println("\n🗑️  USUÁRIO SELECIONADO:");
        System.out.println("   Nome: " + user.getName());
        System.out.println("   Tipo: " + (user instanceof Teacher ? "TEACHER" : "STUDENT"));
        System.out.println("   Login: " + user.getLogin());

        // Verificar se pode deletar
        System.out.println("\n🔍 VERIFICANDO VÍNCULOS...");
        boolean podeDeletar = global.canDeleteUser(userId);

        if (!podeDeletar) {
            System.out.println("❌ Este usuário NÃO pode ser deletado!");
            System.out.println("   Motivo: Está vinculado a CourseTeachers, TeamMembers ou StudentAwards");
            System.out.println("\n💡 Use a opção 13 para criar usuários SEM vínculos para testar.");
            return;
        }

        System.out.println("✅ Este usuário pode ser deletado (não possui vínculos)");

        System.out.print("\n⚠️  Confirma exclusão do usuário " + user.getName() + "? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            String resultado = global.deleteUser(userId);
            System.out.println("\n📋 Resultado: " + resultado);

            // Atualizar lista
            global.readAllUserWithJplq();

            if (resultado.equals("Success")) {
                System.out.println("🎉 Usuário deletado com sucesso do banco de dados e da memória!");
            }
        } else {
            System.out.println("✅ Operação cancelada pelo usuário.");
        }
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
    }

    *//**
     * Cria automaticamente 4 usuários de teste:
     * 1. Teacher SEM vínculos (pode deletar)
     * 2. Teacher COM vínculos (não pode deletar)
     * 3. Student SEM vínculos (pode deletar)
     * 4. Student COM vínculos (não pode deletar)
     *//*
    private static void criarUsuariosTesteParaDelecao() {
        System.out.println("\n🎯 ===========================================");
        System.out.println("🎯 CRIANDO USUÁRIOS DE TESTE PARA DELEÇÃO");
        System.out.println("🎯 ===========================================");

        System.out.println("📝 Criando dados de suporte (curso, time, award)...");

        // 1. Criar um curso para usar nos vínculos
        String cursoResult = global.createCourse("Curso Teste Deleção - " + System.currentTimeMillis());
        System.out.println("   Curso: " + cursoResult);

        // 2. Criar um time para usar nos vínculos
        String timeResult = global.createTeam("Time Teste Deleção - " + System.currentTimeMillis());
        System.out.println("   Time: " + timeResult);

        // 3. Criar um award para usar nos vínculos
        String awardResult = global.createAward(
                "Award Teste Deleção",
                "Prêmio para teste de deleção",
                100,
                "MANUAL",
                "INDIVIDUAL"
        );
        System.out.println("   Award: " + awardResult);

        // 4. Atualizar listas para ter os objetos carregados
        global.readAllCourseWithJplq();
        global.readAllTeamWithJplq();
        global.readAllAwardWithJplq();
        global.readAllUserWithJplq();

        // Pegar os objetos criados
        Course curso = global.getCourses().get(global.getCourses().size() - 1);
        Team time = global.getTeams().get(global.getTeams().size() - 1);
        Award award = global.getAwards().get(global.getAwards().size() - 1);

        System.out.println("\n✅ Dados de suporte criados:");
        System.out.println("   - Curso: " + curso.getCourseName() + " (ID: " + curso.getCourseID() + ")");
        System.out.println("   - Time: " + time.getTeamName() + " (ID: " + time.getTeamID() + ")");
        System.out.println("   - Award: " + award.getAwardName() + " (ID: " + award.getAwardID() + ")");

        System.out.println("\n👥 CRIANDO USUÁRIOS:");
        System.out.println("====================");

        // Timestamp para evitar conflitos
        String timestamp = String.valueOf(System.currentTimeMillis());

        // =========================================================================
        // 1. TEACHER SEM VÍNCULOS (PODE DELETAR)
        // =========================================================================
        System.out.println("\n1️⃣  TEACHER SEM VÍNCULOS:");
        System.out.println("   -------------------");

        String teacherSemVinculosNome = "Prof. Sem Vínculos " + timestamp;
        String teacherSemVinculosLogin = "teacher_sem_vinculos_" + timestamp;

        String resultadoTeacherSemVinculos = global.createTeacher(
                teacherSemVinculosNome,
                teacherSemVinculosLogin,
                "senha123"
        );

        System.out.println("   Resultado: " + resultadoTeacherSemVinculos);

        if (resultadoTeacherSemVinculos.equals("Success")) {
            // Buscar o teacher recém-criado
            global.readAllUserWithJplq();
            Teacher teacherSemVinculos = null;
            for (User user : global.getUsers()) {
                if (user instanceof Teacher && user.getLogin().equals(teacherSemVinculosLogin)) {
                    teacherSemVinculos = (Teacher) user;
                    break;
                }
            }

            if (teacherSemVinculos != null) {
                System.out.println("   ✅ Criado: " + teacherSemVinculosNome);
                System.out.println("      ID: " + teacherSemVinculos.getUserId());
                System.out.println("      Login: " + teacherSemVinculos.getLogin());
                System.out.println("      Vínculos: NENHUM (pode deletar)");
            }
        }

        // =========================================================================
        // 2. TEACHER COM VÍNCULOS (NÃO PODE DELETAR)
        // =========================================================================
        System.out.println("\n2️⃣  TEACHER COM VÍNCULOS:");
        System.out.println("   -------------------");

        String teacherComVinculosNome = "Prof. Com Vínculos " + timestamp;
        String teacherComVinculosLogin = "teacher_com_vinculos_" + timestamp;

        String resultadoTeacherComVinculos = global.createTeacher(
                teacherComVinculosNome,
                teacherComVinculosLogin,
                "senha123"
        );

        System.out.println("   Resultado: " + resultadoTeacherComVinculos);

        if (resultadoTeacherComVinculos.equals("Success")) {
            // Buscar o teacher recém-criado
            global.readAllUserWithJplq();
            Teacher teacherComVinculos = null;
            for (User user : global.getUsers()) {
                if (user instanceof Teacher && user.getLogin().equals(teacherComVinculosLogin)) {
                    teacherComVinculos = (Teacher) user;
                    break;
                }
            }

            if (teacherComVinculos != null) {
                System.out.println("   ✅ Criado: " + teacherComVinculosNome);
                System.out.println("      ID: " + teacherComVinculos.getUserId());
                System.out.println("      Login: " + teacherComVinculos.getLogin());

                // CRIAR VÍNCULO: Associar teacher ao curso
                String vinculoCursoResult = curso.createCourseTeacher(teacherComVinculos, true);
                System.out.println("      Vínculo com curso: " + vinculoCursoResult);

                // Atualizar dados do teacher
                teacherComVinculos.readAllCourseTeacherWithJplq();

                System.out.println("      Status: TEM vínculos (NÃO pode deletar)");
            }
        }

        // =========================================================================
        // 3. STUDENT SEM VÍNCULOS (PODE DELETAR)
        // =========================================================================
        System.out.println("\n3️⃣  STUDENT SEM VÍNCULOS:");
        System.out.println("   -------------------");

        String studentSemVinculosNome = "Estudante Sem Vínculos " + timestamp;
        String studentSemVinculosLogin = "student_sem_vinculos_" + timestamp;
        String studentSemVinculosNumero = "2024" + timestamp.substring(0, 6);

        String resultadoStudentSemVinculos = global.createStudent(
                studentSemVinculosNome,
                studentSemVinculosLogin,
                "senha123",
                studentSemVinculosNumero,
                3
        );

        System.out.println("   Resultado: " + resultadoStudentSemVinculos);

        if (resultadoStudentSemVinculos.equals("Success")) {
            // Buscar o student recém-criado
            global.readAllUserWithJplq();
            Student studentSemVinculos = null;
            for (User user : global.getUsers()) {
                if (user instanceof Student && user.getLogin().equals(studentSemVinculosLogin)) {
                    studentSemVinculos = (Student) user;
                    break;
                }
            }

            if (studentSemVinculos != null) {
                System.out.println("   ✅ Criado: " + studentSemVinculosNome);
                System.out.println("      ID: " + studentSemVinculos.getUserId());
                System.out.println("      Login: " + studentSemVinculos.getLogin());
                System.out.println("      Número: " + studentSemVinculos.getStudentNumber());
                System.out.println("      Vínculos: NENHUM (pode deletar)");
            }
        }

        // =========================================================================
        // 4. STUDENT COM VÍNCULOS (NÃO PODE DELETAR)
        // =========================================================================
        System.out.println("\n4️⃣  STUDENT COM VÍNCULOS:");
        System.out.println("   -------------------");

        String studentComVinculosNome = "Estudante Com Vínculos " + timestamp;
        String studentComVinculosLogin = "student_com_vinculos_" + timestamp;
        String studentComVinculosNumero = "2025" + timestamp.substring(0, 6);

        String resultadoStudentComVinculos = global.createStudent(
                studentComVinculosNome,
                studentComVinculosLogin,
                "senha123",
                studentComVinculosNumero,
                4
        );

        System.out.println("   Resultado: " + resultadoStudentComVinculos);

        if (resultadoStudentComVinculos.equals("Success")) {
            // Buscar o student recém-criado
            global.readAllUserWithJplq();
            Student studentComVinculos = null;
            for (User user : global.getUsers()) {
                if (user instanceof Student && user.getLogin().equals(studentComVinculosLogin)) {
                    studentComVinculos = (Student) user;
                    break;
                }
            }

            if (studentComVinculos != null) {
                System.out.println("   ✅ Criado: " + studentComVinculosNome);
                System.out.println("      ID: " + studentComVinculos.getUserId());
                System.out.println("      Login: " + studentComVinculos.getLogin());
                System.out.println("      Número: " + studentComVinculos.getStudentNumber());

                // CRIAR VÍNCULO 1: Adicionar student ao time
                String vinculoTimeResult = time.createTeamMember(studentComVinculos, TeamMember.Role.DEVELOPER);
                System.out.println("      Vínculo com time: " + vinculoTimeResult);

                // CRIAR VÍNCULO 2: Criar um StudentAward para o student
                // Primeiro precisamos de um project
                String projectResult = curso.createProject(
                        "Projeto Teste Deleção " + timestamp,
                        time,
                        curso
                );

                if (projectResult.contains("successfully")) {
                    // Buscar o projeto criado
                    curso.retrieveProjects();
                    Project projeto = curso.getProjects().get(curso.getProjects().size() - 1);

                    // Buscar um teacher para atribuir o award
                    Teacher teacherParaAward = null;
                    for (User user : global.getUsers()) {
                        if (user instanceof Teacher && user.getLogin().equals(teacherComVinculosLogin)) {
                            teacherParaAward = (Teacher) user;
                            break;
                        }
                    }

                    if (teacherParaAward != null && projeto != null) {
                        // Criar StudentAward
                        String awardResult1 = global.assignAwardStudent(
                                award.getAwardID(),
                                studentComVinculos.getUserId(),
                                teacherParaAward.getUserId(),
                                projeto.getProjectId()
                        );

                        System.out.println("      Vínculo com award: " + awardResult1);

                        // Atualizar dados do student
                        studentComVinculos.readAllTeamMemberWithJplq();
                        studentComVinculos.readAllStudentAwardWithJplq();
                    }
                }

                System.out.println("      Status: TEM vínculos (NÃO pode deletar)");
            }
        }

        // =========================================================================
        // RESUMO FINAL
        // =========================================================================
        System.out.println("\n🎯 RESUMO DOS USUÁRIOS CRIADOS:");
        System.out.println("===============================");

        // Atualizar lista completa de usuários
        global.readAllUserWithJplq();

        System.out.println("\nPara testar a deleção, use a opção 6 (DELETE) ou:");
        System.out.println("1. Execute o sistema");
        System.out.println("2. Consulte o banco de dados para ver os IDs");
        System.out.println("3. Use o comando: global.deleteUser(ID)");

        System.out.println("\n📋 LISTA DE USUÁRIOS CRIADOS:");
        System.out.println("-----------------------------");

        int count = 1;
        for (User user : global.getUsers()) {
            // Verificar se é um dos usuários que acabamos de criar (pelo timestamp no nome)
            if (user.getName().contains(timestamp)) {
                String tipo = user instanceof Teacher ? "TEACHER" : "STUDENT";
                boolean podeDeletar = global.canDeleteUser(user.getUserId());
                String statusDelecao = podeDeletar ? "✅ PODE DELETAR" : "❌ NÃO PODE DELETAR";

                System.out.printf("\n%d. %s - %s\n", count, user.getName(), tipo);
                System.out.println("   ID: " + user.getUserId());
                System.out.println("   Login: " + user.getLogin());
                System.out.println("   Status: " + statusDelecao);

                if (user instanceof Teacher) {
                    Teacher t = (Teacher) user;
                    System.out.println("   Vínculos: " + t.getCourses().size() + " cursos, " +
                            t.getAssignedAwards().size() + " awards atribuídos");
                } else if (user instanceof Student) {
                    Student s = (Student) user;
                    System.out.println("   Vínculos: " + s.getTeamMemberships().size() + " times, " +
                            s.getStudentAwards().size() + " awards recebidos");
                }

                count++;
            }
        }

        System.out.println("\n💡 DICA PARA TESTAR:");
        System.out.println("1. Usuários SEM vínculos → deleteUser() deve retornar 'Success'");
        System.out.println("2. Usuários COM vínculos → deleteUser() deve retornar 'ERROR: Cannot delete user because they are linked...'");
        System.out.println("\n✅ Pronto! Agora você pode testar a deleção no banco de dados.");
    }*/

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
