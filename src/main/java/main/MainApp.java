package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import main.MainApp;

import java.io.IOException;

import controller.LoginController;
import controller.SupplierController;

public class MainApp extends Application {
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getStage() {
		return this.stage;
	}

	@Override
	public void start(Stage stage) {

		this.stage = stage;
		this.showMainView();
	}

	public void showMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/login.fxml"));
			this.stage.setTitle("Login");

			// Show the scene containing the root layout.
			Scene scene = new Scene(loader.load());
			this.stage.setScene(scene);
			this.stage.show();

			// Give the controller access to the main app.
			LoginController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showSupplierView() {
		try {
			// Load the fxml file and create a new scene
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/supplier/layouts.fxml"));
			this.stage.setTitle("Supplier");

			Scene scene = new Scene(loader.load());
			this.stage.setScene(scene);

			SupplierController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCustomerView() {
		stage.setTitle("Customer View");

		Button btn = new Button();
		btn.setText("Neue Verknüpfung erstellen'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showUpdateRelationView();
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(btn);

		Scene scene = new Scene(root, 300, 250);

		stage.setScene(scene);
		stage.show();

	}

	public void showUpdateRelationView() {
		Stage relStage = new Stage();
		relStage.setTitle("Verknüpfung erstellen");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		stage.setTitle("Customer View");

		Scene scene = new Scene(grid, 400, 300);
		relStage.setScene(scene);

		Text scenetitle = new Text("Maschine zuweisen");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0);

		Label machine = new Label("Maschinen Typ");
		grid.add(machine, 0, 1);

		ComboBox<String> choiceMachine = new ComboBox<>();
		choiceMachine.setMaxSize(200, 0);
		// TODO: populate list automatically
		choiceMachine.getItems().add("Laser XYZ v33");
		choiceMachine.getItems().add("Presse zehntausendundeins");
		choiceMachine.getItems().add("Presse niemandjuckts");
		grid.add(choiceMachine, 1, 1);

		Label count = new Label("Anzahl Maschinen");
		grid.add(count, 0, 2);

		NumberTextField counter = new NumberTextField();
		counter.setText("1");
		grid.add(counter, 1, 2);

		Label area = new Label("Ziel Area");
		grid.add(area, 0, 3);

		ComboBox<String> choiceArea = new ComboBox<>();
		choiceArea.setMaxSize(200, 0);
		// TODO: populate list automatically
		choiceArea.getItems().add("Atelier 1");
		choiceArea.getItems().add("Produktionshalle B");
		choiceArea.getItems().add("Maschinendepot C5");
		grid.add(choiceArea, 1, 3);

		Button btnRel = new Button("zuweisen");
		btnRel.isDefaultButton();

		Button btnCancel = new Button("abbrechen");
		btnCancel.isCancelButton();

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnCancel);
		hbBtn.getChildren().add(btnRel);
		grid.add(hbBtn, 1, 5);
		relStage.show();

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 7);

		btnRel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Aktion ausgeführt");
			}
		});

		relStage.setScene(scene);
		relStage.show();

	}

}
