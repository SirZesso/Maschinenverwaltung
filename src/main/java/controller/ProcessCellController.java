package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.customer.Area;
import model.machine.ProcessCell;


public class ProcessCellController {
    private ObservableList<ProcessCell> processCells;
    private ObservableList<ProcessCell> aviableProcessCells;
    private ObservableList<ProcessCell> assignedProcessCells;
    private Area selectedArea;

    @FXML
    private TableColumn<ProcessCell, String> columnAssignedName;

    @FXML
    private TableColumn<ProcessCell, String> columnAssignedNr;

    @FXML
    private TableColumn<ProcessCell, String> columnAssignedType;

    @FXML
    private TableColumn<ProcessCell, String> columnAviableName;

    @FXML
    private TableColumn<ProcessCell, String> columnAviableNr;

    @FXML
    private TableColumn<ProcessCell, String> columnAviableType;

    @FXML
    private TableView<ProcessCell> tabelAssignedProcessCells;

    @FXML
    private TableView<ProcessCell> tabelAviableProcessCells;



    @FXML
    private void initialize() {

    }
    @FXML
    void addProcessCell(ActionEvent event) {

    }

    @FXML
    void exitModification(ActionEvent event) {
        
    }

    @FXML
    void removeProcessCell(ActionEvent event) {

    }

    @FXML
    void saveModification(ActionEvent event) {

    }
    public void setProcessCellsAndSelectedArea(ObservableList<ProcessCell> processCells, Area selectedArea) {
        this.processCells = processCells;
        this.selectedArea = selectedArea;
        System.out.println(this.processCells);
        System.out.println(this.selectedArea);
        setTables();
    }
    
    public void setTables() {
        assignedProcessCells = processCells.filtered(processCell -> processCell.getArea() == selectedArea);
        tabelAssignedProcessCells.setItems(assignedProcessCells);
        columnAssignedNr.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asString());
        columnAssignedName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnAssignedType.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asString());


        aviableProcessCells = processCells.filtered(processCells -> processCells.getArea().getName() == null);
        tabelAviableProcessCells.setItems(aviableProcessCells);
        columnAviableNr.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asString());
        columnAviableName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnAviableType.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asString());
        
    }

}