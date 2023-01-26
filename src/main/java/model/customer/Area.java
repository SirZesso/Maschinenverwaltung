package model.customer;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Area {
    private UUID id;
    private String name;
    private String description;
    private String siteId;
    private Floor floor;
    private double surface;
    private String manager;
    private List<String> processCells;

    public Area(String name, String description, String siteId, Floor floor, double surface, String manager,
            List<String> processCells) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.siteId = siteId;
        this.floor = floor;
        this.surface = surface;
        this.manager = manager;
        this.processCells = processCells;
    }

}
