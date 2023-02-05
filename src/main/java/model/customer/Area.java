package model.customer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Area implements Externalizable {
    private StringProperty siteId;
    private StringProperty name;
    private StringProperty description;
    private ObjectProperty<Floor> floor;
    private DoubleProperty surface;
    private StringProperty manager;

    public Area() {
        
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.siteId = new SimpleStringProperty();
        this.floor = new SimpleObjectProperty<>();
        this.surface = new SimpleDoubleProperty();
        this.manager = new SimpleStringProperty();
        
    }

    public Area(String name, String description, String siteId, Floor floor, double surface, String manager) {

        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.siteId = new SimpleStringProperty(siteId);
        this.floor = new SimpleObjectProperty<>(floor);
        this.surface = new SimpleDoubleProperty(surface);
        this.manager = new SimpleStringProperty(manager);
    }

    public String getSiteId() {
        return siteId.get();
    }

    public void setSiteId(String siteId) {
        this.siteId.set(siteId);
    }

    public StringProperty siteIdProperty() {
        return siteId;
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

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public Floor getFloor() {
        return floor.get();
    }

    public void setFloor(Floor floor) {
        this.floor.set(floor);
    }

    public ObjectProperty<Floor> floorProperty() {
        return floor;
    }

    public double getSurface() {
        return surface.get();
    }

    public void setSurface(double surface) {
        this.surface.set(surface);
    }

    public DoubleProperty surfaceProperty() {
        return surface;
    }

    public String getManager() {
        return manager.get();
    }

    public void setManager(String manager) {
        this.manager.set(manager);
    }

    public StringProperty managerProperty() {
        return manager;
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getSiteId());
        out.writeObject(getName());
        out.writeObject(getDescription());
        out.writeObject(getFloor());
        out.writeObject(getSurface());
        out.writeObject(getManager());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setSiteId((String)in.readObject());
        setName((String) in.readObject());
        setDescription((String) in.readObject());
        setFloor((Floor) in.readObject());
        setSurface((Double) in.readObject());
        setManager((String) in.readObject());
    }
}