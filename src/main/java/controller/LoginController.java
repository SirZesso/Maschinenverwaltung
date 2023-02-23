package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.MainApp;
import service.CreateDummyData;



public class LoginController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnClickCustomer(ActionEvent event) {
        System.out.println("Login As Customer");
        this.mainApp.showCustomerView();

    }

    @FXML
    void btnClickSupplier(ActionEvent event) {
        System.out.println("Login As Supplier");
        this.mainApp.showSupplierView();
    }

    // @FXML
    // void creadDummyData(ActionEvent event) {
    //     CreateDummyData.create();
    // }





}
