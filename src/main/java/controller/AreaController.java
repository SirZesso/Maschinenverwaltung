package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import model.Enterprise;
import model.customer.Area;
import model.customer.Floor;
import service.SerializationService;

public class AreaController {
    private static ObservableList<Area> areas;
    private static ObservableList<Enterprise> customers;

    public void initialize() {
        areas = SerializationService.deSerializeAreaData();
        customers = SerializationService.deSerializeEnterpriseDatao("customers.ser");

        if (areas.isEmpty()) {
            createDemoAreas();
            System.out.println("New demo set created");
        }

    }

    // CRUD methods
    // TODO create a test class that tests these functions
    public void createArea(Area area) {

    }

    public Area readArea(String areaID) {
        return null;
    }

    public void updateArea(int index, Area area) {
        areas.set(index, area);
    }

    public void updateAreaPcs(int index, String processCell_id) {
        areas.get(index).addNewProcessCell(processCell_id);
        System.out.print("saved: " + processCell_id);
    }

    public void deleteArea(int index) {
        areas.remove(index);
    }

    public Area getAreaByIndex(int index) {
        return areas.get(index);
    }

    public ObservableList<Area> getCompleteAreaList() {
        return areas;
    }

    public List<String> getAreaNameList() {
        List<String> areaNames = new ArrayList<>();

        for (Area a : areas) {
            areaNames.add(a.getName());
        }
        return areaNames;
    }

    // DEMO CREATION
    public void createDemoAreas() {
        areas.add(new Area("T0 Montagepark", "Automatisierte Montage von T0 Komponenten", "W01", Floor.EG, 643.00,
                "Sascha Seepferd", null));
        areas.add(new Area("LaserXD", "Manuelle visuelle Kontrolle von Lasergravuren", "W02", Floor.OG3, 643.00,
                "Nina Nielpferd", null));
    }

}
