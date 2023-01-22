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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }


}
