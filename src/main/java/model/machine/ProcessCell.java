package model.machine;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Enterprise;

public class ProcessCell implements Externalizable {

    private IntegerProperty serialnumber;
    private StringProperty name;
    private ObjectProperty<Enterprise> manufacturer;
    private ObjectProperty<Enterprise> customer;
    private StringProperty type;

    public ProcessCell() {
        this.serialnumber = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.manufacturer = new SimpleObjectProperty<>();
        this.customer = new SimpleObjectProperty<>();
        this.type = new SimpleStringProperty();
    }

    public ProcessCell(int serialnumber, String name, Enterprise manufacturer, Enterprise customer, String type) {
        this.serialnumber = new SimpleIntegerProperty(serialnumber);
        this.name = new SimpleStringProperty(name);
        this.manufacturer = new SimpleObjectProperty<>(manufacturer);
        this.customer = new SimpleObjectProperty<>(customer);
        this.type = new SimpleStringProperty(type);
    }

    public int getSerialnumber() {
        return serialnumber.get();
    }

    public void setId(int serialnumber) {

        this.serialnumber.set(serialnumber);
    }

    public IntegerProperty idProperty() {
        return serialnumber;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Enterprise getManufacturer() {
        return manufacturer.get();
    }

    public void setManufacturer(Enterprise manufacturer) {
        this.manufacturer.set(manufacturer);
    }

    public ObjectProperty<Enterprise> manufacturerProperty() {
        return manufacturer;
    }

    public Enterprise getCustomer() {
        return customer.get();
    }

    public void setCustomer(Enterprise customer) {
        this.customer.set(customer);
    }

    public ObjectProperty<Enterprise> customerProperty() {
        return customer;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getSerialnumber());
        out.writeObject(getName());
        out.writeObject(getManufacturer());
        out.writeObject(getCustomer());
        out.writeObject(getType());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readInt());
        setName((String) in.readObject());
        setManufacturer((Enterprise) in.readObject());
        setCustomer((Enterprise) in.readObject());
        setType((String) in.readObject());
    }
}