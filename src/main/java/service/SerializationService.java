package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.machine.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Beispielhafte Serialisierung einer ObservableList
 */
public class SerializationService {

	private final static String FILE_PATH = "src/main/resources/processcells/processCells.ser";

	public static void serializeProcessCellData(ObservableList<ProcessCell> processCells) {
		try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(new ArrayList<ProcessCell>(processCells));
		} catch (IOException e) {
			System.out.println(
					"Aktuelle Daten konnten nicht furr naechste Verwendendung gespeichert werden: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ObservableList<ProcessCell> deSerializePersonDatao() {
		ArrayList<ProcessCell> processCells = new ArrayList<>();

		// Deserialize if File exists
		if (Files.exists(Paths.get(FILE_PATH))) {
			try (FileInputStream fileIn = new FileInputStream(FILE_PATH);
					ObjectInputStream in = new ObjectInputStream(fileIn)) {
				processCells = (ArrayList<ProcessCell>) in.readObject();
			} catch (Exception e) {
				System.out.println("Letzte Daten konnten nicht gelesen werden: " + e.getMessage());
			}
		}

		return FXCollections.observableArrayList(processCells);
	}

}
