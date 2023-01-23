package model.customer;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Area {
    private String name;
    private String description;
    private String siteId;
    private Floor floor;
    private double surface;
    private String manager;
    private List<String> processCells;

}
