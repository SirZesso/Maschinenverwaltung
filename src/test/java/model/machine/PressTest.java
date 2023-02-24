package model.machine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class PressTest {

    @Test
    public void testGetNewton() {
        Press press = new Press(1, "TestPress", null, null, null, null, null, 1000);
        assertEquals(1000, press.getNewton());
    }
    
    @Test
    public void testSetNewton() {
        Press press = new Press(1, "TestPress", null, null, null, null, null, 1000);
        press.setNewton(2000);
        assertEquals(2000, press.getNewton());
    }
    
    @Test
    public void testNewtonProperty() {
        Press press = new Press(1, "TestPress", null, null, null, null, null, 100);
        assertEquals(100, press.newtonProperty().get());
        press.newtonProperty().set(200);
        assertEquals(200, press.newtonProperty().get());
    }
}