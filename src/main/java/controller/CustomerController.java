package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.MainApp;
import model.machine.*;
import service.SerializationService;
import model.customer.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerController {

    private ObservableList<ProcessCell> processCells;
    private ObservableList<Area> areas = FXCollections.observableArrayList();
    private ObservableList<Site> sites;
    private MainApp mainApp;
    private Stage stage;

    private Area selectedArea;
    // FXML Elements*************************************************
    @FXML
    private Button buttonAddProcessCell;
    @FXML
    private Button buttonModifyArea;
    @FXML
    private Button buttonNew;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonDelete;
    @FXML
    private ChoiceBox<Floor> choiceboxFloor;
    @FXML
    private ChoiceBox<String> choiceboxSite;
    @FXML
    private TableColumn<Area, String> columnArea;
    @FXML
    private TableColumn<ProcessCell, String> columnName;
    @FXML
    private TableColumn<ProcessCell, Integer> columnSerialnumber;
    @FXML
    private TableColumn<ProcessCell, String> columnType;
    @FXML
    private Label labelAreaName;
    @FXML
    private Label labelSite;
    @FXML
    private Label labelFloor;
    @FXML
    private Label labelManager;
    @FXML
    private Label labelSurface;
    @FXML
    private TableView<Area> tableAreas;
    @FXML
    private TableView<ProcessCell> tableProcessCells;
    @FXML
    private TextArea textareaDescription;
    @FXML
    private TextField textfieldAreaName;
    @FXML
    private TextField textfieldManager;
    @FXML
    private TextField textfieldSurface;
    @FXML
    private Text textDescription;
    @FXML
    private Label labelProcessCellNr;
    @FXML
    private Label labelProcessCellName;
    @FXML
    private Label labelProcessCellType;
    @FXML
    private ImageView imageProcessCell;

    // Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

        sites = createSites();

        // sites = SerializationService.deSerializeSiteDatao();
        // areas = SerializationService.deSerializeAreaDatao();
        processCells = SerializationService.deSerializeProcessCellDatao();

        // areas = createDemoAreas();
        loadAreas();

        setAreaTabel(areas);

        showAreaInfo(null);
        areaEditView(false);
        setDetailTexts();

        List<Floor> floors = Arrays.asList(Floor.values());
        ObservableList<Floor> floorList = FXCollections.observableList(floors);
        choiceboxFloor.setItems(floorList);

        List<String> siteIds = new ArrayList<>();
        for (Site e : sites) {
            siteIds.add(e.getId());
        }
        ObservableList<String> siteIdList = FXCollections.observableList(siteIds);
        choiceboxSite.setItems(siteIdList);

        // Listeners

        tableAreas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showAreaInfo(newValue));
        tableAreas.getSelectionModel().selectFirst();

        tableProcessCells.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                imageProcessCell.setImage(new Image(newValue.getImagePath()));
                labelProcessCellName.setText(newValue.getName());
                labelProcessCellType.setText(String.valueOf(newValue.getType()));
                labelProcessCellNr.setText(String.valueOf(newValue.getSerialnumber()));
                System.out.println(newValue.getArea());
            } else {
                setDetailTexts();
            }
        });
        tableProcessCells.getSelectionModel().selectFirst();

    }

    private void setDetailTexts() {
        imageProcessCell.setImage(null);
        labelProcessCellName.setText("Keine Anlagen Details");
        labelProcessCellNr.setText("Es ist keine Anlage mit dem Area verknüpft");
        labelProcessCellType.setText("oder selektiert.");
    }

    private void setAreaTabel(ObservableList<Area> areas) {

        ObservableList<Area> filteredAreas = FXCollections.observableArrayList(areas.stream()
                .filter(area -> area.getName() != null && !area.getName().isEmpty())
                .collect(Collectors.toList()));

        tableAreas.setItems(filteredAreas);
        columnArea.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    }

    private ObservableList<Site> createSites() {

        List<Site> sites = new ArrayList<>();
        sites.add(new Site("W01", "Schild-Ruststrasse 16", "Grenchen", 2540));
        sites.add(new Site("W02", "Oelirein 3", "Grenchen", 2540));
        sites.add(new Site("W04", "Blumenrainstrasse 1", "Grenchen", 2540));
        sites.add(new Site("W06", "Storchengasse 9", "Grenchen", 2540));

        ObservableList<Site> siteList = FXCollections.observableList(sites);
        return siteList;

    }

    // TODO: Create more Demo areas
    private ObservableList<Area> createDemoAreas() {

        List<Area> areas = new ArrayList<>();
        areas.add(new Area("W01", "T0 Montagepark", "Automatisierte Montage von T0 Komponenten", Floor.EG, 643.00,
                "Sascha Seepferd"));
        areas.add(new Area("W01", "T1 Assembly", "Diverse Montage und Gravuren auf Ebauches", Floor.UG1, 200.00,
                "Nina Nielpferd"));
        areas.add(new Area("W02", "Glaspresse", "Einpressen von Lunetten und kleinen Schlafpillen", Floor.OG3, 250.00,
                "Leo Löwe"));
        areas.add(new Area("W06", "Optische Kontrolle", "Auschecken der gesamten Mouvements Monté", Floor.OG1, 5.00,
                "Emma Elefant"));
        areas.add(new Area("W02", "Top Gun Pressmasters",
                "Unterhalt von Stanzmaschinen in lufitger Höhe wie Cockpit Feeling", Floor.OG4, 10.00,
                "Tom Tiger"));
        areas.add(new Area("W01", "Wertstrom Kugellager",
                "Eindrehen bis es keinen Wert mehr hat und gepresst wird", Floor.EG, 200.00,
                "Peter Pfau"));
        areas.add(new Area("W02", "Laser-Team XYZ",
                "Aktronym Master of Desaster", Floor.UG1, 200.00,
                "Lars Lama"));

        ObservableList<Area> areaList = FXCollections.observableList(areas);
        return areaList;
    }

    @FXML
    void btnClickExit(ActionEvent event) {
        System.out.println("Exit without safe");
        this.mainApp.showMainView();
    }

    @FXML
    void btnClickSaveExit(ActionEvent event) {
        SerializationService.serializeAreaData(areas);
        SerializationService.serializeProcessCellData(processCells);
        System.out.println("Exit");
        this.mainApp.showMainView();
    }

    @FXML
    private void version() {

        // Show the error message.
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ProcessCell Observation Tool");
        alert.setHeaderText("Software Versions Informationen");
        alert.setContentText("Version: 1");
        alert.showAndWait();

    }

    @FXML
    private void authors() {

        // Show the error message.
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ProcessCell Observation Tool");
        alert.setHeaderText("Persönliche Informationen");
        alert.setContentText("Programmierer: \tPatrick Schreyer und Yannic Ziegler \nDatum: \t\t\t20.02.2023");
        alert.showAndWait();

    }

    @FXML
    void modifyArea(ActionEvent event) {
        if (buttonModifyArea.getText().equals("Anpassen")) {
            areaEditView(true);
        } else {
            areaEditView(false);
        }
    }

    @FXML
    void modifyProcessCell(ActionEvent event) {
        selectedArea = tableAreas.getSelectionModel().getSelectedItem();
        if (selectedArea != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/view/customer/assigneProcessCelltoArea.fxml"));
                Scene scene = new Scene(loader.load());

                ProcessCellController controller = loader.getController();
                controller.setProcessCellsAndSelectedArea(processCells, selectedArea);

                Stage stage = new Stage();
                stage.setTitle("Process Cell");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(this.stage);
                stage.setScene(scene);
                stage.showAndWait();
                showAreaInfo(selectedArea);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void deleteArea(ActionEvent event) {
        int selectedIndex = tableAreas.getSelectionModel().getFocusedIndex();
        if (selectedIndex >= 0) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Area");
            confirmationAlert.setContentText("Löschen?");
            ButtonType okButton = new ButtonType("JA");
            ButtonType noButton = new ButtonType("NEIN");
            confirmationAlert.getButtonTypes().setAll(okButton, noButton);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.get() == okButton) {
                System.out.println("Button YES"); // Konsolenausgabe
                System.out.println(selectedIndex); // Konsolenausgabe
                Area area = tableAreas.getItems().get(selectedIndex);
                for (ProcessCell processCell : processCells) {
                    if (processCell.getArea() != null && processCell.getArea().equals(area)) {
                        processCell.setArea(new Area());
                    }
                }
                areas.remove(area);
                tableAreas.getItems().remove(selectedIndex);
                clearAreaInformations();
            } else if (result.get() == noButton) {
                System.out.println("Button NO"); // Konsolenausgabe
                confirmationAlert.close();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Area löschen");
            alert.setHeaderText("Keine Area ausgewählt");
            alert.setContentText("Bitte wählen Sie eine Area in der Liste aus.");
            alert.showAndWait();
        }
    }

    private void clearAreaInformations() {
        labelAreaName.setText("");
        labelFloor.setText("");
        labelSite.setText("");
        labelManager.setText("");
        labelSurface.setText("");
        textDescription.setText("");
        textfieldAreaName.setText("");
        textfieldManager.setText("");
        textfieldSurface.setText("");
        textareaDescription.setText("");

        choiceboxFloor.setValue(null);
        choiceboxSite.setValue(null);

        tableAreas.getSelectionModel().clearSelection();
    }

    @FXML
    void newArea(ActionEvent event) {
        clearAreaInformations();
        areaEditView(true);
    }

    @FXML
    void saveArea(ActionEvent event) {
        if (isInputValid()) {
            Area selectedArea = tableAreas.getSelectionModel().getSelectedItem();
            if (selectedArea != null) {
                selectedArea.setName(textfieldAreaName.getText());
                selectedArea.setDescription(textareaDescription.getText());
                selectedArea.setSiteId(choiceboxSite.getValue());
                selectedArea.setFloor(choiceboxFloor.getValue());
                selectedArea.setManager(textfieldManager.getText());
                selectedArea.setSurface(Double.parseDouble(textfieldSurface.getText()));
                showAreaInfo(selectedArea);
            } else {
                Area newArea = new Area();
                newArea.setName(textfieldAreaName.getText());
                newArea.setDescription(textareaDescription.getText());
                newArea.setSiteId(choiceboxSite.getValue());
                newArea.setFloor(choiceboxFloor.getValue());
                newArea.setManager(textfieldManager.getText());
                newArea.setSurface(Double.parseDouble(textfieldSurface.getText()));
                areas.add(newArea);
                setAreaTabel(areas);
                showAreaInfo(newArea);

            }
            areaEditView(false);
        }

    }

    private void showAreaInfo(Area area) {
        if (area != null) {

            ObservableList<ProcessCell> areaProcessCells = FXCollections.observableList(processCells.stream()
                    .filter(processCell -> processCell.getArea().equals(area))
                    .collect(Collectors.toList()));
            tableProcessCells.setItems(areaProcessCells);
            tableProcessCells.getSelectionModel().selectFirst();

            columnSerialnumber.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asObject());
            columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            columnType.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asString());

            // Label
            labelAreaName.setText(area.getName());
            labelSite.setText(area.getSiteId());
            labelFloor.setText(String.valueOf(area.getFloor()));
            labelManager.setText(area.getManager());
            labelSurface.setText(String.valueOf(area.getSurface()));
            // Textfield
            textareaDescription.setText(area.getDescription());
            textDescription.setText(area.getDescription());
            textfieldAreaName.setText(area.getName());
            textfieldManager.setText(area.getManager());
            textfieldSurface.setText(String.valueOf(area.getSurface()));
            choiceboxSite.setValue(area.getSiteId());
            choiceboxFloor.setValue(area.getFloor());

        } else {

        }

    }

    private void loadAreas() {
        areas.clear();
        // Add Areas from ProcessCells
        for (ProcessCell processCell : processCells) {
            if (!areas.contains(processCell.getArea())) {
                areas.add(processCell.getArea());
            }
        }
        // Add Areas from serialization
        List<Area> savedAreas = SerializationService.deSerializeAreaDatao();
        areas.addAll(savedAreas
                .stream().filter(
                        area -> area.getName() != null && !areas.stream()
                                .anyMatch(existingArea -> existingArea.getName() != null
                                        && existingArea.getName().equals(area.getName())))
                .collect(Collectors.toList()));
    }

    private void areaEditView(boolean status) {
        if (status) {
            buttonModifyArea.setText("Zurück");
        } else {
            buttonModifyArea.setText("Anpassen");
        }
        textfieldAreaName.setVisible(status);
        textfieldManager.setVisible(status);
        textfieldSurface.setVisible(status);
        textareaDescription.setVisible(status);
        buttonSave.setVisible(status);
        buttonDelete.setVisible(status);
        choiceboxFloor.setVisible(status);
        choiceboxSite.setVisible(status);
        // Unvisible
        labelAreaName.setVisible(!status);
        labelFloor.setVisible(!status);
        labelSite.setVisible(!status);
        labelManager.setVisible(!status);
        labelSurface.setVisible(!status);
        textDescription.setVisible(!status);
    }

    private boolean isInputValid() {
        String errorMessage = "";
        boolean valid = true;
        if (textfieldAreaName.getText().isEmpty()) {
            errorMessage += "Name darf nicht leer sein!\n";
            textfieldAreaName.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (textfieldManager.getText().isEmpty()) {
            errorMessage += "Es muss jemand zuständig sein.\n";
            textfieldManager.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (textareaDescription.getText().isEmpty()) {
            errorMessage += "Beschreibung darf nicht leer sein!\n";
            textareaDescription.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (choiceboxSite.getValue() == null) {
            errorMessage += "Werk nicht zugewiesen.\n";
            choiceboxSite.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (choiceboxFloor.getValue() == null) {
            errorMessage += "Etage nicht zugeweisen.\n";
            choiceboxFloor.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (textfieldSurface.getText().isEmpty()) {
            errorMessage += "Die Fläche wurde nicht angegeben\n";
            textfieldSurface.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        try {
            Double.parseDouble(textfieldSurface.getText());

        } catch (NumberFormatException e) {
            errorMessage += "Fläche darf nur Zahlen enthalten\n";
            textfieldSurface.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }

        if (!valid) {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Felder Valdierung");
            alert.setHeaderText("Bitte korrigieren Sie die ungültigen Felder.");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        textfieldAreaName.setStyle("");
                        textareaDescription.setStyle("");
                        choiceboxSite.setStyle("");
                        choiceboxFloor.setStyle("");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return valid;
    }

}
