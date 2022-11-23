package main;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.MainApp;

import java.io.IOException;

import controller.CustomerController;
import controller.LoginController;

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
		this.stage.setTitle("Login");

		this.showMainView();
	}

    public void showMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/primary.fxml"));

			// Show the scene containing the root layout.
			Scene scene = new Scene(loader.load());
			this.stage.setScene(scene);
			this.stage.setMinWidth(500);
			this.stage.setMinHeight(300);
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

			Scene scene = new Scene(loader.load());
			this.stage.setScene(scene);

			// Set the prisoner into the controller and give access to the main app
			CustomerController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
