package model.machine;

import javafx.beans.property.*;
import model.Enterprise;


public class Press extends ProcessCell {


private IntegerProperty newton;

public Press() {
    this.newton = new SimpleIntegerProperty();
}

public Press(int serialnumber, String name, Enterprise manufacturer, Enterprise customer, MachineType type, int newton) {
    super(serialnumber, name, manufacturer, customer, type);
    this.newton = new SimpleIntegerProperty(newton);
}

public int getNewton() {
    return newton.get();
}

public void setNewton(int newton) {
    this.newton.set(newton);
}

public IntegerProperty newtonProperty() {
    return newton;
}

}
