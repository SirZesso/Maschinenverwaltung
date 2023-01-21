package model.machine;

import lombok.Data;
import model.Enterprise;

@Data
abstract class ProcessCell {
    private int id;
    private String name;
    private Enterprise manufacturer;
    private Enterprise customer;
    private String type;

}
