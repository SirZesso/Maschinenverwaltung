package controller;

import java.util.ArrayList;
import java.util.List;

import model.customer.Area;

public class AreaController {

    // this class is prepared to be replaced by a db controller
    List<Area> areas = new ArrayList<>();

    public void initDemoAreas() {
        // areas.add(new Area("T0 Montagepark", "Automatisierte Montage von T0
        // Komponenten", null, Floor.EG, 643.00,
        // "Sascha Seepferd", null));
        // areas.add(new Area("LaserXD", "Manuelle visuelle Kontrolle von
        // Lasergravuren", null, Floor.OG3, 643.00,
        // "Nina Nielpferd", null));
    }

    // CRUD methods

    public void createArea(Area area) {
        areas.add(area);
    }

    public void readArea(int index) {
        areas.get(index);
    }

    public void updateArea(int index, Area area) {
        areas.set(index, area);
    }

    public void deleteArea(int index) {
        areas.remove(index);
    }

}
