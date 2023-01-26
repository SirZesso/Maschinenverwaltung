package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Enterprise;
import model.customer.Site;

public class XmlService {

    public static void main(String[] args) throws JAXBException, IOException {

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

        // JAXB-Kontext erzeugen
        JAXBContext context = JAXBContext.newInstance(Enterprise.class);

        // XML-Datei aus Objekt
        try (
                FileWriter fileWriter = new FileWriter("customer.xml")) {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(customer, fileWriter);
        }

        // Objekt aus XML-Datei
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Enterprise ent = (Enterprise) unmarshaller
                .unmarshal(new File("customer.xml"));
        System.out.println("Name aus Datei: " + ent.getName());
    }

}
