package controller;


import main.MainApp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;



public class SupplierController{
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnClickExit(ActionEvent event) {
        System.out.println("Exit without safe");
        this.mainApp.showMainView();
    }


}
