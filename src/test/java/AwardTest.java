import UPT_SQ.EduScrumAwards.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AwardTest {

    private Award a1;
    private Award a2;
    private Award a3;
    private Award a4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("Test Award Class");
    }

    @BeforeEach
    public void setup() {
        a1 = new Award();
        a2 = new Award(
                "Participation Award",
                "Awarded for participating in the event",
                10,
                AwardType.MANUAL,
                AssignMode.INDIVIDUAL
        );

        ArrayList<AwardRule> rules = new ArrayList<>();
        rules.add(new AwardRule());

        a3  = new Award(
                2,
                "Achievement Award",
                "Awarded for achieving goals",
                50,
                AwardType.AUTOMATIC,
                AssignMode.TEAM,
                rules
        );
    }

    @Test
    public void testCreateAward() {
        assertNull(a4);
        assertNotNull(a1);
        assertNotNull(a2);
        assertNotNull(a3);
    }

    @Test
    public void testAwardFields() {
        assertEquals(2, a3.getAwardID());
        assertEquals("Achievement Award", a3.getAwardName());
        assertEquals("Awarded for achieving goals", a3.getAwardDescription());
        assertEquals(50, a3.getPointsValue());
        assertEquals(AwardType.AUTOMATIC, a3.getAssignType());
        assertEquals(AssignMode.TEAM, a3.getAssignMode());
        assertEquals(1, a3.getAwardRules().size());
    }

    @Test
    public void testAwardSets() {
        a1.setAwardID(1);
        a1.setAwardName("Excellence Award");
        a1.setAwardDescription("Awarded for outstanding performance");
        a1.setPointsValue(100);
        a1.setAssignType(AwardType.MANUAL);
        a1.setAssignMode(AssignMode.TEAM);
        a1.setAwardRules(new ArrayList<>());

        assertEquals(1, a1.getAwardID());
        assertEquals("Excellence Award", a1.getAwardName());
        assertEquals("Awarded for outstanding performance", a1.getAwardDescription());
        assertEquals(100, a1.getPointsValue());
        assertEquals(AwardType.MANUAL, a1.getAssignType());
        assertEquals(AssignMode.TEAM, a1.getAssignMode());
        assertNotNull(a1.getAwardRules());
        assertEquals(0, a1.getAwardRules().size());
    }


    /**
     * Tests the searchAwardRule method in the Award class.
     * Ensures that the correct rule is found by ID or null is returned if not found.
     */
    @Test
    public void testSearchAwardRule() {
        // Create AwardRules with assigned IDs
        AwardRule rule1 = new AwardRule();
        rule1.setRuleId(1);
        AwardRule rule2 = new AwardRule();
        rule2.setRuleId(2);
        AwardRule rule3 = new AwardRule();
        rule3.setRuleId(3);

        // Add them to the Award's list
        ArrayList<AwardRule> ruleList = new ArrayList<>();
        ruleList.add(rule1);
        ruleList.add(rule2);
        ruleList.add(rule3);

        a1.setAwardRules(ruleList);

        // Case 1: existing rule
        AwardRule found = a1.searchAwardRule(2);
        assertNotNull(found, "Rule with ID 2 should be found");
        assertEquals(2, found.getRuleId());

        // Case 2: first and last rule
        assertEquals(rule1, a1.searchAwardRule(1));
        assertEquals(rule3, a1.searchAwardRule(3));

        // Case 3: non-existing rule
        AwardRule notFound = a1.searchAwardRule(99);
        assertNull(notFound, "Non-existing rule should return null");
    }

    @Test
    public void testToString() {
        Award award = new Award();
        award.setAwardID(5);

        String output = award.toString();

        assertTrue(output.contains("awardID=5"));
        assertTrue(output.contains("Award{"));
    }


    @Test
    void testRuleFound() {
        // Arrange
        ArrayList<StudentAward> awards = new ArrayList<>();
        StudentAward sa1 = new StudentAward();
        AwardRule ar1 = new AwardRule();
        ar1.setRuleId(1);
        sa1.setRule(ar1);

        StudentAward sa2 = new StudentAward();
        AwardRule ar2 = new AwardRule();
        ar2.setRuleId(2);
        sa2.setRule(ar2);

        awards.add(sa1);
        awards.add(sa2);

        Award award = new Award();

        assertTrue(award.isRuleInStudentAward(2, awards));
    }

    @Test
    void testRuleNotFound() {
        ArrayList<StudentAward> awards = new ArrayList<>();
        StudentAward sa1 = new StudentAward();
        AwardRule ar1 = new AwardRule();
        ar1.setRuleId(1);
        sa1.setRule(ar1);

        StudentAward sa2 = new StudentAward();
        AwardRule ar2 = new AwardRule();
        ar2.setRuleId(3);
        sa2.setRule(ar2);

        awards.add(sa1);
        awards.add(sa2);

        Award award = new Award();

        assertFalse(award.isRuleInStudentAward(2, awards));
    }

    @Test
    void testNullRuleEntriesHandled() {
        ArrayList<StudentAward> awards = new ArrayList<>();
        StudentAward sa1 = new StudentAward();
        AwardRule ar1 = new AwardRule();
        ar1.setRuleId(5);
        sa1.setRule(ar1);

        awards.add(new StudentAward());
        awards.add(sa1);

        Award award = new Award();

        assertFalse(award.isRuleInStudentAward(2, awards));
    }

    @Test
    void testEmptyListReturnsFalse() {
        ArrayList<StudentAward> awards = new ArrayList<>();

        Award award = new Award();

        assertFalse(award.isRuleInStudentAward(2, awards));
    }
}
