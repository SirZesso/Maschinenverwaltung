package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.MainApp;
import model.customer.Floor;

import java.io.IOException;

import controller.AreaController;
import controller.EnterpriseController;
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

		AreaController areaCtr = new AreaController();
		areaCtr.initDemoAreas();

		EnterpriseController eCtr = new EnterpriseController();
		eCtr.initDemoCustomer();

		BorderPane customerPane = new BorderPane();

		// BOTTOM
		Button btnSaveArea = new Button();
		btnSaveArea.setText("Speichern");

		Button btnDeleteArea = new Button();
		btnDeleteArea.setText("Löschen");

		Button btnCreateArea = new Button();
		btnCreateArea.setText("Neues Area erstellen");

		HBox actionBtns = new HBox(10, btnDeleteArea, btnSaveArea, btnCreateArea);
		actionBtns.setAlignment(Pos.CENTER_RIGHT);
		actionBtns.setPadding(new Insets(20, 20, 20, 20));

		customerPane.setBottom(actionBtns);

		// TOP
		MenuBar menu = new MenuBar();

		customerPane.setTop(menu);

		// RIGHT

		TextField title = new TextField("Platinen Fertigung");
		title.maxWidth(200);
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

		TextArea description = new TextArea("Maschinelle Herstellung von Platinen");
		description.maxWidth(200);
		description.minHeight(60);

		Separator divider = new Separator();
		Separator divider2 = new Separator();

		Text area = new Text("Werk");
		ObservableList<String> siteList = FXCollections.observableList(eCtr.getSiteNames());
		ComboBox<String> choiceSite = new ComboBox<>();
		choiceSite.setItems(siteList);
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
				showRelationManagerView(choiceSite.getSelectionModel().getSelectedItem());
			}
		});

		HBox hMachineTypes = new HBox(10, machineTypes, addMachineTypes);
		hMachineTypes.setAlignment(Pos.CENTER_LEFT);

		ListView<String> machineList = new ListView<String>();
		// TODO populate list based on entries
		machineList.maxHeight(30);
		machineList.setItems(siteList);

		VBox hContent = new VBox(10, title, description, divider, grid, divider2, hMachineTypes, machineList);
		hContent.setAlignment(Pos.TOP_LEFT);
		hContent.setPadding(new Insets(25, 25, 25, 25));

		customerPane.setCenter(hContent);
		BorderPane.setAlignment(btnCreateArea, Pos.CENTER);

		// LEFT
		// List with all Areas
		ListView<String> list = new ListView<String>();
		ObservableList<String> oList = FXCollections.observableArrayList(areaCtr.getCompleteAreaNameList());
		list.setItems(oList);
		list.maxWidth(50);

		customerPane.setLeft(list);

		Scene scene = new Scene(customerPane, 800, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showRelationManagerView(String inputSiteId) { // TODO Add optional inputs: Site, MachineType

		String iSite = inputSiteId;
		// TODO add parameter MachineType

		Stage relStage = new Stage();
		relStage.setTitle("Verknüpfungs Manager");

		EnterpriseController eCtr = new EnterpriseController();
		eCtr.initDemoCustomer(); // TODO bad code...

		AreaController aCtr = new AreaController();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		stage.setTitle("Customer View");

		Scene scene = new Scene(grid, 600, 300);
		relStage.setScene(scene);

		Text scenetitle = new Text("Neue Maschine zuweisen");
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

		Label site = new Label("Werk");
		grid.add(site, 0, 3);

		ObservableList<String> siteList = FXCollections.observableList(eCtr.getSiteNames());
		ComboBox<String> choiceSite = new ComboBox<>();
		choiceSite.setMaxSize(200, 0);
		choiceSite.getSelectionModel().select(inputSiteId);
		choiceSite.setItems(siteList);
		choiceSite.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				choiceSite.getSelectionModel().getSelectedItem();
				// TODO solve selction problem ...
			}
		});
		grid.add(choiceSite, 1, 3);

		Label area = new Label("Ziel Area");
		grid.add(area, 0, 4);

		ObservableList<String> areaList = FXCollections
				.observableList(aCtr.getAreaNameList(inputSiteId));
		ComboBox<String> choiceArea = new ComboBox<>();
		choiceArea.setMaxSize(200, 0);
		choiceArea.setItems(areaList);
		grid.add(choiceArea, 1, 4);

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
			// TODO: add business logic
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
