package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.customer.Area;
import model.customer.Floor;

public class AreaController {
    // this class is prepared to be replaced by a db controller
    // TODO persist the list locally
    private List<Area> areas = new ArrayList<>();

    private String dataPath = "C:/Temp/Maschinenverwaltung/";

    public void initDemoAreas() {
        areas.add(new Area("T0 Montagepark", "Automatisierte Montage von T0 Komponenten", "W01", Floor.EG, 643.00,
                "Sascha Seepferd", null));
        areas.add(new Area("LaserXD", "Manuelle visuelle Kontrolle von Lasergravuren", "W02", Floor.OG3, 643.00,
                "Nina Nielpferd", null));
    }

    // CRUD methods
    // TODO create a test class that tests these functions
    public void createArea(Area area) {
        try {
            FileOutputStream f = new FileOutputStream(dataPath + "Areas/" + area.getId() + ".area");
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(area);

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public Area readArea(String areaID) throws ClassNotFoundException, IOException {
        FileInputStream fi = new FileInputStream(new File(dataPath + "Areas/" + areaID));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        Area userClassic = (Area) oi.readObject();
        oi.close();
        fi.close();
        return userClassic;
    }

    public void updateArea(int index, Area area) {
        areas.set(index, area);
    }

    public void deleteArea(int index) {
        areas.remove(index);
    }

    public Area getAreaByIndex(int index) {
        return areas.get(index);
    }

    public List<String> getCompleteAreaNameList() {
        List<String> areaNames = new ArrayList<>();
        for (Area a : areas) {
            areaNames.add(a.getName());
        }
        return areaNames;
    }

    public List<String> getAreaNameList(String siteID) {
        List<String> areaNames = new ArrayList<>();

        for (Area a : areas) {
            if (a.getSiteId().equals(siteID)) {
                areaNames.add(a.getName());
            }
        }
        return areaNames;
    }

}
