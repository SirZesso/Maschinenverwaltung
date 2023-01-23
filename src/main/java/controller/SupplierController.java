package controller;


import main.MainApp;
import model.Enterprise;
import model.machine.Laser;
import model.machine.Press;
import model.machine.ProcessCell;
import service.SerializationService;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;




public class SupplierController{

    private static ObservableList<ProcessCell> processCells;
    private MainApp mainApp;
    
    //FXML Elements*************************************************
    @FXML
    private TableView<ProcessCell> processCellTable;
    @FXML
    private TableColumn<ProcessCell, Integer> processCellId;
    @FXML
    private TableColumn<ProcessCell, String> processCellName;
    @FXML
    private TableColumn<ProcessCell, String> processCellType;

    //Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
	private void initialize() {
        //processCells = createDemoProcessCells();
        processCells = SerializationService.deSerializePersonDatao();
        processCellTable.setItems(processCells);
        processCellId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        processCellName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        processCellType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    }
    @FXML
    void btnClickExit(ActionEvent event) {
        System.out.println("Exit without safe");
        this.mainApp.showMainView();
    }

    @FXML
    void btnClickSaveExit(ActionEvent event) {
        SerializationService.serializeProcessCellData(processCells);
        System.out.println("Exit");
        this.mainApp.showMainView();
    }

    private ObservableList<ProcessCell> createDemoProcessCells(){
        List<ProcessCell> processCells = new ArrayList<>();
    
        Enterprise manufacturer = new Enterprise("Manufacturer A", "logo_path", null);
        Enterprise customer = new Enterprise("Customer B", "logo_path", null);
    
        processCells.add(new Press(1, "Press 1", manufacturer, customer, "UFM 01", 1000));
        processCells.add(new Laser(2, "Laser 1", manufacturer, customer, "Faser", 2000));
        processCells.add(new Press(3, "Press 2", manufacturer, customer, "UFM 10", 10000));
        processCells.add(new Laser(4, "Laser 2", manufacturer, customer, "Diode", 2500));
    
        ObservableList<ProcessCell> observableList = FXCollections.observableArrayList(processCells);
        return observableList;
    }
    

}
