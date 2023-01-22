package controller;

import java.util.ArrayList;
import java.util.List;

import model.Enterprise;
import model.customer.Site;

public class EnterpriseController {
    public List<Enterprise> customers;
    public List<Enterprise> suppliers;

    void createDemoCustomer() {
        List<Site> demoSites = new ArrayList<>();
        demoSites.add(new Site("W01", "Schild-Ruststrasse 16", "Grenchen", 2540));
        demoSites.add(new Site("W02", "Oelirein 3", "Grenchen", 2540));
        demoSites.add(new Site("W04", "Blumenrainstrasse 1", "Grenchen", 2540));
        demoSites.add(new Site("W06", "Storchengasse 9", "Grenchen", 2540));

        customers.add(new Enterprise("ETA SA Manufacture Horlogère Suisse", null,
                demoSites));
    }

    void createDemoSupplier() {
        List<Site> demoSites = new ArrayList<>();
        demoSites.add(new Site("Headquarters", "Solothurnstrasse", "Biel", 2504));
        suppliers.add(new Enterprise("AxNum GmbH", null, demoSites));
    }

    // In the future this controller can be used to load the configured enterprise
    // B2B version. Currently only one supplier and one enterprise are supportet but
    // with this architecture it can be easily adapted to support multiple
    // enterprises

    public Enterprise getCustomer() {
        return customers.get(0);
    }

    public Enterprise getSupplier() {
        return suppliers.get(0);
    }
}
