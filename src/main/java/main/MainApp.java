package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.MainApp;
import java.io.IOException;
import controller.AreaController;
import controller.LoginController;
import controller.SupplierController;

public class MainApp extends Application {
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		showMainView();

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
		try {
			// Load the fxml file and create a new scene
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/customer/layouts.fxml"));
			this.stage.setTitle("Customer");

			Scene scene = new Scene(loader.load());
			this.stage.setScene(scene);

			AreaController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
