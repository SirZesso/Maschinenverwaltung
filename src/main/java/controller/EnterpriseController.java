package controller;

import java.util.ArrayList;
import java.util.List;

import model.Enterprise;
import model.customer.Site;

public class EnterpriseController {
    Enterprise customer;
    Enterprise supplier;

    public void initDemoCustomer() {
        List<Site> demoSites = new ArrayList<>();
        demoSites.add(new Site("W01", "Schild-Ruststrasse 16", "Grenchen", 2540));
        demoSites.add(new Site("W02", "Oelirein 3", "Grenchen", 2540));
        demoSites.add(new Site("W04", "Blumenrainstrasse 1", "Grenchen", 2540));
        demoSites.add(new Site("W06", "Storchengasse 9", "Grenchen", 2540));

        customer = new Enterprise("ETA SA Manufacture Horlogère Suisse", null,
                demoSites);
    }

    // In the future this controller can be used to load the configured enterprise
    // B2B version. Currently only one supplier and one enterprise are supportet but
    // with this architecture it can be easily adapted to support multiple
    // enterprises

    public Enterprise getCustomer() {
        return customer;
    }

    public List<String> getSiteNames() {
        List<String> siteNames = new ArrayList<>();

        for (Site s : customer.getSites()) {
            siteNames.add(s.getId());
        }

        return siteNames;
    }

    public Enterprise getSupplier() {
        return supplier;
    }
}
