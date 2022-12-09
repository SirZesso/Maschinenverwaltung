package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainApp;
import java.io.IOException;
import controller.CustomerController;
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
		this.showUpdateRelationView(stage);
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

	public void showCustomerView() {
		try {
			// Load the fxml file and create a new scene
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/customer/layouts.fxml"));
			this.stage.setTitle("Customer");

			Scene scene = new Scene(loader.load());
			this.stage.setScene(scene);

			CustomerController controller = loader.getController();
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

	public void showUpdateRelationView(Stage s) {
		s.setTitle("Relation...");

		// TODO: update layout
		BorderPane border = new BorderPane();

		// TODO: add dropdowns to create relations
		Button btnSave = new Button("Speichern");
		// Button btnCancel = new Button("abbrechen");

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				showCustomerView();
				// TODO: change action
			}
		};
		btnSave.setOnAction(event);

		Pane p = new Pane();
		p.getChildren().add(btnSave);
		// p.getChildren().add(btnCancel);

		Scene scene = new Scene(p, 500, 200);
		s.setScene(scene);
		s.show();

	}

}
