import UPT_SQ.EduScrumAwards.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SprintTest {
    @Test
    public void testConstructorAndGetters() {
        Date start = new Date();
        Date end = new Date();
        List<Goal> goals = new ArrayList<>();
        Project project = new Project();

        Sprint sprint = new Sprint(1, start, end, goals, project);

        assertEquals(1, sprint.getSprintId());
        assertEquals(start, sprint.getStartDate());
        assertEquals(end, sprint.getEndDate());
        assertEquals(goals, sprint.getGoals());
        assertEquals(project, sprint.getProject());
    }

    @Test
    public void testSecondConstructor() {
        Date start = new Date();
        Date end = new Date();
        List<Goal> goals = new ArrayList<>();
        Project project = new Project();

        Sprint sprint = new Sprint(start, end, goals, project);

        assertEquals(start, sprint.getStartDate());
        assertEquals(end, sprint.getEndDate());
        assertEquals(goals, sprint.getGoals());
        assertEquals(project, sprint.getProject());
    }
    @Test
    public void testSetters() {
        Sprint sprint = new Sprint();
        Date start = new Date();
        Date end = new Date();
        List<Goal> goals = new ArrayList<>();
        Project project = new Project();

        sprint.setSprintId(10);
        sprint.setStartDate(start);
        sprint.setEndDate(end);
        sprint.setGoals(goals);
        sprint.setProject(project);

        assertEquals(10, sprint.getSprintId());
        assertEquals(start, sprint.getStartDate());
        assertEquals(end, sprint.getEndDate());
        assertEquals(goals, sprint.getGoals());
        assertEquals(project, sprint.getProject());
    }

    @Test
    public void testCalcTotalScore() {
        Goal g1 = new Goal(1, "Goal 1", 10, true);
        Goal g2 = new Goal(2, "Goal 2", 20, false);

        List<Goal> goals = new ArrayList<>();
        goals.add(g1);
        goals.add(g2);

        Sprint sprint = new Sprint();
        sprint.setGoals(goals);

        assertEquals(30, sprint.calcTotalScore());
    }

    @Test
    public void testCalcTotalScoreEmpty() {
        Sprint sprint = new Sprint();
        sprint.setGoals(new ArrayList<>());

        assertEquals(0, sprint.calcTotalScore());
    }

    @Test
    public void testCalcCompletionByScore() {
        Goal g1 = new Goal(1, "Goal 1", 10, true);
        Goal g2 = new Goal(2, "Goal 2", 20, false);

        List<Goal> goals = new ArrayList<>();
        goals.add(g1);
        goals.add(g2);

        Sprint sprint = new Sprint();
        sprint.setGoals(goals);

        // completed = 10 out of total 30 → 33.33%
        assertEquals(33.33, sprint.calcCompletionByScore(), 0.5);
    }

    @Test
    public void testCalcCompletionByScoreZeroTotal() {
        Sprint sprint = new Sprint();
        sprint.setGoals(new ArrayList<>());

        assertEquals(0, sprint.calcCompletionByScore());
    }
    @Test
    public void testToString() {
        Sprint sprint = new Sprint();
        sprint.setSprintId(5);

        String output = sprint.toString();

        assertTrue(output.contains("sprintId=5"));
        assertTrue(output.contains("Sprint{"));
    }



    @Test
    void testIsRuleAssigned_EmptyList() {
        Sprint sprint = new Sprint();
        sprint.setSprintId(1);

        ArrayList<StudentAward> list = new ArrayList<>();
        AwardRule rule = new AwardRule();
        rule.setRuleId(10);

        assertFalse(sprint.isRuleAssigned(list, rule));
    }

    @Test
    void testIsRuleAssigned_NullSprintOrRule() {
        Sprint sprint = new Sprint();
        sprint.setSprintId(1);

        ArrayList<StudentAward> list = new ArrayList<>();
        StudentAward sa = new StudentAward();

        AwardRule rule = new AwardRule();
        rule.setRuleId(10);

        sa.setSprint(sprint);
        sa.setRule(null);
        list.add(sa);


        StudentAward sa1 = new StudentAward();
        sa1.setSprint(null);
        sa1.setRule(rule);
        list.add(sa1);



        assertFalse(sprint.isRuleAssigned(list, rule));
    }

    @Test
    void testIsRuleAssigned_SprintNotMatch() {
        Sprint sprint = new Sprint();
        sprint.setSprintId(1);

        ArrayList<StudentAward> list = new ArrayList<>();

        StudentAward sa = new StudentAward();
        sa.setRule(new AwardRule());
        sa.getRule().setRuleId(10);

        Sprint sprint1 = new Sprint();
        sprint1.setSprintId(2);
        sa.setSprint(sprint1);

        list.add(sa);

        AwardRule rule = new AwardRule();
        rule.setRuleId(10);

        assertFalse(sprint.isRuleAssigned(list, rule));
    }

    @Test
    void testIsRuleAssigned_RuleNotMatch() {
        Sprint sprint = new Sprint();
        sprint.setSprintId(1);

        ArrayList<StudentAward> list = new ArrayList<>();

        StudentAward sa = new StudentAward();
        sa.setSprint(sprint);
        sa.setRule(new AwardRule());
        sa.getRule().setRuleId(5);

        list.add(sa);

        AwardRule rule = new AwardRule();
        rule.setRuleId(10);

        assertFalse(sprint.isRuleAssigned(list, rule));
    }

    @Test
    void testIsRuleAssigned_FullMatch() {
        Sprint sprint = new Sprint();
        sprint.setSprintId(1);

        ArrayList<StudentAward> list = new ArrayList<>();

        StudentAward sa = new StudentAward();
        sa.setSprint(sprint);

        AwardRule r = new AwardRule();
        r.setRuleId(10);
        sa.setRule(r);

        list.add(sa);

        AwardRule rule = new AwardRule();
        rule.setRuleId(10);

        assertTrue(sprint.isRuleAssigned(list, rule));
    }
}

