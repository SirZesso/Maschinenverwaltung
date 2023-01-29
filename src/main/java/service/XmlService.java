package service;

import java.util.ArrayList;
import java.util.List;

import model.Enterprise;
import model.customer.Site;

public class XmlService {

    public static void main(String[] args) {

        List<Site> demoSites = new ArrayList<>();
        demoSites.add(new Site("W01", "Schild-Ruststrasse 16", "Grenchen", 2540));
        demoSites.add(new Site("W02", "Oelirein 3", "Grenchen", 2540));
        demoSites.add(new Site("W04", "Blumenrainstrasse 1", "Grenchen", 2540));
        demoSites.add(new Site("W06", "Storchengasse 9", "Grenchen", 2540));

        Enterprise customer = new Enterprise("ETA SA Manufacture Horlogère Suisse", "",
                demoSites);

        // List<Site> demoSites = new ArrayList<>();
        // demoSites.add(new Site("Headquarters", "Solothurnstrasse", "Biel", 2504));
        // supplier = new Enterprise("AxNum GmbH", null, demoSites);

    }

}
