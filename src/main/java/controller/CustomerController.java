package controller;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.MainApp;
import javafx.scene.control.Label;


public class CustomerController{
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private Label lblOut;

    @FXML
    void btnClickAction(ActionEvent event) {
        System.out.println("Button Test");
        this.mainApp.showMainView();
    }


}
