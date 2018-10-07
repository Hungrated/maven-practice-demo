import edu.zju.cst.myproject.model.User;
import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testSayHello() {
        User user = new User("hungrated", "Hangzhou");
        String expected = "Hello world";
        String actual = user.sayHello(" world");
        assertEquals(expected, actual);
    }

}
