import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testSayHello() {
        User user = new User();
        String expected = "Hello world";
        String actual = user.sayHello(" world");
        assertEquals(expected, actual);
    }

}
