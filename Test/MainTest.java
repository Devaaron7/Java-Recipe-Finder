import java.io.IOException;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void main() throws IOException, InterruptedException {
        //String Password = "123";

        Field S = Input.term();

        assertEquals(Input.term().getT, Password);



    }
}