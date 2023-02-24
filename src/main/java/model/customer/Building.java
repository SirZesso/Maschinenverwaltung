package model.customer;

import java.util.List;

import lombok.Data;

@Data
public class Building {
    private String name;
    private List<Floor> floors;

    public Building(String name, List<Floor> floors) {
        this.name = name;
        this.floors = floors;
    }
}
