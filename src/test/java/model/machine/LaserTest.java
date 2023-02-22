package model.machine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



public class LaserTest {

    @Test
    public void testGetWavelength() {
        Laser laser = new Laser(1, "TestLaser", null, null, null, null, null, 100);
        assertEquals(100, laser.getWavelength());
    }
    
    @Test
    public void testSetWavelength() {
        Laser laser = new Laser(1, "TestLaser", null, null, null, null, null, 100);
        laser.setWavelength(200);
        assertEquals(200, laser.getWavelength());
    }
    
    @Test
    public void testWavelengthProperty() {
        Laser laser = new Laser(1, "TestLaser", null, null, null, null, null, 100);
        assertEquals(100, laser.wavelengthProperty().get());
        laser.wavelengthProperty().set(200);
        assertEquals(200, laser.wavelengthProperty().get());
    }
}
