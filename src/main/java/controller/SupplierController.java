package controller;


import main.MainApp;
import model.Enterprise;
import model.machine.*;

import javafx.fxml.FXML;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;




public class SupplierController{

    private static ObservableList<ProcessCell> processcells;

    


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
