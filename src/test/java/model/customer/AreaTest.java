package model.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setUp() {
        area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
    }

    @Test
    public void testGetSiteId() {
        setUp();
        Assertions.assertEquals("1", area.getSiteId());
    }

    @Test
    public void testSetSiteId() {
        area.setSiteId("2");
        Assertions.assertEquals("2", area.getSiteId());
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("Area1", area.getName());
    }

    @Test
    public void testSetName() {
        area.setName("Area2");
        Assertions.assertEquals("Area2", area.getName());
    }

    @Test
    public void testGetDescription() {
        Assertions.assertEquals("This is Area1", area.getDescription());
    }

    @Test
    public void testSetDescription() {
        area.setDescription("This is Area2");
        Assertions.assertEquals("This is Area2", area.getDescription());
    }

    @Test
    public void testGetFloor() {
        Assertions.assertEquals(Floor.UG2, area.getFloor());
    }

    @Test
    public void testSetFloor() {
        area.setFloor(Floor.UG1);
        Assertions.assertEquals(Floor.UG1, area.getFloor());
    }

    @Test
    public void testGetSurface() {
        Assertions.assertEquals(100.0, area.getSurface());
    }

    @Test
    public void testSetSurface() {
        area.setSurface(200.0);
        Assertions.assertEquals(200.0, area.getSurface());
    }

    @Test
    public void testGetManager() {
        Assertions.assertEquals("John", area.getManager());
    }

    @Test
    public void testSetManager() {
        area.setManager("Mary");
        Assertions.assertEquals("Mary", area.getManager());
    }

    @Test
    public void testConstructor() {
        setUp();
        Assertions.assertEquals("1", area.getSiteId());
        Assertions.assertEquals("Area1", area.getName());
        Assertions.assertEquals("This is Area1", area.getDescription());
        Assertions.assertEquals(Floor.UG2, area.getFloor());
        Assertions.assertEquals(100.0, area.getSurface());
        Assertions.assertEquals("John", area.getManager());
    }

}