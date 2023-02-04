package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.Enterprise;
import model.customer.Area;
import model.machine.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Beispielhafte Serialisierung einer ObservableList
 */
public class SerializationService {

	// private final static String FILE_PATH = "src/main/resources/serialisation/";
	private final static String PROCESSCELL_PATH = "src/main/resources/serialisation/processCells.ser";
	private final static String AREA_PATH = "src/main/resources/serialisation/areas.ser";

	private final static String ENTERPRISE_PATH = "src/main/resources/serialisation/";

	// ProcessCell************************************************************************************************************
	public static void serializeProcessCellData(ObservableList<ProcessCell> processCells) {
		try (FileOutputStream fileOut = new FileOutputStream(PROCESSCELL_PATH);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(new ArrayList<ProcessCell>(processCells));
		} catch (IOException e) {
			System.out.println(
					"ProcessCells: Aktuelle Daten konnten nicht furr naechste Verwendendung gespeichert werden: "
							+ e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<ProcessCell> deSerializeProcessCellDatao() {
		ArrayList<ProcessCell> processCells = new ArrayList<>();

		// Deserialize if File exists
		if (Files.exists(Paths.get(PROCESSCELL_PATH))) {
			try (FileInputStream fileIn = new FileInputStream(PROCESSCELL_PATH);
					ObjectInputStream in = new ObjectInputStream(fileIn)) {
				processCells = (ArrayList<ProcessCell>) in.readObject();
			} catch (Exception e) {
				System.out.println("Letzte Daten konnten nicht gelesen werden: " + e.getMessage());
			}
		}

		return FXCollections.observableArrayList(processCells);
	}

	// Area
	// ************************************************************************************************************
	public static void serializeAreaData(ObservableList<Area> areas) {
		try (FileOutputStream fileOut = new FileOutputStream(AREA_PATH);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(new ArrayList<Area>(areas));
		} catch (IOException e) {
			System.out.println(
					"Areas: Aktuelle Daten konnten nicht furr naechste Verwendendung gespeichert werden: "
							+ e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<Area> deSerializeAreaData() {
		ArrayList<Area> areas = new ArrayList<>();

		// Deserialize if File exists
		if (Files.exists(Paths.get(AREA_PATH))) {
			try (FileInputStream fileIn = new FileInputStream(AREA_PATH);
					ObjectInputStream in = new ObjectInputStream(fileIn)) {
				areas = (ArrayList<Area>) in.readObject();
			} catch (Exception e) {
				System.out.println("Letzte Daten konnten nicht gelesen werden: " + e.getMessage());
			}
		}

		return FXCollections.observableArrayList(areas);
	}

	// Enterprise************************************************************************************************************
	public static void serializeEnterpriseData(ObservableList<Enterprise> enterprises, String file) {
		try (FileOutputStream fileOut = new FileOutputStream(ENTERPRISE_PATH + file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(new ArrayList<Enterprise>(enterprises));
		} catch (IOException e) {
			System.out.println(
					"Enterprises: Aktuelle Daten konnten nicht furr naechste Verwendendung gespeichert werden: "
							+ e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<Enterprise> deSerializeEnterpriseDatao(String file) {
		ArrayList<Enterprise> enterprises = new ArrayList<>();

		// Deserialize if File exists
		if (Files.exists(Paths.get(ENTERPRISE_PATH + file))) {
			try (FileInputStream fileIn = new FileInputStream(ENTERPRISE_PATH + file);
					ObjectInputStream in = new ObjectInputStream(fileIn)) {
				enterprises = (ArrayList<Enterprise>) in.readObject();
			} catch (Exception e) {
				System.out.println("Letzte Daten konnten nicht gelesen werden: " + e.getMessage());
			}
		}

		return FXCollections.observableArrayList(enterprises);
	}

}
