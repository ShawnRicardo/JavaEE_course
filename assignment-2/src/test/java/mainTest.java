import com.Ricardo.InitMethod;
import com.Ricardo.myClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class mainTest {

    private myClass myCalculator;


    @BeforeEach
    public void setup(){
        myCalculator = new myClass();
    }

    @Test
    public void testInit() throws Exception {
        this.myCalculator.init();
    }

    @Test
    public void testAdd() throws Exception {

        int result = myCalculator.Add(1, 2);
        assertEquals(3, result);
    }

    @Test
    public void testSub() throws Exception {

        int result = myCalculator.Sub(1, 2);
        assertEquals(-1, result);
    }

    @Test
    public void testMult() throws Exception {

        int result = myCalculator.Mult(1, 2);
        assertEquals(2, result);
    }

    @Test
    public void testDiv() throws Exception {

        assertThrows(ArithmeticException.class, () -> {
            myCalculator.Div(5, 0);
        });
    }
}
