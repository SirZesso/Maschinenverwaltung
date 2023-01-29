package model.machine;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Enterprise;


public class Press extends ProcessCell {


private IntegerProperty newton;

public Press() {
    super();
}

public Press(int serialnumber, String name, Enterprise manufacturer, Enterprise customer,String type, int newton) {
    super(serialnumber, name, manufacturer,customer, type);
    this.newton = new SimpleIntegerProperty(newton);
}

public int getNewton() {
    return newton.get();
}

public void setNewton(int newton) {
    this.newton = new SimpleIntegerProperty();
}

public IntegerProperty newtonProperty() {
    return newton;
}

@Override
public void writeExternal(ObjectOutput out) throws IOException {
    out.writeInt(getSerialnumber());
    out.writeObject(getName());
    out.writeObject(getType());
    out.writeInt(getNewton());
}

@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    setId(in.readInt());
    setName((String) in.readObject());
    setType((String) in.readObject());
    setNewton(in.readInt());
}
}
