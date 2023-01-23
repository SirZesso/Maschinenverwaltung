package model.machine;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Enterprise;

public class Laser extends ProcessCell {


private IntegerProperty wavelength;



public Laser() {
    this.wavelength = new SimpleIntegerProperty();
}

public Laser(int id, String name, Enterprise manufacturer, Enterprise customer, String type, int wavelength) {
    super(id, name, manufacturer,customer, type);
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
    out.writeInt(getId());
    out.writeObject(getName());
    out.writeObject(getType());
    out.writeInt(getWavelength());
}

@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    setId(in.readInt());
    setName((String) in.readObject());
    setType((String) in.readObject());
    setWavelength(in.readInt());
}
}