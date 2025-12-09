package UPT_SQ.EduScrumAwards.model;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
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
    private ArrayList<Course> courses;
    private ArrayList<Team> teams;
    private ArrayList<Award> awards;
    private ArrayList<StudentAward> studentsAwards;

    /**
     * Constructs a new {@code Global} instance and initializes all lists
     * (users, courses, teams, awards, and student awards) as empty
     * {@link ArrayList}s.
     */
    public Global() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        teams = new ArrayList<>();
        awards = new ArrayList<>();
        studentsAwards = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("Initializing Global...");

            readAllTeamWithJplq();
            readAllUserWithJplq();
            readAllAwardWithJplq();
            readAllCourseWithJplq();
            readAllStudentAwardWithJplq();

            System.out.println("✓ Global initialization complete");
            System.out.println("  - Users: " + users.size());
            System.out.println("  - Courses: " + courses.size());
            System.out.println("  - Teams: " + teams.size());
            System.out.println("  - Awards: " + awards.size());
            System.out.println("  - Student Awards: " + studentsAwards.size());

        } catch (Exception e) {
            System.err.println("ERROR during Global initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

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

    // public void readFromDB() {

    // }

    /**
     * Creates a new Award object and saves it to the database.
     *
     * @param awardName        The name of the award. Must not be empty and no
     *                         longer than 100 characters.
     * @param awardDescription The description of the award. Must not be empty and
     *                         no longer than 500 characters.
     * @param pointsValue      The number of points for the award. Must be between 0
     *                         and 1000.
     * @param assignType       The type of assignment for the award (will be
     *                         converted to AwardType).
     * @return "Success" if the award was successfully created and saved, or an
     *         error message if any validation fails.
     */
    public String createAward(String awardName, String awardDescription, int pointsValue, String assignType,
            String assignMode) {
        if (!awardName.isEmpty() && !awardDescription.isEmpty() && pointsValue >= 0 && !assignType.isEmpty()
                && !assignMode.isEmpty()) {
            if (awardName.length() <= 100) {
                if (awardDescription.length() <= 500) {
                    if (pointsValue <= 1000) {
                        if (!assignType.equalsIgnoreCase("AUTOMATIC") || !assignMode.equalsIgnoreCase("INDIVIDUAL")) {
                            // Award newAward = new Award(awardName,
                            // awardDescription,
                            // pointsValue,
                            // AwardType.valueOf(assignType.toUpperCase()),
                            // AssignMode.valueOf(assignMode.toUpperCase()));
                            // awards.add(newAward);
                            // DatabaseHelper DatabaseHelper = new DatabaseHelper();
                            // DatabaseHelper.setup();
                            // Session session = DatabaseHelper.getSessionFactory().openSession();
                            // session.beginTransaction();
                            //
                            // session.persist(newAward);
                            //
                            // session.getTransaction().commit();
                            // session.close();
                            // DatabaseHelper.exit();
                            // return "Success";

                            try {
                                Award newAward = new Award(
                                        awardName,
                                        awardDescription,
                                        pointsValue,
                                        AwardType.valueOf(assignType.toUpperCase()),
                                        AssignMode.valueOf(assignMode.toUpperCase()));

                                awards.add(newAward);

                                DatabaseHelper databaseHelper = new DatabaseHelper();
                                databaseHelper.setup();
                                Session session = databaseHelper.getSessionFactory().openSession();
                                session.beginTransaction();
                                session.persist(newAward);
                                session.getTransaction().commit();
                                session.close();
                                databaseHelper.exit();

                                return "Success";
                            } catch (IllegalArgumentException e) {
                                return "Error: Invalid award type or assign mode - " + e.getMessage();
                            } catch (Exception e) {
                                return "Error creating award: " + e.getMessage();
                            }
                        } else {
                            return "ERROR: AUTOMATIC awards can be assign only for TEAM";
                        }
                    } else {
                        return "ERROR: Too many points!";
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
     * Loads all Award objects from the database and stores them in the local awards
     * list.
     * Uses JPQL to query the database.
     */
    public void readAllAwardWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<Award> awardList = session.createQuery("SELECT a FROM Award a", Award.class).getResultList();

        awards = (ArrayList<Award>) awardList;

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
     * 
     * Note: Ensure that the {@link DatabaseHelper} and {@link StudentAward} classes
     * are properly configured with JPA/Hibernate mappings before calling this
     * method.
     */
    public void readAllStudentAwardWithJplq() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper();
        DatabaseHelper.setup();
        Session session = DatabaseHelper.getSessionFactory().openSession();

        List<StudentAward> studentAwardList = session.createQuery("SELECT sa FROM StudentAward sa", StudentAward.class)
                .getResultList();

        this.studentsAwards = (ArrayList<StudentAward>) studentAwardList;

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
     * the method returns that {@link User} object; otherwise, it returns
     * {@code null}.
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
     * @param awardName        The new name of the award. Must not be empty and no
     *                         longer than 100 characters.
     * @param awardDescription The new description of the award. Must not be empty
     *                         and no longer than 500 characters.
     * @return "Success" if the award was successfully updated, or an error message
     *         if validation fails or the award does not exist.
     */
    public String updateAward(int id, String awardName, String awardDescription) {
        Award award = searchAward(id);
        if (award != null) {
            if (!awardName.isEmpty() && !awardDescription.isEmpty()) {
                if (awardName.length() <= 100) {
                    if (awardDescription.length() <= 500) {
                        // award.setAwardName(awardName);
                        // award.setAwardDescription(awardDescription);
                        //
                        // DatabaseHelper DatabaseHelper = new DatabaseHelper();
                        // DatabaseHelper.setup();
                        // Session session = DatabaseHelper.getSessionFactory().openSession();
                        // session.beginTransaction();
                        //
                        // session.merge(award);
                        //
                        // session.getTransaction().commit();
                        // session.close();
                        // DatabaseHelper.exit();
                        //
                        // return "Success";
                        try {
                            award.setAwardName(awardName);
                            award.setAwardDescription(awardDescription);

                            DatabaseHelper databaseHelper = new DatabaseHelper();
                            databaseHelper.setup();
                            Session session = databaseHelper.getSessionFactory().openSession();
                            session.beginTransaction();

                            session.merge(award);

                            session.getTransaction().commit();
                            session.close();
                            databaseHelper.exit();

                            return "Success";

                        } catch (Exception e) {
                            return "ERROR: " + e.getMessage();
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
        return "ERROR: Award with this id does not exist";
    }

    /**
     * Searches for a {@link StudentAward} in the list of student awards by its ID.
     *
     * This method iterates through the {@code studentsAwards} list to find
     * a {@link StudentAward} with the specified {@code studentAwardId}.
     * Returns the matching object if found, otherwise returns {@code null}.
     *
     * @param studentAwardId the unique identifier of the {@link StudentAward} to
     *                       search for
     * @return the {@link StudentAward} with the given ID, or {@code null} if not
     *         found
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

    // /**
    // * Creates a new award rule for the specified project and teacher.
    // *
    // * @param completionPercent the completion percentage (0–100)
    // * @param isAllGoalsCompleted whether all goals must be completed
    // * @param teacherId the ID of the teacher
    // * @param projectId the ID of the project
    // * @param awardId the ID of the award
    // * @return "Success" if created successfully, or an error message if any field
    // is invalid or missing
    // */
    // // move to api
    // public String createAwardRule(double completionPercent, boolean
    // isAllGoalsCompleted, long teacherId, int projectId,int awardId) {
    // User user = searchUser(teacherId);
    // Award award = searchAward(awardId);
    // Project project = null;
    // for(Course c : courses) {
    // project =c.findProjectById(projectId);
    // if(project != null) {
    // break;
    // }
    // }
    // if(award != null) {
    // if (user != null) {
    // if(project != null) {
    // return
    // award.createAwardRule(completionPercent,isAllGoalsCompleted,user,project);
    // }
    // }
    // }
    //
    // return "ERROR: One of the field is empty or null";
    //
    // }
    //
    //
    // /**
    // * Updates an existing award rule by its identifier.
    // *
    // * @param ruleId the ID of the award rule
    // * @param completionPercent the new completion percentage (0–100)
    // * @param isAllGoalsCompleted whether all goals must be completed
    // * @param awardId the ID of the award
    // * @return "Success" if updated successfully, or an error message if any field
    // is invalid or missing
    // */
    // // move to api
    // public String updateAwardRule(int ruleId, double completionPercent, boolean
    // isAllGoalsCompleted, int awardId) {
    // Award award = searchAward(awardId);
    //
    // if(award != null) {
    // return award.updateAwardRule(ruleId,completionPercent,isAllGoalsCompleted);
    // }
    //
    // return "ERROR: One of the field is empty or null";
    //
    // }
    //

    /**
     * Finds a project by its ID across all courses.
     *
     * @param projectId The ID of the project to search for.
     * @return The Project object if found, otherwise null.
     */
    public Project findProject(int projectId) {
        Project project = null;
        for (Course c : courses) {
            project = c.findProjectById(projectId);
            if (project != null) {
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
     * If all validations pass, creates a StudentAward and persists it to the
     * database.
     *
     * @param awardId   The ID of the award to assign.
     * @param studentId The ID of the student who will receive the award.
     * @param teacherId The ID of the teacher assigning the award.
     * @param projectId The ID of the project associated with the award.
     * @return A string indicating success or the specific validation error
     *         encountered.
     */
    public String assignAwardStudent(int awardId, long studentId, long teacherId, int projectId) {
        Award award = searchAward(awardId);
        if (award != null) {
            if (award.getAssignType() == AwardType.MANUAL) {
                if (award.getAssignMode() == AssignMode.INDIVIDUAL) {
                    User student = searchUser(studentId);
                    if (student != null && student.getRole() == UserRole.STUDENT) {
                        User teacher = searchUser(teacherId);
                        if (teacher != null && teacher.getRole() == UserRole.TEACHER) {
                            Project project = findProject(projectId);
                            if (project != null) {
                                if (project.getCourse().isCourseTeacher(teacher.getUserId())) {
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

            StudentAward studentAward = new StudentAward(
                    award,
                    student,
                    teacher,
                    project,
                    project.getTeam(),
//                    Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
//                    Date.from(LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant()),
                    Date.from(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).atZone(ZoneId.systemDefault()).toInstant()),
                    award.getPointsValue());

            studentsAwards.add(studentAward);

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
     * If all validations pass, a StudentAward is persisted for each student in the
     * team.
     *
     * @param awardId   The ID of the award to assign.
     * @param teacherId The ID of the teacher assigning the award.
     * @param teamId    The ID of the team receiving the award.
     * @return "Success" if all awards were assigned successfully, otherwise a
     *         descriptive error message.
     */
    public String assignTeamAward(int awardId, long teacherId, int teamId) {
        Award award = searchAward(awardId);
        if (award != null) {
            if (award.getAssignType() == AwardType.MANUAL) {
                if (award.getAssignMode() == AssignMode.TEAM) {
                    User teacher = searchUser(teacherId);
                    if (teacher != null && teacher.getRole() == UserRole.TEACHER) {
                        Team team = searchTeam(teamId);
                        if (team != null) {
                            Project project = findProjectByTeamId(teamId);
                            if (project != null) {
                                for (TeamMember member : team.getTeamMember()) {
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
     * Deletes a StudentAward record from the database and removes it from the local
     * list.
     *
     * This method first searches for the StudentAward by its ID. If found, it opens
     * a Hibernate
     * session and begins a transaction to remove the record. After successful
     * deletion, the record
     * is also removed from the local {@code studentsAwards} list.
     *
     * If any exception occurs during the deletion process, the transaction is
     * rolled back and
     * an error message is returned. If the StudentAward is not found, a
     * corresponding error message
     * is returned.
     *
     * @param studentAwardId the ID of the StudentAward to delete
     * @return a {@code String} message indicating success or the reason for failure
     */
    public String deleteStudentAward(int studentAwardId) {
        StudentAward studentAward = searchStudentAward(studentAwardId);
        if (studentAward != null) {
            // DatabaseHelper databaseHelper = new DatabaseHelper();
            // databaseHelper.setup();
            // Session session = databaseHelper.getSessionFactory().openSession();
            // session.beginTransaction();
            //
            // session.remove(studentAward);
            //
            // session.getTransaction().commit();
            // session.close();
            // databaseHelper.exit();
            DatabaseHelper databaseHelper = new DatabaseHelper();
            Session session = null;

            try {
                databaseHelper.setup();
                session = databaseHelper.getSessionFactory().openSession();
                session.beginTransaction();

                session.remove(studentAward);

                session.getTransaction().commit();

                studentsAwards.remove(studentAward);
                return "Record successfully deleted.";

            } catch (Exception e) {
                if (session != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                return "ERROR: Failed to delete the record. Reason: " + e.getMessage();

            } finally {
                if (session != null) {
                    session.close();
                }
                databaseHelper.exit();
            }

        } else {
            return "ERROR: Student Award not found";
        }
    }

    /**
     * Checks if an award is associated with any student awards.
     *
     * @param awardId the ID of the award to check
     * @return true if the award exists in any StudentAward record, false otherwise
     */
    public boolean isAwardInStudentAwards(int awardId) {
        for (StudentAward studentAward : studentsAwards) {
            if (studentAward.getAward().getAwardID() == awardId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes an award from the database.
     *
     * The method performs the following steps:
     * 1. Checks if the award exists.
     * 2. Checks if the award is referenced in any StudentAward records.
     * 3. Deletes all related award rules (if any).
     * 4. Removes the award from the database and the in-memory list.
     *
     *
     * @param awardId the ID of the award to delete
     * @return a message indicating success or the reason for failure
     */
    public String deleteAward(int awardId) {
        Award award = searchAward(awardId);
        if (award != null) {
            if (!isAwardInStudentAwards(awardId)) {
                Boolean result = true;
                if (award.getAwardRules().size() > 0) {
                    result = award.deleteAllAwardRules();
                }
                if (result) {
                    DatabaseHelper databaseHelper = new DatabaseHelper();
                    Session session = null;

                    try {
                        databaseHelper.setup();
                        session = databaseHelper.getSessionFactory().openSession();
                        session.beginTransaction();

                        session.remove(award);

                        session.getTransaction().commit();

                        awards.remove(award);
                        return "Record successfully deleted.";

                    } catch (Exception e) {
                        if (session != null && session.getTransaction().isActive()) {
                            session.getTransaction().rollback();
                        }
                        return "ERROR: Failed to delete the record. Reason: " + e.getMessage();

                    } finally {
                        if (session != null) {
                            session.close();
                        }
                        databaseHelper.exit();
                    }
                } else {
                    return "ERROR: Failed to delete awardRules";
                }
            } else {
                return "ERROR: Award cannot be deleted because there are existing records in the StudentAward table";
            }
        } else {
            return "ERROR: Award not found";
        }
    }

    /**
     * Creates a new Course and saves it to the database.
     *
     * @param courseName The name of the course. Must not be empty and no longer
     *                   than 100 characters.
     * @return "Success" if the course was created and saved; otherwise, an error
     *         message describing the issue.
     */
    public String createCourse(String courseName) {
        // courseId is auto-generated by the DB; ignore provided id and let JPA assign
        // it
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
     * Loads all Course objects from the database and stores them in the local
     * courses list.
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
                c.retrieveProjects();
            }
        }
        session.close();
        DatabaseHelper.exit();
    }

    /**
     * Searches for a course in the local courses list by its unique ID.
     *
     * @param courseId The ID of the course to search for.
     * @return The Course object with the given ID if found; otherwise, returns
     *         null.
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
     * @param courseName The new name of the course. Must not be empty and no longer
     *                   than 100 characters.
     * @return "Success" if the course was successfully updated, or an error message
     *         if validation fails or the course does not exist.
     */
    public String updateCourse(int id, String courseName) {
        readAllCourseWithJplq();
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
     * Deletes an existing course with the specified ID.
     *
     * @param id The ID of the course to delete.
     * @return "Success" if the course was successfully deleted, or an error message
     *         if the course does not exist or deletion fails.
     */
    public String deleteCourse(int id) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = null;
        try {
            session = databaseHelper.getSessionFactory().openSession();
            session.beginTransaction();

            Course course = session.get(Course.class, id);

            if (course == null) {
                session.getTransaction().rollback();
                return "ERROR: Course with id " + id + " does not exist";
            }

            if (!course.getProjects().isEmpty()) {
                session.getTransaction().rollback();
                return "ERROR: Course still has projects";
            }

            // Delete associated CourseTeachers
            session.createMutationQuery("DELETE FROM CourseTeacher ct WHERE ct.course.courseID = :courseId")
                    .setParameter("courseId", id)
                    .executeUpdate();

            session.remove(course);

            session.getTransaction().commit();

            // Update local list
            Course localCourse = searchCourse(id);
            if (localCourse != null) {
                courses.remove(localCourse);
            }

            return "Success";
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return "ERROR: Failed to delete course: " + e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
            databaseHelper.exit();
        }
    }

    public Team findTeamByName(String teamName) {
        for (Team t : teams) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Creates a new TEAM object and saves it to the database.
     * 
     * @param teamName The name of the team. Must not be empty and no longer than
     *                 100 characters.
     * @return "Success" if the team was successfully created and saved, or an error
     *         message if any validation fails.
     */
    public String createTeam(String teamName) {
        if (teamName == null || teamName.isEmpty())
            return "ERROR: Name is empty!";
        if (teamName.length() > 100)
            return "ERROR: Name is too long!";

        // Refresh local list from DB so we see all existing teams
        readAllTeamWithJplq();

        // prevent duplicate name locally
        Team existing = findTeamByName(teamName);
        if (existing != null) {
            return "ERROR: Team name already exists!";
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
     * Loads all Team objects from the database and stores them in the local teams
     * list.
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
            if (t != null)
                t.readAllTeamMemberWithJplq();
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
     * @param id       the unique ID of the {@link Team} to update
     * @param teamName the new name for the team; must not be {@code null} or empty,
     *                 and must be ≤ 100 characters
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

        // check if another team already has this name
        Team existingTeam = findTeamByName(teamName);
        if (existingTeam != null && existingTeam.getTeamID() != id) {
            return "ERROR: Team name already exists!";
        }

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
    /**
     * Checks if a team is associated with any student awards.
     *
     * @param teamId the ID of the team to check
     * @return true if the team exists in any StudentAward record, false otherwise
     */
    public boolean isTeamInStudentAwards(int teamId) {
        for (StudentAward studentAward : studentsAwards) {
            if (studentAward.getTeam() != null &&
                    studentAward.getTeam().getTeamID() == teamId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a team is associated with any projects.
     *
     * @param teamId the ID of the team to check
     * @return true if the team exists in any Project record, false otherwise
     */

    public boolean isTeamInProject(int teamId) {
        DatabaseHelper db = new DatabaseHelper();
        db.setup();
        Session session = db.getSessionFactory().openSession();

        Long count = session.createQuery(
                        "SELECT COUNT(p) FROM Project p WHERE p.team.teamID = :tid",
                        Long.class
                ).setParameter("tid", teamId)
                .getSingleResult();

        session.close();
        db.exit();

        return count > 0;
    }

    /**
     * Deletes a team from the database if it is not associated with any projects
     * or student awards.
     *
     * The method performs the following steps:
     * 1. Checks if the team exists.
     * 2. Checks if the team is referenced in any Project records.
     * 3. Checks if the team is referenced in any StudentAward records.
     * 4. Removes the team from the database (cascading deletes all TeamMembers).
     * 5. Updates the in-memory teams list.
     *
     * @param teamId the ID of the team to delete
     * @return a message indicating success or the reason for failure
     */
    public String deleteTeam(int teamId) {
        Team team = searchTeam(teamId);
        if (team != null) {

            // Team must not be used in Project or StudentAward
            if (!isTeamInProject(teamId) && !isTeamInStudentAwards(teamId)) {

                DatabaseHelper databaseHelper = new DatabaseHelper();
                Session session = null;

                try {
                    databaseHelper.setup();
                    session = databaseHelper.getSessionFactory().openSession();
                    session.beginTransaction();

                    session.remove(team); //delete from DB

                    session.getTransaction().commit();

                    //  Remove from local list
                    Team localTeam = searchTeam(teamId);
                    if (localTeam != null) {
                        teams.remove(localTeam);
                    }
                    return "Record successfully deleted.";

                } catch (Exception e) {
                    if (session != null && session.getTransaction().isActive()) {
                        session.getTransaction().rollback();
                    }
                    return "ERROR: Failed to delete the record. Reason: " + e.getMessage();

                } finally {
                    if (session != null) {
                        session.close();
                    }
                    databaseHelper.exit();
                }

            } else {
                return "ERROR: Team cannot be deleted because there are existing records in the Project or StudentAward table";
            }

        } else {
            return "ERROR: Team not found";
        }
    }


    // =============================================
    // BGN CRUD METHODS - USER, TEACHER AND STUDENT
    // =============================================

    /**
     * Creates a new Teacher and saves it to the database.
     *
     * @param name     The name of the teacher. Must not be empty and no longer than
     *                 100 characters.
     * @param login    The login of the teacher. Must be unique and no longer than
     *                 50 characters.
     * @param password The password of the teacher. Must not be empty and no longer
     *                 than 255 characters.
     * @return "Success" if the teacher was successfully created and saved, or an
     *         error message if any validation fails.
     */
    public String createTeacher(String name, String login, String password) {
        if (name == null || name.isEmpty())
            return "ERROR: Name is empty!";
        if (login == null || login.isEmpty())
            return "ERROR: Login is empty!";
        if (password == null || password.isEmpty())
            return "ERROR: Password is empty!";
        if (name.length() > 100)
            return "ERROR: Name is too long!";
        if (login.length() > 50)
            return "ERROR: Login is too long!";
        if (password.length() > 255)
            return "ERROR: Password is too long!";

        // Check if login already exists
        if (findUserByLogin(login) != null) {
            return "ERROR: Login already exists!";
        }

        Teacher newTeacher = new Teacher(name, login, password);
        users.add(newTeacher);

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(newTeacher);
            transaction.commit();
            return "Success";
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return "ERROR: Database error - " + e.getMessage();
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Creates a new Student and saves it to the database.
     *
     * @param name            The name of the student. Must not be empty and no
     *                        longer than 100 characters.
     * @param login           The login of the student. Must be unique and no longer
     *                        than 50 characters.
     * @param password        The password of the student. Must not be empty and no
     *                        longer than 255 characters.
     * @param studentNumber   The student number. Must be unique and no longer than
     *                        20 characters.
     * @param currentSemester The current semester of the student. Must be at least
     *                        1.
     * @return "Success" if the student was successfully created and saved, or an
     *         error message if any validation fails.
     */
    public String createStudent(String name, String login, String password, String studentNumber,
            Integer currentSemester) {
        if (name == null || name.isEmpty())
            return "ERROR: Name is empty!";
        if (login == null || login.isEmpty())
            return "ERROR: Login is empty!";
        if (password == null || password.isEmpty())
            return "ERROR: Password is empty!";
        if (studentNumber == null || studentNumber.isEmpty())
            return "ERROR: Student number is empty!";
        if (currentSemester == null || currentSemester < 1)
            return "ERROR: Current semester must be at least 1!";
        if (name.length() > 100)
            return "ERROR: Name is too long!";
        if (login.length() > 50)
            return "ERROR: Login is too long!";
        if (password.length() > 255)
            return "ERROR: Password is too long!";
        if (studentNumber.length() > 20)
            return "ERROR: Student number is too long!";

        // Check if login already exists
        if (findUserByLogin(login) != null) {
            return "ERROR: Login already exists!";
        }

        // Check if student number already exists
        if (findStudentByNumber(studentNumber) != null) {
            return "ERROR: Student number already exists!";
        }

        Student newStudent = new Student(name, login, password, studentNumber, currentSemester);
        users.add(newStudent);

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(newStudent);
            transaction.commit();
            return "Success";
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return "ERROR: Database error - " + e.getMessage();
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }
    /**
     * Loads all User objects from the database and stores them in the local users list.
     * Uses JPQL to query the database.
     */
    public void readAllUserWithJplq() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            List<User> userList = session.createQuery("FROM User", User.class).getResultList();
            users = new ArrayList<>(userList);
            System.out.println("✓ Loaded " + users.size() + " users from database");

        } catch (Exception e) {
            System.err.println("ERROR loading users: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Checks if a user can be deleted (not linked to any other entities)
     * Uses the global arrays (courses, teams, studentsAwards) to check for references
     *
     * @param userId ID of the user to check
     * @return true if can be deleted, false otherwise
     */
    public boolean canDeleteUser(long userId) {
        User user = searchUser(userId);
        if (user == null) {
            return false;
        }

        if (user instanceof Teacher) {
            System.out.println("Checking deletability for Teacher: " + user.getName());

            // Check if teacher is assigned to any course (via CourseTeacher)
            for (Course course : courses) {
                for (CourseTeacher ct : course.getCourseTeachers()) {
                    if (ct.getTeacher().getUserId() == userId) {
                        System.out.println("Teacher is assigned to course: " + course.getCourseName());
                        return false;
                    }
                }
            }

            // Check if teacher has assigned any awards (via StudentAward)
            for (StudentAward sa : studentsAwards) {
                if (sa.getTeacher().getUserId() == userId) {
                    System.out.println("Teacher has assigned student award: " + sa.getAward().getAwardName());
                    return false;
                }
            }

            System.out.println("Teacher can be deleted.");
            return true;

        } else if (user instanceof Student) {
            System.out.println("Checking deletability for Student: " + user.getName());

            // Check if student is member of any team
            for (Team team : teams) {
                for (TeamMember tm : team.getTeamMember()) {
                    if (tm.getStudent().getUserId() == userId) {
                        System.out.println("Student is member of team: " + team.getTeamName());
                        return false;
                    }
                }
            }

            // Check if student has any awards
            for (StudentAward sa : studentsAwards) {
                if (sa.getStudent().getUserId() == userId) {
                    System.out.println("Student has award: " + sa.getAward().getAwardName());
                    return false;
                }
            }

            System.out.println("Student can be deleted.");
            return true;
        }

        return true;
    }

    /**
     * Loads all Teacher objects from the database and stores them in the local
     * users list.
     * Uses JPQL to query the database.
     */
    public void readAllTeacherWithJplq() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            List<Teacher> teacherList = session.createQuery("FROM Teacher", Teacher.class).getResultList();
            // Add teachers to users list (if not already present)
            for (Teacher teacher : teacherList) {
                if (!users.contains(teacher)) {
                    users.add(teacher);
                }
            }
            System.out.println("✓ Loaded " + teacherList.size() + " teachers from database");
        } catch (Exception e) {
            System.err.println("ERROR loading teachers: " + e.getMessage());
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Loads all Student objects from the database and stores them in the local
     * users list.
     * Uses JPQL to query the database.
     */
    public void readAllStudentWithJplq() {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            List<Student> studentList = session.createQuery("FROM Student", Student.class).getResultList();
            // Add students to users list (if not already present)
            for (Student student : studentList) {
                if (!users.contains(student)) {
                    users.add(student);
                }
            }
            System.out.println("✓ Loaded " + studentList.size() + " students from database");
        } catch (Exception e) {
            System.err.println("ERROR loading students: " + e.getMessage());
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Updates an existing Teacher with the specified ID.
     *
     * @param userId   The ID of the teacher to update.
     * @param name     The new name of the teacher. Must not be empty and no longer
     *                 than 100 characters.
     * @param login    The new login of the teacher. Must be unique and no longer
     *                 than 50 characters.
     * @param password The new password of the teacher. Must not be empty and no
     *                 longer than 255 characters.
     * @return "Success" if the teacher was successfully updated, or an error
     *         message if validation fails or the teacher does not exist.
     */
    public String updateTeacher(long userId, String name, String login, String password) {
        User user = searchUser(userId);
        if (user == null || !(user instanceof Teacher)) {
            return "ERROR: Teacher with this id does not exist";
        }

        Teacher teacher = (Teacher) user;

        if (name == null || name.isEmpty())
            return "ERROR: Name is empty!";
        if (login == null || login.isEmpty())
            return "ERROR: Login is empty!";
        if (password == null || password.isEmpty())
            return "ERROR: Password is empty!";
        if (name.length() > 100)
            return "ERROR: Name is too long!";
        if (login.length() > 50)
            return "ERROR: Login is too long!";
        if (password.length() > 255)
            return "ERROR: Password is too long!";

        // Check if login already exists for another user
        User existingUser = findUserByLogin(login);
        if (existingUser != null && existingUser.getUserId() != userId) {
            return "ERROR: Login already exists for another user!";
        }

        teacher.setName(name);
        teacher.setLogin(login);
        teacher.setPassword(password);

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(teacher);
            transaction.commit();
            return "Success";
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return "ERROR: Database error - " + e.getMessage();
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Updates an existing Student with the specified ID.
     *
     * @param userId          The ID of the student to update.
     * @param name            The new name of the student. Must not be empty and no
     *                        longer than 100 characters.
     * @param login           The new login of the student. Must be unique and no
     *                        longer than 50 characters.
     * @param password        The new password of the student. Must not be empty and
     *                        no longer than 255 characters.
     * @param studentNumber   The new student number. Must be unique and no longer
     *                        than 20 characters.
     * @param currentSemester The new current semester of the student. Must be at
     *                        least 1.
     * @return "Success" if the student was successfully updated, or an error
     *         message if validation fails or the student does not exist.
     */
    public String updateStudent(long userId, String name, String login, String password, String studentNumber,
            Integer currentSemester) {
        User user = searchUser(userId);
        if (user == null || !(user instanceof Student)) {
            return "ERROR: Student with this id does not exist";
        }

        Student student = (Student) user;

        if (name == null || name.isEmpty())
            return "ERROR: Name is empty!";
        if (login == null || login.isEmpty())
            return "ERROR: Login is empty!";
        if (password == null || password.isEmpty())
            return "ERROR: Password is empty!";
        if (studentNumber == null || studentNumber.isEmpty())
            return "ERROR: Student number is empty!";
        if (currentSemester == null || currentSemester < 1)
            return "ERROR: Current semester must be at least 1!";
        if (name.length() > 100)
            return "ERROR: Name is too long!";
        if (login.length() > 50)
            return "ERROR: Login is too long!";
        if (password.length() > 255)
            return "ERROR: Password is too long!";
        if (studentNumber.length() > 20)
            return "ERROR: Student number is too long!";

        // Check if login already exists for another user
        User existingUser = findUserByLogin(login);
        if (existingUser != null && existingUser.getUserId() != userId) {
            return "ERROR: Login already exists for another user!";
        }

        // Check if student number already exists for another student
        Student existingStudent = findStudentByNumber(studentNumber);
        if (existingStudent != null && existingStudent.getUserId() != userId) {
            return "ERROR: Student number already exists for another student!";
        }

        student.setName(name);
        student.setLogin(login);
        student.setPassword(password);
        student.setStudentNumber(studentNumber);
        student.setCurrentSemester(currentSemester);

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(student);
            transaction.commit();
            return "Success";
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            return "ERROR: Database error - " + e.getMessage();
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Deletes a User (Teacher or Student) with the specified ID.
     * Only allowed if the user is not linked to any other entities.
     * Removes from both database and in-memory arrays.
     *
     * @param userId The ID of the user to delete.
     * @return "Success" if the user was successfully deleted, or an error message
     *         if the user does not exist, has references, or deletion fails.
     */
    public String deleteUser(long userId) {
        // First check in memory using global arrays
        if (!canDeleteUser(userId)) {
            return "ERROR: Cannot delete user because they are linked to other entities " +
                    "(assigned to courses, member of teams, or has/has assigned awards).";
        }

        User user = searchUser(userId);
        if (user == null) {
            return "ERROR: User with this id does not exist";
        }

        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = null;
        Transaction transaction = null;

        try {
            session = databaseHelper.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Load the user from database to ensure we have a managed entity
            User userToDelete = session.get(User.class, userId);
            if (userToDelete == null) {
                transaction.rollback();
                return "ERROR: User with this id does not exist in database";
            }

            // Remove the user (this will cascade delete if CascadeType.ALL is set)
            session.remove(userToDelete);
            transaction.commit();

            // Remove from the in-memory array (important!)
            users.remove(user);

            System.out.println("✓ User deleted successfully from DB and memory: " + userId);
            return "Success";

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return "ERROR: Cannot delete user - " + e.getMessage() +
                    ". User might be referenced by other entities (database constraint).";
        } finally {
            if (session != null) {
                session.close();
            }
            databaseHelper.exit();
        }
    }

    // =============================================
    // HELPER METHODS
    // =============================================

    /**
     * Finds a user by login.
     *
     * @param login The login to search for.
     * @return The User object if found, null otherwise.
     */
    private User findUserByLogin(String login) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            return session.createQuery("FROM User WHERE login = :login", User.class)
                    .setParameter("login", login)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    /**
     * Finds a student by student number.
     *
     * @param studentNumber The student number to search for.
     * @return The Student object if found, null otherwise.
     */
    private Student findStudentByNumber(String studentNumber) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.setup();
        Session session = databaseHelper.getSessionFactory().openSession();

        try {
            return session.createQuery("FROM Student WHERE studentNumber = :number", Student.class)
                    .setParameter("number", studentNumber)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
            databaseHelper.exit();
        }
    }

    // =============================================
    // END OF CRUD METHODS - USER, TEACHER AND STUDENT
    // =============================================
}