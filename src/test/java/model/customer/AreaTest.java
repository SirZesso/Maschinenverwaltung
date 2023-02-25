package model.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AreaTest {

    @Test
    public void testGetSiteId() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals("1", area.getSiteId());
    }

    @Test
    public void testSetSiteId() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        area.setSiteId("2");
        Assertions.assertEquals("2", area.getSiteId());
    }

    @Test
    public void testGetName() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals("Area1", area.getName());
    }

    @Test
    public void testSetName() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        area.setName("Area2");
        Assertions.assertEquals("Area2", area.getName());
    }

    @Test
    public void testGetDescription() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals("This is Area1", area.getDescription());
    }

    @Test
    public void testSetDescription() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        area.setDescription("This is Area2");
        Assertions.assertEquals("This is Area2", area.getDescription());
    }

    @Test
    public void testGetFloor() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals(Floor.UG2, area.getFloor());
    }

    @Test
    public void testSetFloor() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        area.setFloor(Floor.UG1);
        Assertions.assertEquals(Floor.UG1, area.getFloor());
    }

    @Test
    public void testGetSurface() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals(100.0, area.getSurface());
    }

    @Test
    public void testSetSurface() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        area.setSurface(200.0);
        Assertions.assertEquals(200.0, area.getSurface());
    }

    @Test
    public void testGetManager() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals("John", area.getManager());
    }

    @Test
    public void testSetManager() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        area.setManager("Mary");
        Assertions.assertEquals("Mary", area.getManager());
    }

    @Test
    public void testConstructor() {
        Area area = new Area("1", "Area1", "This is Area1", Floor.UG2, 100.0, "John");
        Assertions.assertEquals("1", area.getSiteId());
        Assertions.assertEquals("Area1", area.getName());
        Assertions.assertEquals("This is Area1", area.getDescription());
        Assertions.assertEquals(Floor.UG2, area.getFloor());
        Assertions.assertEquals(100.0, area.getSurface());
        Assertions.assertEquals("John", area.getManager());
    }

}