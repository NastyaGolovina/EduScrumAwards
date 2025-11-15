import UPT_SQ.EduScrumAwards.model.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTest {
    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("---Test UserRole Enum---");
    }

    @Test
    public void testUserRoleValues() {
        assertEquals("TEACHER", UserRole.TEACHER.name());
        assertEquals("STUDENT", UserRole.STUDENT.name());
    }

    @Test
    public void testUserRoleValueOf() {
        assertEquals(UserRole.TEACHER, UserRole.valueOf("TEACHER"));
        assertEquals(UserRole.STUDENT, UserRole.valueOf("STUDENT"));
    }

    @Test
    public void testUserRoleOrdinal() {
        assertEquals(0, UserRole.TEACHER.ordinal());
        assertEquals(1, UserRole.STUDENT.ordinal());
    }
}
