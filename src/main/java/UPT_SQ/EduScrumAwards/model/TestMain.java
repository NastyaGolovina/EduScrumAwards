package UPT_SQ.EduScrumAwards.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;

public class TestMain {
    public static void main(String[] args) {
        //just to test
        // we will remove this class later
        long ts = System.currentTimeMillis();
        Award award = new Award(
                "Top Performer " + ts,
                "Awarded for outstanding performance",
                100,
                AwardType.AUTOMATIC,
                AssignMode.TEAM
        );

        Team team = new Team(
                "IronMan-" + ts
        );

        Student student = new Student();
        student.setName("Alice " + ts);
        student.setLogin("alice_" + ts);
        student.setPassword("pass123");

        TeamMember mem = new TeamMember("Alice Wonderland", TeamMember.Role.PRODUCT_OWNER);
        mem.setTeam(team);
        mem.setStudent(student);

        Teacher teacher = new Teacher();
        teacher.setName("Prof. Smith " + ts);
        teacher.setLogin("prof_smith_" + ts);
        teacher.setPassword("pass123");

        Course course = new Course("Test Course " + ts);
        CourseTeacher courseTeacher = new CourseTeacher(course, teacher, true);

        Project project = new Project();
        project.setProjectName("AI Development " + ts);
        project.setTeam(team);
        project.setCourse(course);

        Sprint sprint1 = new Sprint();
        sprint1.setStartDate(new Date()); // today
        sprint1.setEndDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)); // +7 days
        sprint1.setProject(project);


        Goal goal1 = new Goal("Implement login feature", 10, false);
        goal1.setSprint(sprint1);

        AwardRule awardRule = new AwardRule();
        awardRule.setAward(award);
        awardRule.setProject(project);
        awardRule.setTeacher(teacher);
        awardRule.setAllGoalsCompleted(false);
        awardRule.setCompletionPercent(75.0);

        StudentAward studentAward = new StudentAward();
        studentAward.setAward(award);
        studentAward.setStudent(student);
        studentAward.setTeacher(teacher);
        studentAward.setProject(project);
        studentAward.setTeam(team);
        studentAward.setDate(new Date());
        studentAward.setPoints(100);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(award);
        session.persist(team);
        session.persist(student);
        session.persist(mem);
        session.persist(teacher);
        session.persist(course);
        session.persist(courseTeacher);
        session.persist(project);
        session.persist(sprint1);
        session.persist(goal1);

        session.persist(awardRule);
        session.persist(studentAward);


        session.flush();
        session.getTransaction().commit();
        session.close();

        // =============================================
        // NEW TESTS - USER, TEACHER AND STUDENT
        // =============================================
        System.out.println("===  STARTING THE TESTS - USER/TEACHER/STUDENT ===");

        try {
            // Execute new tests using the same DatabaseHelper
            UserTeacherStudentCRUD(databaseHelper);
        } catch (Exception e) {
            System.err.println("Error during new tests: " + e.getMessage());
            e.printStackTrace();
        } finally {
            databaseHelper.exit();
        }

        System.out.println("=== ALL TESTS COMPLETED ===");
//        ////        TEST Global
//        Global g = new Global();
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


////        g.createAward("Top Performer",
////                "Awarded for outstanding performance",
////                100,
////                "AUTOMATIC",
////                 "TEAM");
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

    }

    private static void UserTeacherStudentCRUD(DatabaseHelper databaseHelper) {
        System.out.println("\n🔧 STARTING CRUD TESTS FOR USER/TEACHER/STUDENT ");

        try {
            // Teste 1: Criar e inserir dados
            System.out.println("\n TEST 1 - INSERT DATA");
            //CreateAndInsertUsers(databaseHelper);

            // Teste 2: Editar dados
            System.out.println("\n TEST 2 - EDIT DATA");
            //UpdateUsersOperations(databaseHelper);

            // Teste 3: Deletar dados
            System.out.println("\n TEST 3 - DELETE DATA");
            //DeleteUsersOperations(databaseHelper);

            // Teste 4: Listar dados restantes
            System.out.println("\n TEST 4 - VERIFY REMAINING DATA");
            ListRemainingUsersData(databaseHelper);

            System.out.println("\n ALL CRUD TESTS SUCCESSFULLY COMPLETED!");

        } catch (Exception e) {
            System.err.println(" Error in CRUD tests: " + e.getMessage());
            throw e;
        }
    }

    private static void CreateAndInsertUsers(DatabaseHelper databaseHelper) {
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Insert Students
            Student student1 = new Student("Ana Silva", "ana.silva", "password123", "2023001", 2);
            session.persist(student1);
            System.out.println("✓ Student inserido: " + student1.getName() + " (Nº: " + student1.getStudentNumber() + ")");

            Student student2 = new Student("João Pereira", "joao.pereira", "pass456", "2023002", 3);
            session.persist(student2);
            System.out.println("✓ Student inserido: " + student2.getName() + " (Nº: " + student2.getStudentNumber() + ")");

            // Insert Teachers
            Teacher teacher1 = new Teacher("Dr. Carlos Santos", "carlos.santos", "teacher123");
            session.persist(teacher1);
            System.out.println("✓ Teacher inserido: " + teacher1.getName());

            Teacher teacher2 = new Teacher("Prof. Maria Oliveira", "maria.oliveira", "teach456");
            session.persist(teacher2);
            System.out.println("✓ Teacher inserido: " + teacher2.getName());

            transaction.commit();
            System.out.println("✓ Transação commitada com sucesso");

            // Verify inserted data
            verifyInsertedData(session);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("✗ Erro - Rollback realizado");
            }
            throw e;
        } finally {
            session.close();
        }
    }

    private static void verifyInsertedData(Session session) {
        System.out.println("\n VERIFICANDO DADOS INSERIDOS COM QUERY DA BASE DE DADOS:");

        // List all Users
        List<User> users = session.createQuery("FROM User", User.class).list();
        System.out.println();
        System.out.println(" Total Users on dataBase: " + users.size());
        for (User user : users) {
            System.out.println("   User: " + user.getName() +
                    " (" + user.getRole() + ") - ID: " + user.getUserId() +
                    " - Login: " + user.getLogin());
        }

        // List Students specifically
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        System.out.println();
        System.out.println(" Total Students: " + students.size());
        for (Student student : students) {
            System.out.println("    Student: " + student.getName() +
                    " - Número: " + student.getStudentNumber() +
                    " - Semestre: " + student.getCurrentSemester());
        }

        // List Teachers specifically
        List<Teacher> teachers = session.createQuery("FROM Teacher", Teacher.class).list();
        System.out.println();
        System.out.println(" Total Teachers: " + teachers.size());
        for (Teacher teacher : teachers) {
            System.out.println("   Teacher: " + teacher.getName());
        }
    }

    private static void UpdateUsersOperations(DatabaseHelper databaseHelper) {
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Find and edit Student
            Student studentToUpdate = session.createQuery("FROM Student WHERE studentNumber = '2023001'", Student.class)
                    .uniqueResult();
            if (studentToUpdate != null) {
                String oldName = studentToUpdate.getName();
                Integer oldSemester = studentToUpdate.getCurrentSemester();
                studentToUpdate.setName("Ana Silva Costa (Atualizado)");
                studentToUpdate.setCurrentSemester(6);
                session.merge(studentToUpdate);
                System.out.println("✓ Student editado: '" + oldName + "' -> '" + studentToUpdate.getName() + "'");
                System.out.println("✓ Semestre atualizado: " + oldSemester + " -> " + studentToUpdate.getCurrentSemester());
            } else {
                System.out.println("✗ Student não encontrado para edição");
            }

            // Find and edit Teacher
            Teacher teacherToUpdate = session.createQuery("FROM Teacher WHERE login = 'carlos.santos'", Teacher.class)
                    .uniqueResult();
            if (teacherToUpdate != null) {
                String oldName = teacherToUpdate.getName();
                teacherToUpdate.setName("Dr. Carlos Santos Silva (Atualizado)");
                session.merge(teacherToUpdate);
                System.out.println("✓ Teacher editado: '" + oldName + "' -> '" + teacherToUpdate.getName() + "'");
            } else {
                System.out.println("✗ Teacher não encontrado para edição");
            }

            transaction.commit();
            System.out.println("✓ Todas as edições foram commitadas com sucesso");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("✗ Erro - Rollback realizado");
            }
            throw e;
        } finally {
            session.close();
        }
    }

    private static void DeleteUsersOperations(DatabaseHelper databaseHelper) {
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Delete Student
            Student studentToDelete = session.createQuery("FROM Student WHERE studentNumber = '2023001'", Student.class)
                    .uniqueResult();
            if (studentToDelete != null) {
                System.out.println("✓ Student encontrado para deleção: " + studentToDelete.getName());
                session.remove(studentToDelete);
                System.out.println("✓ Student deletado: " + studentToDelete.getName());
            } else {
                System.out.println("✗ Student não encontrado para deleção");
            }

            // Delete Teacher
            Teacher teacherToDelete = session.createQuery("FROM Teacher WHERE login = 'carlos.santos'", Teacher.class)
                    .uniqueResult();
            if (teacherToDelete != null) {
                System.out.println("✓ Teacher encontrado para deleção: " + teacherToDelete.getName());
                session.remove(teacherToDelete);
                System.out.println("✓ Teacher deletado: " + teacherToDelete.getName());
            } else {
                System.out.println("✗ Teacher não encontrado para deleção");
            }

            transaction.commit();
            System.out.println("✓ Todas as deleções foram commitadas com sucesso");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("✗ Erro - Rollback realizado");
            }
            throw e;
        } finally {
            session.close();
        }
    }

    private static void ListRemainingUsersData(DatabaseHelper databaseHelper) {
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            // List remaining Users
            List<User> remainingUsers = session.createQuery("FROM User", User.class).list();
            System.out.println(" FINAL REPORT - REMAINING DATA IN DATABASE:");
            System.out.println();
            System.out.println("   Total remaining Users: " + remainingUsers.size());

            for (User user : remainingUsers) {

                System.out.println("   " + user.getName() +
                        " (" + user.getRole() + ")" +
                        " - ID: " + user.getUserId() +
                        " - Login: " + user.getLogin());
            }

            // Final statistics
            List<Student> remainingStudents = session.createQuery("FROM Student", Student.class).list();
            List<Teacher> remainingTeachers = session.createQuery("FROM Teacher", Teacher.class).list();
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

            System.out.println("\n FINAL STATISTICS: ");
            System.out.println();
            System.out.println("    Remaining Students: " + remainingStudents.size());
            System.out.println();
            System.out.println("    Remaining Teachers: " + remainingTeachers.size());
            System.out.println();
            System.out.println("    Total Users overall: " + remainingUsers.size());

        } finally {
            session.close();
        }
    }
}
