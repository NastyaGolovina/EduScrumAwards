import UPT_SQ.EduScrumAwards.model.User;
import UPT_SQ.EduScrumAwards.model.UserRole;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User u1;
    private User u2;
    private User u4;

    @BeforeAll
    public static void onceExecutedBeforeAll() {
        System.out.println("---Test User Class---");
    }

    @BeforeEach
    public void setup() {
        u1 = new User();

        u2 = new User(
                "João Silva",
                "joao.silva",
                "senha123",
                UserRole.TEACHER
        );
    }

    @Test
    public void testCreateUser() {
        assertNull(u4);
        assertNotNull(u1);
        assertNotNull(u2);

    }

    @Test
    public void testUserFields() {
        assertEquals("João Silva", u2.getName());
        assertEquals("joao.silva", u2.getLogin());
        assertEquals("senha123", u2.getPassword());
        assertEquals(UserRole.TEACHER, u2.getRole());
        assertNull(u2.getUserId());
    }

    @Test
    public void testUserSets() {
        u1.setUserId(1L);
        u1.setName("Maria Santos");
        u1.setLogin("maria.santos");
        u1.setPassword("novaSenha456");
        u1.setRole(UserRole.STUDENT);

        assertEquals(1L, u1.getUserId());
        assertEquals("Maria Santos", u1.getName());
        assertEquals("maria.santos", u1.getLogin());
        assertEquals("novaSenha456", u1.getPassword());
        assertEquals(UserRole.STUDENT, u1.getRole());
    }

    @Test
    public void testUserToString() {
        u2.setUserId(1L);
        String toStringResult = u2.toString();

        assertTrue(toStringResult.contains("User{"));
        assertTrue(toStringResult.contains("userId=1"));
        assertTrue(toStringResult.contains("name='João Silva'"));
        assertTrue(toStringResult.contains("login='joao.silva'"));
        assertTrue(toStringResult.contains("role=TEACHER"));
    }

    @AfterAll
    public static void onceExecutedAfterAll() {
        System.out.println("---Final do Test User---");
    }
}
