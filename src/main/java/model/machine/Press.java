package model.machine;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.*;
import model.Enterprise;
import model.customer.Area;


public class Press extends ProcessCell{


private IntegerProperty newton;

public Press() {
    this.newton = new SimpleIntegerProperty();
}

public Press(int serialnumber, String name, Enterprise manufacturer, Enterprise customer, MachineType type, String imagePath, Area area, int newton) {
    super(serialnumber, name, manufacturer, customer, type, imagePath, area);
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
@Override
public void writeExternal(ObjectOutput out) throws IOException {
    out.writeInt(getSerialnumber());
    out.writeObject(getName());
    out.writeObject(getManufacturer());
    out.writeObject(getCustomer());
    out.writeObject(getType());
    out.writeObject(getImagePath());
    out.writeObject(getArea());
    out.writeInt(getNewton());
    
}

@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    setSerialnumber(in.readInt());
    setName((String) in.readObject());
    setManufacturer((Enterprise) in.readObject());
    setCustomer((Enterprise) in.readObject());
    setType((MachineType) in.readObject());
    setImagePath((String) in.readObject());
    setArea((Area) in.readObject());
    setNewton(in.readInt());
    
}


}
