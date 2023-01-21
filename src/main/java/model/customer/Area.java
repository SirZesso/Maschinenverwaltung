package model.customer;

import java.util.List;

import lombok.Data;

@Data
public class Area {
    private String name;
    private String description;
    private Site site;
    private Floor floor;
    private double surface;
    private String manager;
    private List<String> processCells;

}
