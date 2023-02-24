package service;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Enterprise;
import model.machine.*;
import model.customer.*;

public class CreateDummyData {
        public static void create() {

                // Create Dummy Areas*******************************************************

                List<Area> areaList = new ArrayList<>();
                areaList.add(new Area("W01", "T0 Montagepark", "Automatisierte Montage von T0 Komponenten", Floor.EG,
                                643.00,
                                "Sascha Seepferd"));
                areaList.add(new Area("W01", "T1 Assembly", "Diverse Montage und Gravuren auf Ebauches", Floor.UG1,
                                200.00,
                                "Nina Nielpferd"));
                areaList.add(
                                new Area("W02", "Glaspresse", "Einpressen von Lunetten und kleinen Schlafpillen",
                                                Floor.OG3, 250.00,
                                                "Leo Löwe"));
                areaList.add(new Area("W06", "Optische Kontrolle", "Auschecken der gesamten Mouvements Monté",
                                Floor.OG1, 5.00,
                                "Emma Elefant"));
                areaList.add(new Area("W02", "Top Gun Pressmasters",
                                "Unterhalt von Stanzmaschinen in lufitger Höhe wie Cockpit Feeling", Floor.OG4, 10.00,
                                "Tom Tiger"));
                areaList.add(new Area("W01", "Wertstrom Kugellager",
                                "Eindrehen bis es keinen Wert mehr hat und gepresst wird", Floor.EG, 200.00,
                                "Peter Pfau"));
                areaList.add(new Area("W02", "Laser-Team XYZ",
                                "Aktronym Master of Desaster", Floor.UG1, 200.00,
                                "Lars Lama"));

                ObservableList<Area> areas = FXCollections.observableList(areaList);
                SerializationService.serializeAreaData(areas);

                // Create Dummy
                // Enterprises**********************************************************

                // Customers
                List<Enterprise> customerList = new ArrayList<>();

                customerList.add(new Enterprise("ETA", "logo_path", null));
                customerList.add(new Enterprise("Ypsomed", "logo_path", null));
                customerList.add(new Enterprise("Asic", "logo_path", null));
                customerList.add(new Enterprise("Disney", "logo_path", null));

                ObservableList<Enterprise> customers = FXCollections.observableList(customerList);
                SerializationService.serializeEnterpriseData(customers, "customers.ser");

                // Manufactors
                List<Enterprise> manufactorList = new ArrayList<>();

                manufactorList.add(new Enterprise("Promess", "logo_path", null));
                manufactorList.add(new Enterprise("ACI Laser GMBH", "logo_path", null));
                manufactorList.add(new Enterprise("Gechter", "logo_path", null));
                manufactorList.add(new Enterprise("SIC Marking", "logo_path", null));
                manufactorList.add(new Enterprise("Lucasfilm", "logo_path", null));

                ObservableList<Enterprise> manufacturers = FXCollections.observableList(manufactorList);
                SerializationService.serializeEnterpriseData(manufacturers, "manufactures.ser");

                // Create Dummy
                // ProcessCells********************************************************

                List<ProcessCell> processCellList = new ArrayList<>();

                // Real data
                processCellList.add(
                                new Press(124335, "Compact 500", manufacturers.get(0), customers.get(0),
                                                MachineType.INTEGRIERT,
                                                "images/Compact500.png", areas.get(0), 1000));
                processCellList.add(
                                new Press(456433, "Compact L", manufacturers.get(1), customers.get(0),
                                                MachineType.INTEGRIERT,
                                                "images/CompactL.png", areas.get(0), 2000));
                processCellList
                                .add(new Press(674341, "Compact M", manufacturers.get(2), customers.get(1),
                                                MachineType.INTEGRIERT,
                                                "images/CompactM.png", areas.get(1), 10000));
                processCellList.add(
                                new Press(236358, "Compact S", manufacturers.get(0), customers.get(2),
                                                MachineType.INTEGRIERT,
                                                "images/CompactS.png", areas.get(2), 150));
                processCellList.add(
                                new Press(256471, "Compact XL", manufacturers.get(0), customers.get(2),
                                                MachineType.INTEGRIERT,
                                                "images/CompactXL.png", areas.get(2), 150));
                processCellList.add(
                                new Press(256471, "Compact XL", manufacturers.get(1), customers.get(1),
                                                MachineType.INTEGRIERT,
                                                "images/CompactXL.png", areas.get(3), 150));

                // Lightsaber jokes
                processCellList.add(
                                new Laser(124782, "Laser Y", manufacturers.get(4), customers.get(3),
                                                MachineType.HANDARBEITSPLATZ,
                                                "images/LightsaberYoda.jpg", areas.get(4), 5400));
                processCellList.add(
                                new Laser(100066, "Laser A", manufacturers.get(4), customers.get(3),
                                                MachineType.HANDARBEITSPLATZ,
                                                "images/LightsaberAnakin.png", areas.get(5), 5600));
                processCellList.add(
                                new Laser(124782, "Laser O", manufacturers.get(4), customers.get(3),
                                                MachineType.HANDARBEITSPLATZ,
                                                "images/LightsaberObiWan.png", new Area(), 5400));
                processCellList.add(
                                new Laser(124782, "Laser L", manufacturers.get(4), customers.get(3),
                                                MachineType.HANDARBEITSPLATZ,
                                                "images/LightsaberLuke.jpg", new Area(), 5400));

                // more jokes
                processCellList.add(
                                new Press(124782, "Gutenberg Presse", manufacturers.get(0), customers.get(3),
                                                MachineType.INTEGRIERT,
                                                "images/Gutenberg.jpg", new Area(), 1));
                processCellList.add(
                                new Press(444256, "Super Hydraulic Press", manufacturers.get(3), customers.get(1),
                                                MachineType.INTEGRIERT,
                                                "images/hydraulic_press.jpg", new Area(), 15000));

                ObservableList<ProcessCell> processCells = FXCollections.observableArrayList(processCellList);
                SerializationService.serializeProcessCellData(processCells);

        }

}
