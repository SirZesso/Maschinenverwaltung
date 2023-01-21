package model.customer;

import lombok.Data;

@Data
public class Site {

    private String id;
    private String street;
    private String city;
    private int postalcode;

    public Site(String id, String street, String city, int postalcode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalcode = postalcode;
    }

}
