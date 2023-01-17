package controller;

import java.util.List;

import model.Enterprise;
import model.customer.Site;

public class EnterpriseController {
    public List<Enterprise> customers;
    public List<Enterprise> suppliers;

    void createDemoCustomer() {
        customers.add(new Enterprise("ETA SA Manufacture Horlogère Suisse", null,
                new Site("W01", "Schild-Ruststrasse 16", "Grenchen", 2540)));
    }

    void createDemoSupplier() {
        suppliers.add(new Enterprise("AxNum GmbH", null, new Site("Headquarters", "Blumenweg 123", "Biel", 4444)));
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
