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
import model.machine.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Beispielhafte Serialisierung einer ObservableList
 */
public class SerializationService {

	//private final static String FILE_PATH = "src/main/resources/serialisation/";
	private final static String PROCESSCELL_PATH = "src/main/resources/serialisation/processCells.ser";
	private final static String ENTERPRISE_PATH = "src/main/resources/serialisation/enterprises.ser";

	//ProcessCell************************************************************************************************************
	public static void serializeProcessCellData(ObservableList<ProcessCell> processCells) {
		try (FileOutputStream fileOut = new FileOutputStream(PROCESSCELL_PATH);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(new ArrayList<ProcessCell>(processCells));
		} catch (IOException e) {
			System.out.println(
					"ProcessCells: Aktuelle Daten konnten nicht furr naechste Verwendendung gespeichert werden: " + e.getMessage());
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
	//Enterprise************************************************************************************************************
	public static void serializeEnterpriseData(ObservableList<Enterprise> enterprises) {
		try (FileOutputStream fileOut = new FileOutputStream(ENTERPRISE_PATH);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(new ArrayList<Enterprise>(enterprises));
		} catch (IOException e) {
			System.out.println(
					"Enterprises: Aktuelle Daten konnten nicht furr naechste Verwendendung gespeichert werden: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<Enterprise> deSerializeEnterpriseDatao() {
		ArrayList<Enterprise> enterprises = new ArrayList<>();

		// Deserialize if File exists
		if (Files.exists(Paths.get(ENTERPRISE_PATH))) {
			try (FileInputStream fileIn = new FileInputStream(ENTERPRISE_PATH);
					ObjectInputStream in = new ObjectInputStream(fileIn)) {
					enterprises = (ArrayList<Enterprise>) in.readObject();
			} catch (Exception e) {
				System.out.println("Letzte Daten konnten nicht gelesen werden: " + e.getMessage());
			}
		}

		return FXCollections.observableArrayList(enterprises);
	}

}
