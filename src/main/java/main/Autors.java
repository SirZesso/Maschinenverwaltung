package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Autors {
    public void showAutorsAlert() {
        Alert autors = new Alert(AlertType.INFORMATION);

        autors.setTitle("Maschinenverwaltung Info");
        autors.setHeaderText("Das sind die Autoren der App ");
        autors.setContentText("Patrick Schreyer\nYannic Ziegler");

        autors.show();
    }

}
