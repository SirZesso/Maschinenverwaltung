package controller;


import main.MainApp;
import model.Enterprise;
import model.machine.*;
import service.SerializationService;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;





public class SupplierController{

    private static ObservableList<ProcessCell> processCells;
    private MainApp mainApp;
    ProcessCell selectCell;
    
    //FXML Elements*************************************************
    @FXML private TableView<ProcessCell> tableProcessCells;
    @FXML private TableColumn<ProcessCell, Integer> columnSerialnumber;
    @FXML private TableColumn<ProcessCell, String> columnName;
    //@FXML private TableColumn<ProcessCell, String> processCellType;
    @FXML private ImageView imageProcessCell;
    @FXML private Button buttonChangeProcessCellView;
    @FXML private Button buttonNew;
    @FXML private Button buttonSave;
    @FXML private Button buttonDelete;
    @FXML private ChoiceBox<Enterprise> choiceboxManufacturer;
    @FXML private ChoiceBox<Enterprise> choiceboxCustomer;
    @FXML private Label labelSerialnumber;
    @FXML private Label labelName;
    @FXML private Label labelManufacturer;
    @FXML private Label labelCustomer;
    @FXML private Label labelType;
    @FXML private Label labelSpecial;
    @FXML private Label labelSpezialAbout;
    @FXML private TextField textfieldSerialnumber;
    @FXML private TextField textfieldName;
    @FXML private TextField textfieldType;
    @FXML private TextField textfieldSpecial;



    //Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
	private void initialize() {
        //processCells = createDemoProcessCells();
        processCells = SerializationService.deSerializePersonDatao();
        tableProcessCells.setItems(processCells);
        columnSerialnumber.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //processCellType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        showProcessCellInfo(null);
        processCellEditView(false);

        //Listeners

        tableProcessCells.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProcessCellInfo(newValue));



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

    @FXML
    void modifyProcessCell(ActionEvent event) {
        processCellEditView(true);

    }

    @FXML
    void clearProcessCellInformations(ActionEvent event) {

    }

    @FXML
    void deleteProcessCell(ActionEvent event) {

    }

    @FXML
    void saveNewProcessCell(ActionEvent event) {

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

    private void showProcessCellInfo(ProcessCell processCell){
        if (processCell != null){
            //Label
            labelSerialnumber.setText(""+processCell.getSerialnumber());
            labelName.setText(processCell.getName());
            labelManufacturer.setText(""+processCell.getManufacturer());
            labelCustomer.setText(""+processCell.getCustomer());
            labelType.setText(processCell.getType());

            //Textfield
            textfieldSerialnumber.setText(""+processCell.getSerialnumber());
            textfieldName.setText(processCell.getName());
            choiceboxManufacturer.setValue(processCell.getManufacturer());
            choiceboxCustomer.setValue(processCell.getCustomer());
            textfieldType.setText(processCell.getType());
            


        } else{

        }

    }


    @FXML
    void button3(ActionEvent event) {

    }
    private void processCellEditView(boolean status){
        //Textfelder
        textfieldSerialnumber.setVisible(status);
        textfieldName.setVisible(status);
        choiceboxCustomer.setVisible(status);
        choiceboxManufacturer.setVisible(status);
        textfieldType.setVisible(status);
        textfieldSpecial.setVisible(status);
        //Label
        labelSerialnumber.setVisible(!status);
        labelName.setVisible(!status);
        labelManufacturer.setVisible(!status);
        labelCustomer.setVisible(!status);
        labelType.setVisible(!status);
        labelSpecial.setVisible(!status);

        
    }
    

}
