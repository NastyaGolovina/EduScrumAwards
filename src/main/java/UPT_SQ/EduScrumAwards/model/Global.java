package UPT_SQ.EduScrumAwards.model;

//import jakarta.annotation.PostConstruct;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * The {@code Global} class serves as a centralized data container for managing
 * users, courses, teams, awards, and student awards across the system.
 *
 * This class initializes and maintains lists for each of these entities,
 * providing getter and setter methods for accessing and updating them.
 * It is typically used to store and share global application data
 * throughout the program.
 */

public class Global {
    private ArrayList<User> users;
    private  ArrayList <Course> courses;
    private  ArrayList<Team> teams;
    private ArrayList<Award> awards;
    private ArrayList<StudentAward> studentsAwards;
    private ArrayList<CourseTeacher> courseTeachers;

    /**
     * Constructs a new {@code Global} instance and initializes all lists
     * (users, courses, teams, awards, and student awards) as empty {@link ArrayList}s.
     */
    public Global() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        teams = new ArrayList<>();
        awards = new ArrayList<>();
        studentsAwards = new ArrayList<>();
        courseTeachers = new ArrayList<>();
    }
//
//    @PostConstruct
//    public void init() {
//        readAllAwardWithJplq();
//    }

    /**
     * Returns the list of all users.
     *
     * @return a list of {@link User} objects
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users.
     *
     * @param users a list of {@link User} objects to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Returns the list of all courses.
     *
     * @return a list of {@link Course} objects
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses.
     *
     * @param courses a list of {@link Course} objects to set
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns the list of all teams.
     *
     * @return a list of {@link Team} objects
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Sets the list of teams.
     *
     * @param teams a list of {@link Team} objects to set
     */
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    /**
     * Returns the list of all awards.
     *
     * @return a list of {@link Award} objects
     */
    public ArrayList<Award> getAwards() {
        return awards;
    }

    /**
     * Sets the list of awards.
     *
     * @param awards a list of {@link Award} objects to set
     */
    public void setAwards(ArrayList<Award> awards) {
        this.awards = awards;
    }

    /**
     * Returns the list of all student awards.
     *
     * @return a list of {@link StudentAward} objects
     */
    public ArrayList<StudentAward> getStudentsAwards() {
        return studentsAwards;
    }

    /**
     * Sets the list of student awards.
     *
     * @param studentsAwards a list of {@link StudentAward} objects to set
     */
    public void setStudentsAwards(ArrayList<StudentAward> studentsAwards) {
        this.studentsAwards = studentsAwards;
    }

    /**
     * Returns the list of all CourseTeacher entries.
     * @return list of CourseTeacher
     */
    public ArrayList<CourseTeacher> getCourseTeachers() {
        return courseTeachers;
    }

    /**
     * Sets the list of CourseTeacher entries.
     * @param courseTeachers list to set
     */
    public void setCourseTeachers(ArrayList<CourseTeacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

//    public void readFromDB() {

//    }


    /**
     * Creates a new Award object and saves it to the database.
     *
     * @param awardName        The name of the award. Must not be empty and no longer than 100 characters.
     * @param awardDescription The description of the award. Must not be empty and no longer than 500 characters.
     * @param pointsValue      The number of points for the award. Must be between 0 and 1000.
     * @param assignType       The type of assignment for the award (will be converted to AwardType).
     * @return "Success" if the award was successfully created and saved, or an error message if any validation fails.
     */
    public String createAward(String awardName, String awardDescription, int pointsValue, String assignType,
                              String assignMode) {
        if(!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0 && !assignType.isEmpty() && !assignMode.isEmpty()) {
            if(awardName.length() <= 100) {
                if(awardDescription.length() <= 500) {
                    if(pointsValue <= 1000) {
                        Award newAward = new Award(awardName,
                                awardDescription,
                                pointsValue,
                                AwardType.valueOf(assignType.toUpperCase()),
                                AssignMode.valueOf(assignMode.toUpperCase()));
                        awards.add(newAward);
                        DatabaseHelper DatabaseHelper = new DatabaseHelper();
                        DatabaseHelper.setup();
                        Session session = DatabaseHelper.getSessionFactory().openSession();
                        session.beginTransaction();

                        session.persist(newAward);

                        session.getTransaction().commit();
                        session.close();
                        DatabaseHelper.exit();
                        return "Success";
                    } else {
                        return  "ERROR: Too many points!";
                    }
                } else {
                    return "ERROR: Description is too long!";
                }
            } else {
                return "ERROR: Name is too long!";
            }
        }
        return "ERROR: One of the field is empty";
    }

    /**
     * Loads all Award objects from the database and stores them in the local awards list.
     * Uses JPQL to query the database.
     */
    public void readAllAwardWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<Award> awardList = session.createQuery("SELECT a FROM Award a", Award.class).getResultList();

        awards = (ArrayList<Award>)awardList;

        for (Award award : awards) {
            award.readAllAwardRuleWithJplq();
        }
        session.close();
        DatabaseHelper.exit();
    }


    /**
     * Reads all student awards from the database using JPA and stores them in the
     * {@code studentsAwards} list.
     *

     * Note: Ensure that the {@link DatabaseHelper} and {@link StudentAward} classes
     * are properly configured with JPA/Hibernate mappings before calling this method.
     */
    public void readAllStudentAwardWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<StudentAward> studentAwardList = session.createQuery("SELECT sa FROM StudentAward sa", StudentAward.class).getResultList();

        this.studentsAwards = (ArrayList<StudentAward>)studentAwardList;

        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for an award in the local awards list by its unique ID.
     *
     * @param awardId The ID of the award to search for.
     * @return The Award object with the given ID if found; otherwise, returns null.
     */
    public Award searchAward(int awardId) {
        int i = 0;
        while (i < awards.size() && awards.get(i).getAwardID() != awardId) {
            i++;
        }
        if (i != awards.size()) {
            return awards.get(i);
        }
        return null;
    }


    /**
     * Searches for a user in the list of users by their unique user ID.
     *
     * This method iterates through the list of users until it finds a user
     * whose {@code userId} matches the specified value. If such a user is found,
     * the method returns that {@link User} object; otherwise, it returns {@code null}.
     *
     * @param userId the unique identifier of the user to search for
     * @return the {@link User} object with the specified {@code userId},
     *         or {@code null} if no such user exists in the list
     */
    public User searchUser(long userId) {
        int i = 0;
        while (i < users.size() && users.get(i).getUserId() != userId) {
            i++;
        }
        if (i != users.size()) {
            return users.get(i);
        }
        return null;
    }

    /**
     * Updates an existing award with the specified ID.
     *
     * @param id               The ID of the award to update.
     * @param awardName        The new name of the award. Must not be empty and no longer than 100 characters.
     * @param awardDescription The new description of the award. Must not be empty and no longer than 500 characters.
     * @return "Success" if the award was successfully updated, or an error message if validation fails or the award does not exist.
     */
    public String updateAward(int id,String awardName, String awardDescription) {
        Award award = searchAward(id);
        if(award != null) {
            if(!awardName.isEmpty() && !awardDescription.isEmpty()) {
                if(awardName.length() <= 100) {
                    if(awardDescription.length() <= 500) {
                        award.setAwardName(awardName);
                        award.setAwardDescription(awardDescription);

                        DatabaseHelper DatabaseHelper = new DatabaseHelper();
                        DatabaseHelper.setup();
                        Session session = DatabaseHelper.getSessionFactory().openSession();
                        session.beginTransaction();

                        session.merge(award);

                        session.getTransaction().commit();
                        session.close();
                        DatabaseHelper.exit();

                        return "Success";
                    } else {
                        return "ERROR: Description is too long!";
                    }
                } else {
                    return "ERROR: Name is too long!";
                }
            }
            return "ERROR: One of the field is empty";
        }
        return "ERROR: Award with this id does not exist";
    }

    /**
     * Searches for a {@link StudentAward} in the list of student awards by its ID.
     *
     * This method iterates through the {@code studentsAwards} list to find
     * a {@link StudentAward} with the specified {@code studentAwardId}.
     * Returns the matching object if found, otherwise returns {@code null}.
     *
     * @param studentAwardId the unique identifier of the {@link StudentAward} to search for
     * @return the {@link StudentAward} with the given ID, or {@code null} if not found
     */
    public StudentAward searchStudentAward(int studentAwardId) {
        int i = 0;
        while (i < studentsAwards.size() && studentsAwards.get(i).getStudentAwardId() != studentAwardId) {
            i++;
        }
        if (i != studentsAwards.size()) {
            return studentsAwards.get(i);
        }
        return null;
    }


//    /**
//     * Creates a new award rule for the specified project and teacher.
//     *
//     * @param completionPercent the completion percentage (0–100)
//     * @param isAllGoalsCompleted whether all goals must be completed
//     * @param teacherId the ID of the teacher
//     * @param projectId the ID of the project
//     * @param awardId the ID of the award
//     * @return "Success" if created successfully, or an error message if any field is invalid or missing
//     */
//    // move to api
//    public String createAwardRule(double completionPercent, boolean isAllGoalsCompleted, long teacherId, int projectId,int awardId) {
//        User user = searchUser(teacherId);
//        Award award = searchAward(awardId);
//        Project project = null;
//        for(Course c : courses) {
//            project =c.findProjectById(projectId);
//            if(project != null) {
//                break;
//            }
//        }
//        if(award != null) {
//            if (user != null) {
//                if(project != null) {
//                    return award.createAwardRule(completionPercent,isAllGoalsCompleted,user,project);
//                }
//            }
//        }
//
//        return "ERROR: One of the field is empty or null";
//
//    }
//
//
//    /**
//     * Updates an existing award rule by its identifier.
//     *
//     * @param ruleId the ID of the award rule
//     * @param completionPercent the new completion percentage (0–100)
//     * @param isAllGoalsCompleted whether all goals must be completed
//     * @param awardId the ID of the award
//     * @return "Success" if updated successfully, or an error message if any field is invalid or missing
//     */
//    // move to api
//    public String updateAwardRule(int ruleId, double completionPercent, boolean isAllGoalsCompleted, int awardId) {
//        Award award = searchAward(awardId);
//
//        if(award != null) {
//            return award.updateAwardRule(ruleId,completionPercent,isAllGoalsCompleted);
//        }
//
//        return "ERROR: One of the field is empty or null";
//
//    }
//


    /**
     * Finds a project by its ID across all courses.
     *
     * @param projectId The ID of the project to search for.
     * @return The Project object if found, otherwise null.
     */
    public Project findProject(int projectId) {
        Project project = null;
        for(Course c : courses) {
            project =c.findProjectById(projectId);
            if(project != null) {
                return project;
            }
        }
        return project;
    }

    /**
     * Searches for a project by its team ID.
     *
     * @param teamId The ID of the team to search for.
     * @return The Project object containing the team, or null if not found.
     */
    public Project findProjectByTeamId(int teamId) {
        for (Course course : courses) {
            for (Project project : course.getProjects()) {
                if (project.getTeam() != null && project.getTeam().getTeamID() == teamId) {
                    return project; // нашли проект с нужной командой
                }
            }
        }
        return null;
    }


    /**
     * Assigns an award to a student for a specific project.
     * Performs multiple validations before assignment:
     * - Award exists
     * - Award is MANUAL and INDIVIDUAL
     * - Student exists and has STUDENT role
     * - Teacher exists and has TEACHER role
     * - Project exists
     * - Teacher is assigned to the course of the project
     * - Student is a member of the project's team
     *
     * If all validations pass, creates a StudentAward and persists it to the database.
     *
     * @param awardId    The ID of the award to assign.
     * @param studentId  The ID of the student who will receive the award.
     * @param teacherId  The ID of the teacher assigning the award.
     * @param projectId  The ID of the project associated with the award.
     * @return A string indicating success or the specific validation error encountered.
     */
    public String assignAwardStudent(int awardId, long studentId, long teacherId, int projectId) {
        Award award = searchAward(awardId);
        if(award != null) {
            if(award.getAssignType() == AwardType.MANUAL) {
                if(award.getAssignMode() == AssignMode.INDIVIDUAL) {
                    User student = searchUser(studentId);
                    if (student != null && student.getRole() == UserRole.STUDENT) {
                        User teacher = searchUser(teacherId);
                        if (teacher != null && teacher.getRole() == UserRole.TEACHER) {
                            Project project = findProject(projectId);
                            if (project != null) {
                                if (project.getCourse().isCourseTeacher((Teacher) teacher)) {
                                    if (project.getTeam().isTeamMember(studentId) != null) {
                                        return persistStudentAward(award,
                                                (Student) student,
                                                (Teacher) teacher,
                                                project);
                                    } else {
                                        return "ERROR: The student is not a member of the project team.";
                                    }
                                } else {
                                    return "ERROR: The teacher is not assigned to this course.";
                                }
                            } else {
                                return "ERROR: Project not found.";
                            }
                        } else {
                            return "ERROR: Invalid teacher or role mismatch.";
                        }
                    } else {
                        return "ERROR: Invalid student or role mismatch";
                    }
                } else {
                    return "ERROR: Award ia not INDIVIDUAL";
                }
            } else {
                return "ERROR: Award is not MANUAL";
            }
        } else {
            return "ERROR: Award not found";
        }
    }


    /**
     * Persists a StudentAward object into the database.
     *
     * @param award   The Award to assign.
     * @param student The Student receiving the award.
     * @param teacher The Teacher assigning the award.
     * @param project The Project associated with the award.
     * @return "Success" if persisted successfully, otherwise an error message.
     */
    private String persistStudentAward(Award award, Student student, Teacher teacher, Project project) {
        try {
            // Создаем объект StudentAward
            StudentAward studentAward = new StudentAward(
                    award,
                    student,
                    teacher,
                    project,
                    project.getTeam(),
                    Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    award.getPointsValue()
            );

            DatabaseHelper databaseHelper = new DatabaseHelper();
            databaseHelper.setup();
            Session session = databaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            session.persist(studentAward);

            session.getTransaction().commit();
            session.close();
            databaseHelper.exit();

            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: Failed to persist StudentAward - " + e.getMessage();
        }
    }

    /**
     * Assigns a team award to all members of a given team.
     *
     * Validations performed:
     * - Award exists
     * - Award type is MANUAL
     * - Award assign mode is TEAM
     * - Teacher exists and has TEACHER role
     * - Team exists
     * - Project containing the team exists
     *
     * If all validations pass, a StudentAward is persisted for each student in the team.
     *
     * @param awardId   The ID of the award to assign.
     * @param teacherId The ID of the teacher assigning the award.
     * @param teamId    The ID of the team receiving the award.
     * @return "Success" if all awards were assigned successfully, otherwise a descriptive error message.
     */
    public String assignTeamAward(int awardId, long teacherId, int teamId) {
        Award award = searchAward(awardId);
        if(award != null) {
            if(award.getAssignType() == AwardType.MANUAL) {
                if(award.getAssignMode() == AssignMode.TEAM) {
                    User teacher = searchUser(teacherId);
                    if (teacher != null && teacher.getRole() == UserRole.TEACHER) {
                        Team team = searchTeam(teamId);
                        if (team != null) {
                            Project project = findProjectByTeamId(teamId);
                            if(project != null) {
                                for(TeamMember member : team.getTeamMember()) {
                                    persistStudentAward(award,
                                            member.getStudent(),
                                            (Teacher) teacher,
                                            project);
                                }
                                return "Success";
                            } else {
                                return "ERROR: Project not found for the given team";
                            }
                        } else {
                            return "ERROR: Team not found";
                        }
                    } else {
                        return "ERROR: Invalid teacher or role mismatch.";
                    }
                } else {
                    return "ERROR: Award is not TEAM";
                }
            } else {
                return "ERROR: Award is not MANUAL";
            }
        } else {
            return "ERROR: Award not found";
        }

    }






    /**
     * Creates a new Course and saves it to the database.
     *
     * @param courseName The name of the course. Must not be empty and no longer than 100 characters.
     * @return "Success" if the course was created and saved; otherwise, an error message describing the issue.
     */
    public String createCourse(String courseName) {
        // courseId is auto-generated by the DB; ignore provided id and let JPA assign it
        if (courseName == null || courseName.isEmpty()) {
            return "ERROR: Name is empty!";
        }
        if (courseName.length() > 100) {
            return "ERROR: Name is too long!";
        }

        Course newCourse = new Course(courseName);
        courses.add(newCourse);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.persist(newCourse);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();
        return "Success";
    }

    /**
     * Loads all Course objects from the database and stores them in the local courses list.
     * Uses JPQL to query the database.
     */
    public void readAllCourseWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<Course> courseList = session.createQuery("SELECT c FROM Course c", Course.class).getResultList();

        courses = (ArrayList<Course>) courseList;

        // Populate each course with its CourseTeacher rows
        for (Course c : courses) {
            if (c != null) {
                c.readAllCourseTeacherWithJplq();
            }
        }
        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for a course in the local courses list by its unique ID.
     *
     * @param courseId The ID of the course to search for.
     * @return The Course object with the given ID if found; otherwise, returns null.
     */
    public Course searchCourse(int courseId) {
        int i = 0;
        while (i < courses.size() && courses.get(i).getCourseID() != courseId) {
            i++;
        }
        if (i != courses.size()) {
            return courses.get(i);
        }
        return null;
    }

    /**
     * Updates an existing course with the specified ID.
     *
     * @param id         The ID of the course to update.
     * @param courseName The new name of the course. Must not be empty and no longer than 100 characters.
     * @return "Success" if the course was successfully updated, or an error message if validation fails or the course does not exist.
     */
    public String updateCourse(int id, String courseName) {
        Course course = searchCourse(id);
        if (course != null) {
            if (courseName != null && !courseName.isEmpty()) {
                if (courseName.length() <= 100) {
                    course.setCourseName(courseName);

                    DatabaseHelper DatabaseHelper = new DatabaseHelper();
                    DatabaseHelper.setup();
                    Session session = DatabaseHelper.getSessionFactory().openSession();
                    session.beginTransaction();

                    session.merge(course);

                    session.getTransaction().commit();
                    session.close();
                    DatabaseHelper.exit();

                    return "Success";
                } else {
                    return "ERROR: Name is too long!";
                }
            }
            return "ERROR: Name is empty!";
        }
        return "ERROR: Course with this id does not exist";
    }

     /**
     * Creates a new TEAM object and saves it to the database.
     * @param teamName    The name of the team. Must not be empty and no longer than 100 characters.
     * @return "Success" if the team was successfully created and saved, or an error message if any validation fails.
     */
     public String createTeam( String teamName) {
         if (teamName == null || teamName.isEmpty())
             return "ERROR: Name is empty!";
         if (teamName.length() > 100)
             return "ERROR: Name is too long!";

         // prevent duplicate name locally
         for (Team t : teams) {
             if (t.getTeamName().equalsIgnoreCase(teamName)) {
                 return "ERROR: Team name already exists!";
             }
         }

         // Create new team
         Team newTeam = new Team(teamName);
         teams.add(newTeam);

         // saving team just created into database by using Hibernate
         DatabaseHelper DatabaseHelper = new DatabaseHelper();
         DatabaseHelper.setup();
         Session session = DatabaseHelper.getSessionFactory().openSession();
         session.beginTransaction();

         session.persist(newTeam);

         session.getTransaction().commit();
         session.close();
         DatabaseHelper.exit();

         return "Success";
     }

    /**
     * Loads all Team objects from the database and stores them in the local teams list.
     * Uses JPLQ to query the database.
     */
    public void readAllTeamWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<Team> teamList = session.createQuery("SELECT t FROM Team t", Team.class).getResultList();
        teams = new ArrayList<>(teamList);

        // also load members for each team so the in-memory cache is complete
        for (Team t : teams) {
            if (t != null) t.readAllTeamMemberWithJplq();
        }

        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for a team in the local team list by its unique ID.
     *
     * @param teamId The ID of the team to search for.
     * @return The Team object with the given ID if found; otherwise, returns null.
     */
    public Team searchTeam(int teamId) {
        int i = 0;
        while (i < teams.size() && teams.get(i).getTeamID() != teamId)
            i++;
        if (i != teams.size()) {
            return teams.get(i);
        }
        return null;
    }

    /**
     * Updates the name of an existing {@link Team} identified by its unique ID.
     *
    * @param id        the unique ID of the {@link Team} to update
    * @param teamName  the new name for the team; must not be {@code null} or empty, and must be ≤ 100 characters
    * @return "Success" if the update was completed successfully,
     */

    public String updateTeam(int id, String teamName) {
        Team team = searchTeam(id);
        if (team == null)
            return "ERROR: Team with this id does not exist";
        if (teamName == null || teamName.isEmpty())
            return "ERROR: Name is empty!";
        if (teamName.length() > 100)
            return "ERROR: Name is too long!";

        team.setTeamName(teamName);

        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.merge(team);

        session.getTransaction().commit();
        session.close();
        DatabaseHelper.exit();
        return "Success";
    }
}