package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ProcessCellController {

    @FXML
    private TableColumn<?, ?> columnAssignedName;

    @FXML
    private TableColumn<?, ?> columnAssignedNr;

    @FXML
    private TableColumn<?, ?> columnAssignedType;

    @FXML
    private TableColumn<?, ?> columnAviableName;

    @FXML
    private TableColumn<?, ?> columnAviableNr;

    @FXML
    private TableColumn<?, ?> columnAviableType;

    @FXML
    private TableView<?> tabelAssignedProcessCells;

    @FXML
    private TableView<?> tabelAviableProcessCells;

    private CustomerController controller;

    public void setMainApp(CustomerController customerController) {
        this.controller = customerController;
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

}