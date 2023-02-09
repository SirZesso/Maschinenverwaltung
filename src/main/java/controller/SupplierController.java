package controller;

import main.MainApp;
import model.Enterprise;
import model.customer.Area;
import model.machine.*;
import service.SerializationService;
import javafx.fxml.FXML;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SupplierController {

    private static ObservableList<ProcessCell> processCells;
    private static ObservableList<Enterprise> customers;
    private static ObservableList<Enterprise> manufacturers;
    private MainApp mainApp;
    private String lasertype = "Wellenlänge";
    private String presstype = "Newton";
    ProcessCell selectCell;

    // FXML Elements*************************************************
    @FXML
    private TableView<ProcessCell> tableProcessCells;
    @FXML
    private TableColumn<ProcessCell, Integer> columnSerialnumber;
    @FXML
    private TableColumn<ProcessCell, String> columnName;
    // @FXML private TableColumn<ProcessCell, String> processCellType;
    @FXML
    private ImageView imageProcessCell;
    @FXML
    private Button buttonChangeProcessCellView;
    @FXML
    private Button buttonNew;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonChooseImage;
    @FXML
    private ChoiceBox<String> choiceboxManufacturer;
    @FXML
    private ChoiceBox<String> choiceboxCustomer;
    @FXML
    private ChoiceBox<MachineType> choiceboxType;
    @FXML
    private Label labelSpecification;
    @FXML
    private Label labelSerialnumber;
    @FXML
    private Label labelName;
    @FXML
    private Label labelManufacturer;
    @FXML
    private Label labelCustomer;
    @FXML
    private Label labelType;
    @FXML
    private Label labelSpecial;
    @FXML
    private Label labelSpecialAbout;
    @FXML
    private TextField textfieldSerialnumber;
    @FXML
    private TextField textfieldName;
    @FXML
    private TextField textfieldSpecial;


    // Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

        manufacturers = createDemoManufactors();
        customers = createDemoCustomers();
        processCells = createDemoProcessCells();
        // manufacturers = SerializationService.deSerializeEnterpriseDatao("manufactures.ser");
        // customers = SerializationService.deSerializeEnterpriseDatao("customers.ser");
        // processCells = SerializationService.deSerializeProcessCellDatao();

        tableProcessCells.setItems(processCells);
        columnSerialnumber.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asObject());
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showProcessCellInfo(null);
        processCellEditView(false);

        List<MachineType> types = Arrays.asList(MachineType.values());
        ObservableList<MachineType> typeList = FXCollections.observableList(types);
        choiceboxType.setItems(typeList);

        List<String> manufacturerNames = new ArrayList<>();
        for (Enterprise e : manufacturers) {
            manufacturerNames.add(e.getName());
        }
        ObservableList<String> manufacturerNameList = FXCollections.observableList(manufacturerNames);
        choiceboxManufacturer.setItems(manufacturerNameList);

        List<String> customerNames = new ArrayList<>();
        for (Enterprise e : customers) {
            customerNames.add(e.getName());
        }
        ObservableList<String> customerNamesList = FXCollections.observableList(customerNames);
        choiceboxCustomer.setItems(customerNamesList);

        // Listeners

        tableProcessCells.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showProcessCellInfo(newValue));

    }

    @FXML
    void btnClickExit(ActionEvent event) {
        System.out.println("Exit without safe");
        this.mainApp.showMainView();
    }

    @FXML
    void btnClickSaveExit(ActionEvent event) {
        SerializationService.serializeEnterpriseData(manufacturers, "manufactures.ser");
        SerializationService.serializeEnterpriseData(customers, "customers.ser");
        SerializationService.serializeProcessCellData(processCells);
        System.out.println("Exit");
        this.mainApp.showMainView();
    }

    @FXML
    void modifyProcessCell(ActionEvent event) {
        if (buttonChangeProcessCellView.getText().equals("Anpassen")) {
            processCellEditView(true);
        } else {
            processCellEditView(false);
        }
    }

    @FXML
    void newProcessCell(ActionEvent event) {
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setTitle("ProcessCell");
        confirmationAlert.setContentText("Typ der neuen ProcessCell anwählen");
        ButtonType buttonPress = new ButtonType("Press");
        ButtonType buttonLaser = new ButtonType("Laser");
        confirmationAlert.getButtonTypes().setAll(buttonPress, buttonLaser);
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        processCellEditView(true);
        clearProcessCellInformations();

        if (result.get() == buttonPress) {
            changeProcessCellInfo("Press");

        } else if (result.get() == buttonLaser) {
            changeProcessCellInfo("Laser");
        }

    }

    @FXML
    void deleteProcessCell(ActionEvent event) {
        int selectedIndex = tableProcessCells.getSelectionModel().getFocusedIndex();
        if (selectedIndex >= 0) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("ProcessCell");
            confirmationAlert.setContentText("Löschen?");
            ButtonType okButton = new ButtonType("JA");
            ButtonType noButton = new ButtonType("NEIN");
            confirmationAlert.getButtonTypes().setAll(okButton, noButton);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.get() == okButton) {
                System.out.println("Button YES"); // Konsolenausgabe
                System.out.println(selectedIndex); // Konsolenausgabe
                tableProcessCells.getItems().remove(selectedIndex);
                clearProcessCellInformations();
            } else if (result.get() == noButton) {
                System.out.println("Button NO"); // Konsolenausgabe
                confirmationAlert.close();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Person löschen");
            alert.setHeaderText("Keine Person ausgewählt");
            alert.setContentText("Bitte wählen Sie eine Person in der Liste aus.");
            alert.showAndWait();
        }
    }

    @FXML
    void saveNewProcessCell(ActionEvent event) throws Exception {
        if (isInputValid()) {
            ProcessCell selectedProcessCell = tableProcessCells.getSelectionModel().getSelectedItem();
            if (selectedProcessCell != null) {
                selectedProcessCell.setSerialnumber(Integer.parseInt(textfieldSerialnumber.getText()));
                selectedProcessCell.setName(textfieldName.getText());
                selectedProcessCell.setManufacturer(getEnterprisebyName(choiceboxManufacturer.getValue()));
                selectedProcessCell.setCustomer(getEnterprisebyName(choiceboxCustomer.getValue()));
                selectedProcessCell.setType(choiceboxType.getValue());
                if (selectedProcessCell instanceof Press) {
                    Press selectPress = new Press();
                    selectPress = (Press) selectedProcessCell;
                    selectPress.setNewton(Integer.parseInt(textfieldSpecial.getText()));
                }
                if (selectedProcessCell instanceof Laser) {
                    Laser selectLaser = new Laser();
                    selectLaser = (Laser) selectedProcessCell;
                    selectLaser.setWavelength(Integer.parseInt(textfieldSpecial.getText()));
                }
                showProcessCellInfo(selectedProcessCell);

            } else {
                if (labelSpecification.getText().equals("Press")) {
                    Press newPress = new Press();
                    newPress.setSerialnumber(Integer.parseInt(textfieldSerialnumber.getText()));
                    newPress.setName(textfieldName.getText());
                    newPress.setManufacturer(getEnterprisebyName(choiceboxManufacturer.getValue()));
                    newPress.setCustomer(getEnterprisebyName(choiceboxCustomer.getValue()));
                    newPress.setType(choiceboxType.getValue());
                    newPress.setNewton(Integer.parseInt(textfieldSpecial.getText()));
                    processCells.add(newPress);
                    showProcessCellInfo(newPress);
                } else if (labelSpecification.getText().equals("Laser")) {
                    Laser newLaser = new Laser();
                    newLaser.setSerialnumber(Integer.parseInt(textfieldSerialnumber.getText()));
                    newLaser.setName(textfieldName.getText());
                    newLaser.setManufacturer(getEnterprisebyName(choiceboxManufacturer.getValue()));
                    newLaser.setCustomer(getEnterprisebyName(choiceboxCustomer.getValue()));
                    newLaser.setType(choiceboxType.getValue());
                    newLaser.setWavelength(Integer.parseInt(textfieldSpecial.getText()));
                    processCells.add(newLaser);
                    showProcessCellInfo(newLaser);
                }

            }
            processCellEditView(false);
        }

    }

    @FXML
    void chooseImage(ActionEvent event) {
        ProcessCell selectedProcessCell = tableProcessCells.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Bild");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Bild-Dateien", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String newFilePath = "src/main/resources/images/" + selectedFile.getName();
                File newFile = new File(newFilePath);
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("images/"+ selectedFile.getName());
                selectedProcessCell.setImagePath("images/"+ selectedFile.getName());
                showProcessCellInfo(selectedProcessCell);
            } catch (Exception ex) {
                System.out.println("Fehler beim Kopieren des Bildes: " + ex.getMessage());
            }
        }
    }

    private void showProcessCellInfo(ProcessCell processCell) {
        if (processCell != null) {
            // Label
            labelSerialnumber.setText(String.valueOf(processCell.getSerialnumber()));
            labelName.setText(processCell.getName());
            labelManufacturer.setText((processCell.getManufacturer().getName()));
            labelCustomer.setText((processCell.getCustomer().getName()));
            labelType.setText(String.valueOf(processCell.getType()));

            // Textfield
            textfieldSerialnumber.setText(String.valueOf(processCell.getSerialnumber()));
            textfieldName.setText(processCell.getName());
            choiceboxManufacturer.setValue(processCell.getManufacturer().getName());
            choiceboxCustomer.setValue((processCell.getCustomer().getName()));
            choiceboxType.setValue(processCell.getType());

            // Image
            imageProcessCell.setImage(new Image(processCell.getImagePath()));

            if (processCell instanceof Press) {
                changeProcessCellInfo("Press");
                Press press = (Press) processCell;
                labelSpecial.setText(String.valueOf(press.getNewton()));
                textfieldSpecial.setText(String.valueOf(press.getNewton()));
            }
            if (processCell instanceof Laser) {
                changeProcessCellInfo("Laser");
                Laser laser = (Laser) processCell;
                labelSpecial.setText(String.valueOf(laser.getWavelength()));
                textfieldSpecial.setText(String.valueOf(laser.getWavelength()));
            }

        } else {

        }

    }

    private void changeProcessCellInfo(String processCellClass) {
        if (processCellClass.equals("Press")) {
            labelSpecialAbout.setText(presstype);
            labelSpecification.setText("Press");
        }
        if (processCellClass.equals("Laser")) {
            labelSpecialAbout.setText(lasertype);
            labelSpecification.setText("Laser");
        }
    }

    private void processCellEditView(boolean status) {
        // Button
        if (status) {
            buttonChangeProcessCellView.setText("Zurück");
        } else {
            buttonChangeProcessCellView.setText("Anpassen");
        }
        buttonSave.setVisible(status);
        buttonDelete.setVisible(status);
        buttonChooseImage.setVisible(status);

        // Textfelder
        textfieldSerialnumber.setVisible(status);
        textfieldName.setVisible(status);
        choiceboxCustomer.setVisible(status);
        choiceboxManufacturer.setVisible(status);
        choiceboxType.setVisible(status);
        textfieldSpecial.setVisible(status);
        // Label
        labelSerialnumber.setVisible(!status);
        labelName.setVisible(!status);
        labelManufacturer.setVisible(!status);
        labelCustomer.setVisible(!status);
        labelType.setVisible(!status);
        labelSpecial.setVisible(!status);

    }

    private void clearProcessCellInformations() {
        labelSerialnumber.setText("");
        labelName.setText("");
        labelManufacturer.setText("");
        labelCustomer.setText("");
        labelType.setText("");
        labelSpecial.setText("");
        labelSpecialAbout.setText("Zusatz Info");
        // Textfield
        textfieldSerialnumber.setText("");
        textfieldName.setText("");
        choiceboxManufacturer.setValue(null);
        choiceboxCustomer.setValue(null);
        choiceboxType.setValue(null);
        textfieldSpecial.setText("");

        tableProcessCells.getSelectionModel().clearSelection();

    }

    private Enterprise getEnterprisebyName(String enterpriseName) throws Exception {
        for (Enterprise e : manufacturers) {
            if (e.getName().equals(enterpriseName)) {
                return e;
            }
        }
        for (Enterprise e : customers) {
            if (e.getName().equals(enterpriseName)) {
                return e;
            }
        }
        throw new NoSuchElementException();

    }

    private boolean isInputValid() {
        String errorMessage = "";
        boolean valid = true;
        if (textfieldSerialnumber.getText().isEmpty()) {
            errorMessage += "Serienummer darf nicht leer sein!\n";
            textfieldSerialnumber.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        try {
            Integer.parseInt(textfieldSerialnumber.getText());

        } catch (NumberFormatException e) {
            errorMessage += "Serienummer darf nur Zahlen enthalten\n";
            textfieldSerialnumber.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (textfieldName.getText().isEmpty()) {
            errorMessage += "Name darf nicht leer sein!\n";
            textfieldName.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (choiceboxManufacturer.getValue() == null) {
            errorMessage += "Hersteller nicht zugewiesen.\n";
            choiceboxManufacturer.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (choiceboxCustomer.getValue() == null) {
            errorMessage += "Kunde nicht zugeweisen.\n";
            choiceboxCustomer.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (choiceboxType.getValue() == null) {
            errorMessage += "Kein Type zugeweisen.\n";
            choiceboxType.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        if (textfieldSpecial.getText().isEmpty()) {
            errorMessage += "Informationen Fehlen!\n";
            textfieldSpecial.setStyle("-fx-background-color: #ffad99");
            valid = false;
        }
        try {
            Integer.parseInt(textfieldSpecial.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Nicht zulässige Werte definiert!\n";
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
                        textfieldSerialnumber.setStyle("");
                        textfieldName.setStyle("");
                        choiceboxCustomer.setStyle("");
                        choiceboxManufacturer.setStyle("");
                        choiceboxType.setStyle("");
                        textfieldSpecial.setStyle("");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return valid;
    }
    // Create Demo
    // Values***********************************************************************

    private ObservableList<Enterprise> createDemoManufactors() {
        // Standart Enterprise
        List<Enterprise> manufactors = new ArrayList<>();

        manufactors.add(new Enterprise("Promess", "logo_path", null));
        manufactors.add(new Enterprise("ACI Laser GMBH", "logo_path", null));
        manufactors.add(new Enterprise("Gechter", "logo_path", null));
        manufactors.add(new Enterprise("SIC Marking", "logo_path", null));

        ObservableList<Enterprise> manufactorList = FXCollections.observableList(manufactors);
        return manufactorList;

    }

    private ObservableList<Enterprise> createDemoCustomers() {
        // Standart Enterprise
        List<Enterprise> customers = new ArrayList<>();

        customers.add(new Enterprise("ETA", "logo_path", null));
        customers.add(new Enterprise("Ypsomed", "logo_path", null));
        customers.add(new Enterprise("Asic", "logo_path", null));

        ObservableList<Enterprise> customerList = FXCollections.observableList(customers);
        return customerList;

    }

    private ObservableList<ProcessCell> createDemoProcessCells() {
        List<ProcessCell> processCells = new ArrayList<>();

        // Enterprise manufacturer = new Enterprise("Manufacturer A", "logo_path",
        // null);
        // Enterprise customer = new Enterprise("Customer B", "logo_path", null);

        processCells.add(
                new Press(1, "Compact_S", manufacturers.get(0), customers.get(0), MachineType.HANDARBEITSPLATZ,
                        "images/ProcessCell_default.png",new Area(), 1000));
        processCells.add(
                new Laser(2, "Laser 1", manufacturers.get(1), customers.get(0), MachineType.HANDARBEITSPLATZ,"images/ProcessCell_default.png",new Area(), 2000));
        processCells
                .add(new Press(3, "Press 2", manufacturers.get(2), customers.get(1), MachineType.INTEGRIERT,
                "images/ProcessCell_default.png",new Area(), 10000));
        processCells.add(new Laser(4, "Laser 2", manufacturers.get(3), customers.get(1), MachineType.INTEGRIERT,"images/ProcessCell_default.png", new Area(), 2500));
        processCells.add(
                new Press(23658, "UFM01", manufacturers.get(0), customers.get(2), MachineType.HANDARBEITSPLATZ,
                "images/ProcessCell_default.png",new Area(), 10000));

        ObservableList<ProcessCell> observableList = FXCollections.observableArrayList(processCells);
        return observableList;
    }

}
