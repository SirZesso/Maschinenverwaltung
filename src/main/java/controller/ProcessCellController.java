package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.customer.Area;
import model.machine.ProcessCell;
import service.Logger;
import service.LoggerType;
import javafx.scene.Node;


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
        ProcessCell selectedProcessCell = tabelAviableProcessCells.getSelectionModel().getSelectedItem();
        if (selectedProcessCell != null){
            selectedProcessCell.setArea(selectedArea);
            setTables();
            Logger.log(LoggerType.Customer, "ProcessCell: " + selectedProcessCell.getSerialnumber() + " added to Area: "+ selectedArea.getName());
        }

    }

    @FXML
    void removeProcessCell(ActionEvent event) {
        ProcessCell selectedProcessCell = tabelAssignedProcessCells.getSelectionModel().getSelectedItem();
        if (selectedProcessCell != null){
            selectedProcessCell.setArea(new Area());
            setTables();
            Logger.log(LoggerType.Customer, "ProccessCell: " + selectedProcessCell.getSerialnumber() + " removed from Area: "+ selectedArea.getName());

        }

    }

    @FXML
    void exitModification(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    


    public void setProcessCellsAndSelectedArea(ObservableList<ProcessCell> processCells, Area selectedArea) {
        this.processCells = processCells;
        this.selectedArea = selectedArea;
        setTables();
    }
    
    public void setTables() {
        assignedProcessCells = processCells.filtered(processCell -> {
            if (selectedArea != null && processCell.getArea() != null && processCell.getArea().getName() != null) {
                return selectedArea.getName().equals(processCell.getArea().getName());
            }
            return false;
        });
        
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