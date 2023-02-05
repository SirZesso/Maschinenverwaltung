package model.machine;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.*;

import model.Enterprise;
import model.customer.Area;

public class Laser extends ProcessCell{


private IntegerProperty wavelength;

public Laser() {
    this.wavelength = new SimpleIntegerProperty();
}

public Laser(int serialnumber, String name, Enterprise manufacturer, Enterprise customer,MachineType type, int wavelength) {
    super(serialnumber, name, manufacturer,customer, type);
    this.wavelength = new SimpleIntegerProperty(wavelength);
}
public Laser(int serialnumber, String name, Enterprise manufacturer, Enterprise customer, MachineType type, String imagePath, int wavelength) {
    super(serialnumber, name, manufacturer, customer, type, imagePath);
    this.wavelength = new SimpleIntegerProperty(wavelength);
}
public Laser(int serialnumber, String name, Enterprise manufacturer, Enterprise customer, MachineType type, String imagePath, Area area, int wavelength) {
    super(serialnumber, name, manufacturer, customer, type, imagePath, area);
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
@Override
public void writeExternal(ObjectOutput out) throws IOException {
    out.writeInt(getSerialnumber());
    out.writeObject(getName());
    out.writeObject(getManufacturer());
    out.writeObject(getCustomer());
    out.writeObject(getType());
    out.writeObject(getImagePath());
    out.writeInt(getWavelength());
    out.writeObject(getArea());
}

@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    setSerialnumber(in.readInt());
    setName((String) in.readObject());
    setManufacturer((Enterprise) in.readObject());
    setCustomer((Enterprise) in.readObject());
    setType((MachineType) in.readObject());
    setImagePath((String) in.readObject());
    setWavelength(in.readInt());
    setArea((Area) in.readObject());
}

}