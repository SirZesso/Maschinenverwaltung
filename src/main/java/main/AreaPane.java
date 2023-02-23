package main;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.customer.Floor;

public class AreaPane extends BorderPane {

    public BorderPane init() {
        BorderPane borderPane = new BorderPane();

        TextField title = new TextField("Platinen Fertigung");
        title.maxWidth(200);
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        TextArea description = new TextArea("Maschinelle Herstellung von Platinen");
        description.maxWidth(200);
        description.minHeight(60);

        Separator divider = new Separator();
        Separator divider2 = new Separator();

        Text area = new Text("Werk");
        // ObservableList<String> siteList =
        // FXCollections.observableList(eCtr.getSiteNames());
        ComboBox<String> choiceSite = new ComboBox<>();
        // choiceSite.setItems(siteList);
        choiceSite.getSelectionModel().selectFirst();
        choiceSite.setMinWidth(200);

        Text level = new Text("Etage");
        ComboBox<Floor> choiceLevel = new ComboBox<>();
        choiceLevel.setItems(FXCollections.observableArrayList(Floor.values()));
        choiceLevel.getSelectionModel().select(Floor.EG);
        choiceLevel.setMinWidth(200);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(area, 0, 0);
        grid.add(choiceSite, 1, 0);
        grid.add(level, 0, 1);
        grid.add(choiceLevel, 1, 1);

        Text machineTypes = new Text("Maschinen Typen");
        machineTypes.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));

        Button addMachineTypes = new Button("+");
        addMachineTypes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // showRelationManagerView(choiceSite.getSelectionModel().getSelectedItem());
            }
        });

        HBox hMachineTypes = new HBox(10, machineTypes, addMachineTypes);
        hMachineTypes.setAlignment(Pos.CENTER_LEFT);

        ListView<String> machineList = new ListView<String>();
        machineList.maxHeight(30);
        machineList.setItems(null);

        Button btnSaveArea = new Button();
        btnSaveArea.setText("Speichern");

        Button btnDeleteArea = new Button();
        btnDeleteArea.setText("Löschen");

        HBox actionBtns = new HBox(10, btnDeleteArea, btnSaveArea);
        actionBtns.setAlignment(Pos.CENTER_RIGHT);
        actionBtns.setPadding(new Insets(20, 20, 20, 20));

        VBox hContent = new VBox(10, title, description, divider, grid, divider2, hMachineTypes, machineList);
        hContent.setAlignment(Pos.TOP_LEFT);
        hContent.setPadding(new Insets(25, 25, 25, 25));

        borderPane.setCenter(hContent);

        return borderPane;
    }

}
