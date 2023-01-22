package model.machine;

import lombok.Data;
import model.Enterprise;

@Data
public abstract class ProcessCell {
    private int id;
    private String name;
    private Enterprise manufacturer;
    private Enterprise customer;
    private String type;
    
    public ProcessCell(int id, String name, Enterprise manufacturer, Enterprise customer, String type) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.customer = customer;
        this.type = type;
    }

}
