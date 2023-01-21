package model;

import java.util.List;
import java.util.UUID;

import model.customer.Site;

public class Enterprise {
    String id;
    String name;
    String logo_path;
    List<Site> sites;

    public Enterprise(String name, String logo_path, List<Site> sites) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.logo_path = logo_path;
        this.sites = sites;
    }

}
