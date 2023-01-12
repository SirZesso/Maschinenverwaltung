package model;

import java.util.Map;

public class Area {
    private String description;
    private int floor;
    private String name;
    private double surface;
    // private layout Picture;
    private String manager;
    private Map<String, String> processCells;

    public Area(String description, int floor, String name, double surface, String manager,
            Map<String, String> processCells) {
        this.description = description;
        this.floor = floor;
        this.name = name;
        this.surface = surface;
        this.manager = manager;
        this.processCells = processCells;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Map<String, String> getProcessCells() {
        return processCells;
    }

    public void setProcessCells(Map<String, String> processCells) {
        this.processCells = processCells;
    }

}
