package controller;

import main.MainApp;
import model.Enterprise;
import model.machine.*;
import service.SerializationService;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class SupplierController {

    private static ObservableList<ProcessCell> processCells;
    private static ObservableList<Enterprise> enterprises;
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
    private ChoiceBox<Enterprise> choiceboxManufacturer;
    @FXML
    private ChoiceBox<Enterprise> choiceboxCustomer;
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

        // enterprises = createDemoEnterprises();
        // processCells = createDemoProcessCells();
        enterprises = SerializationService.deSerializeEnterpriseDatao();
        processCells = SerializationService.deSerializeProcessCellDatao();

        tableProcessCells.setItems(processCells);
        columnSerialnumber.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asObject());
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showProcessCellInfo(null);
        processCellEditView(false);

        List<MachineType> types = Arrays.asList(MachineType.values());
        ObservableList<MachineType> typeList = FXCollections.observableList(types);
        choiceboxType.setItems(typeList);
        choiceboxManufacturer.setItems(enterprises);
        choiceboxCustomer.setItems(enterprises);

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
        SerializationService.serializeEnterpriseData(enterprises);
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
    void clearProcessCellInformations() {
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setTitle("ProcessCell");
        confirmationAlert.setContentText("Typ der neuen ProcessCell anwählen");
        ButtonType buttonPress = new ButtonType("Press");
        ButtonType buttonLaser = new ButtonType("Laser");
        confirmationAlert.getButtonTypes().setAll(buttonPress, buttonLaser);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        processCellEditView(true);
        tableProcessCells.getSelectionModel().clearSelection();

        labelSerialnumber.setText("");
        labelName.setText("");
        labelManufacturer.setText("");
        labelCustomer.setText("");
        labelType.setText("");
        labelSpecial.setText("");
        if (result.get() == buttonPress) {
            labelSpecialAbout.setText(presstype); 
            labelSpecification.setText("Presse");
        }
        else{
            labelSpecialAbout.setText(lasertype);
            labelSpecification.setText("Laser");
        }

        // Textfield
        textfieldSerialnumber.setText("");
        textfieldName.setText("");
        choiceboxManufacturer.setValue(null);
        choiceboxCustomer.setValue(null);
        choiceboxType.setValue(null);
        textfieldSpecial.setText("");
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
    void saveNewProcessCell(ActionEvent event) {
        if (isInputValid()) {
            ProcessCell selectProcessCell = tableProcessCells.getSelectionModel().getSelectedItem();
            if (selectProcessCell != null) {
                selectProcessCell.setSerialnumber(Integer.parseInt(textfieldSerialnumber.getText()));
                selectProcessCell.setName(textfieldName.getText());
                selectProcessCell.setManufacturer(choiceboxManufacturer.getValue());
                selectProcessCell.setCustomer(choiceboxCustomer.getValue());
                selectProcessCell.setType(choiceboxType.getValue());
                
            } else {
                ProcessCell newProcessCell = new ProcessCell();
                newProcessCell.setSerialnumber(Integer.parseInt(textfieldSerialnumber.getText()));
                newProcessCell.setName(textfieldName.getText());
                newProcessCell.setManufacturer(choiceboxManufacturer.getValue());
                newProcessCell.setCustomer(choiceboxCustomer.getValue());
                newProcessCell.setType(choiceboxType.getValue());

                processCells.add(newProcessCell);
                showProcessCellInfo(newProcessCell);
            }
            processCellEditView(false);
        }

    }

    private ObservableList<Enterprise> createDemoEnterprises() {
        // Standart Enterprise
        List<Enterprise> enterprises = new ArrayList<>();

        enterprises.add(new Enterprise("Manufacturer A", "logo_path", null));
        enterprises.add(new Enterprise("Customer B", "logo_path", null));
        ObservableList<Enterprise> enterpriseList = FXCollections.observableList(enterprises);
        return enterpriseList;

    }

    private ObservableList<ProcessCell> createDemoProcessCells() {
        List<ProcessCell> processCells = new ArrayList<>();

        // Enterprise manufacturer = new Enterprise("Manufacturer A", "logo_path",
        // null);
        // Enterprise customer = new Enterprise("Customer B", "logo_path", null);

        processCells.add(
                new Press(1, "Press 1", enterprises.get(0), enterprises.get(1), MachineType.HANDARBEITSPLATZ, 1000));
        processCells.add(
                new Laser(2, "Laser 1", enterprises.get(0), enterprises.get(1), MachineType.HANDARBEITSPLATZ, 2000));
        processCells
                .add(new Press(3, "Press 2", enterprises.get(0), enterprises.get(1), MachineType.INTEGRIERT, 10000));
        processCells.add(new Laser(4, "Laser 2", enterprises.get(0), enterprises.get(1), MachineType.INTEGRIERT, 2500));
        processCells.add(
                new Press(23658, "UFM01", enterprises.get(1), enterprises.get(1), MachineType.HANDARBEITSPLATZ, 10000));

        ObservableList<ProcessCell> observableList = FXCollections.observableArrayList(processCells);
        return observableList;
    }

    private void showProcessCellInfo(ProcessCell processCell) {
        if (processCell != null) {
            // Label
            labelSerialnumber.setText(String.valueOf(processCell.getSerialnumber()));
            labelName.setText(processCell.getName());
            labelManufacturer.setText(String.valueOf(processCell.getManufacturer()));
            labelCustomer.setText(String.valueOf(processCell.getCustomer()));
            labelType.setText(String.valueOf(processCell.getType()));

            // Textfield
            textfieldSerialnumber.setText(String.valueOf(processCell.getSerialnumber()));
            textfieldName.setText(processCell.getName());
            choiceboxManufacturer.setValue(processCell.getManufacturer());
            choiceboxCustomer.setValue(processCell.getCustomer());
            choiceboxType.setValue(processCell.getType());

            if (processCell instanceof Press) {
                Press press = (Press) processCell;
                labelSpecialAbout.setText(presstype);
                labelSpecial.setText(String.valueOf(press.getNewton()));
                textfieldSpecial.setText(String.valueOf(press.getNewton()));
                labelSpecification.setText("Presse");
            }
            if (processCell instanceof Laser) {
                Laser laser = (Laser) processCell;
                labelSpecialAbout.setText(lasertype);
                labelSpecial.setText(String.valueOf(laser.getWavelength()));
                textfieldSpecial.setText(String.valueOf(laser.getWavelength()));
                labelSpecification.setText("Laser");
            }

        } else {

        }

    }

    @FXML
    void button3(ActionEvent event) {

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

    private boolean isInputValid() {
        String errorMessage = "";
        if (textfieldSerialnumber.getText().isEmpty()) {
            errorMessage += "Serienummer darf nicht leer sein!\n";
        }
        if (textfieldName.getText().isEmpty()) {
            errorMessage += "Name darf nicht leer sein!\n";
        }
        if (choiceboxManufacturer.getValue() == null) {
            errorMessage += "Hersteller nicht zugewiesen.\n";
        }
        if (choiceboxCustomer.getValue() == null) {
            errorMessage += "Kunde nicht zugeweisen.\n";
        }
        if (choiceboxType.getValue() == null) {
            errorMessage += "Kein Type zugeweisen.\n";
        }
        // if (textfieldSpecial.getText().isEmpty()) {
        // errorMessage += "Informationen Fehlen!\n";
        // }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Felder Valdierung");
            alert.setHeaderText("Bitte korrigieren Sie die ungültigen Felder.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        return false;
    }

}
