package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.customer.Site;

import java.util.List;
import java.util.UUID;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

@XmlRootElement
public class Enterprise {
    private @XmlValue StringProperty id;
    private StringProperty name;
    private StringProperty logo_path;
    @XmlElementWrapper(name = "locations")
    @XmlElement(name = "location")
    private List<Site> sites;

    public Enterprise(String name, String logo_path, List<Site> sites) {
        this.id = new SimpleStringProperty(UUID.randomUUID().toString());
        this.name = new SimpleStringProperty(name);
        this.logo_path = new SimpleStringProperty(logo_path);
        this.sites = sites;
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty logo_pathProperty() {
        return logo_path;
    }

    public String getLogo_path() {
        return logo_path.get();
    }

    public void setLogo_path(String logo_path) {
        this.logo_path.set(logo_path);
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}