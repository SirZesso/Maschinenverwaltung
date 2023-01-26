package model.customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.IntegerProperty;

@XmlRootElement
public class Site {
    private StringProperty id;
    private StringProperty street;
    private StringProperty city;
    private IntegerProperty postalcode;

    public Site(String id, String street, String city, int postalcode) {
        this.id = new SimpleStringProperty(id);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.postalcode = new SimpleIntegerProperty(postalcode);
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

    public StringProperty streetProperty() {
        return street;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public IntegerProperty postalcodeProperty() {
        return postalcode;
    }

    public int getPostalcode() {
        return postalcode.get();
    }

    public void setPostalcode(int postalcode) {
        this.postalcode.set(postalcode);
    }
}
