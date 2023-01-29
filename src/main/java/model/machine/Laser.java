package model.machine;

import javafx.beans.property.*;

import model.Enterprise;

public class Laser extends ProcessCell {


private IntegerProperty wavelength;



public Laser() {
    this.wavelength = new SimpleIntegerProperty();
}

public Laser(int serialnumber, String name, Enterprise manufacturer, Enterprise customer,MachineType type, int wavelength) {
    super(serialnumber, name, manufacturer,customer, type);
    this.wavelength = new SimpleIntegerProperty(wavelength);
}

public int getWavelength() {
    return wavelength.get();
}

public void setWavelength(int wavelength) {
    this.wavelength.set(wavelength);
}

public IntegerProperty wavelengthProperty() {
    return wavelength;
}

}