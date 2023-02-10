package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.MainApp;
import model.machine.*;
import service.SerializationService;
import model.customer.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerController {

    private ObservableList<ProcessCell> processCells;
    private ObservableList<Area> areas = FXCollections.observableArrayList();
    private ObservableList<Site> sites;
    private MainApp mainApp;
    private Stage stage;

    private Area selectedArea;
    // FXML Elements*************************************************
    @FXML
    private Button buttonAddProcessCell;
    @FXML
    private Button buttonModifyArea;
    @FXML
    private ChoiceBox<Floor> choiceboxFloor;
    @FXML
    private ChoiceBox<String> choiceboxSite;
    @FXML
    private TableColumn<Area, String> columnArea;
    @FXML
    private TableColumn<ProcessCell, String> columnName;
    @FXML
    private TableColumn<ProcessCell, Integer> columnSerialnumber;
    @FXML
    private TableColumn<ProcessCell, String> columnType;
    @FXML
    private Label labelAreaName;
    @FXML
    private TableView<Area> tableAreas;
    @FXML
    private TableView<ProcessCell> tableProcessCells;
    @FXML
    private TextArea textareaDescription;
    @FXML
    private TextField textfieldAreaName;
    @FXML
    private Text textDescription;
    @FXML
    private ImageView imageProcessCell;

    // Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

        sites = createSites();

        // sites = SerializationService.deSerializeSiteDatao();
        // areas = SerializationService.deSerializeAreaDatao();
        processCells = SerializationService.deSerializeProcessCellDatao();
        loadAreas();


        ObservableList<Area> filteredAreas = FXCollections.observableArrayList(areas.stream()
                .filter(area -> area.getName() != null && !area.getName().isEmpty())
                .collect(Collectors.toList()));

        tableAreas.setItems(filteredAreas);

        columnArea.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showAreaInfo(null);
        areaEditView(false);

        List<Floor> floors = Arrays.asList(Floor.values());
        ObservableList<Floor> floorList = FXCollections.observableList(floors);
        choiceboxFloor.setItems(floorList);

        List<String> siteIds = new ArrayList<>();
        for (Site e : sites) {
            siteIds.add(e.getId());
        }
        ObservableList<String> siteIdList = FXCollections.observableList(siteIds);
        choiceboxSite.setItems(siteIdList);

        // Listeners

        tableAreas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showAreaInfo(newValue));

        tableProcessCells.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                imageProcessCell.setImage(new Image(newValue.getImagePath()));
                System.out.println(newValue.getArea());
            } else {
                imageProcessCell.setImage(null);
            }
        });
    }

    private ObservableList<Site> createSites() {

        List<Site> sites = new ArrayList<>();
        sites.add(new Site("W01", "Schild-Ruststrasse 16", "Grenchen", 2540));
        sites.add(new Site("W02", "Oelirein 3", "Grenchen", 2540));
        sites.add(new Site("W04", "Blumenrainstrasse 1", "Grenchen", 2540));
        sites.add(new Site("W06", "Storchengasse 9", "Grenchen", 2540));

        ObservableList<Site> siteList = FXCollections.observableList(sites);
        return siteList;

    }

    private ObservableList<Area> createDemoAreas() {

        List<Area> areas = new ArrayList<>();
        areas.add(new Area("W01", "T0 Montagepark", "Automatisierte Montage von T0 Komponenten", Floor.EG, 643.00,
                "Sascha Seepferd"));
        areas.add(new Area("W02", "LaserXD", "Manuelle visuelle Kontrolle von Lasergravuren", Floor.OG3, 643.00,
                "Nina Nielpferd"));

        ObservableList<Area> areaList = FXCollections.observableList(areas);
        return areaList;
    }

    @FXML
    void btnClickExit(ActionEvent event) {
        System.out.println("Exit without safe");
        this.mainApp.showMainView();
    }

    @FXML
    void btnClickSaveExit(ActionEvent event) {
        SerializationService.serializeAreaData(areas);
        SerializationService.serializeProcessCellData(processCells);
        System.out.println("Exit");
        this.mainApp.showMainView();
    }

    @FXML
    void modifyArea(ActionEvent event) {
        if (buttonModifyArea.getText().equals("Anpassen")) {
            areaEditView(true);
        } else {
            areaEditView(false);
        }
    }

    @FXML
    void modifyProcessCell(ActionEvent event) {
        selectedArea = tableAreas.getSelectionModel().getSelectedItem();
        if (selectedArea != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/view/customer/assigneProcessCelltoArea.fxml"));
                Scene scene = new Scene(loader.load());

                ProcessCellController controller = loader.getController();
                controller.setProcessCellsAndSelectedArea(processCells, selectedArea);

                Stage stage = new Stage();
                stage.setTitle("Process Cell");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(this.stage);
                stage.setScene(scene);
                stage.showAndWait();
                showAreaInfo(selectedArea);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAreaInfo(Area area) {
        if (area != null) {

            ObservableList<ProcessCell> areaProcessCells = FXCollections.observableList(processCells.stream()
                .filter(processCell -> processCell.getArea().equals(area))
                .collect(Collectors.toList()));
            tableProcessCells.setItems(areaProcessCells);
            columnSerialnumber.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asObject());
            columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            columnType.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asString());

            // Label
            labelAreaName.setText(area.getName());
            // Textfield
            textareaDescription.setText(area.getDescription());
            textDescription.setText(area.getDescription());
            textfieldAreaName.setText(area.getName());
            choiceboxSite.setValue(area.getSiteId());
            choiceboxFloor.setValue(area.getFloor());

        } else {

        }

    }

    private void loadAreas() {
        areas.clear();
        // Add Areas from ProcessCells
        for (ProcessCell processCell : processCells) {
            if (!areas.contains(processCell.getArea())) {
                areas.add(processCell.getArea());
            }
        }
        // Add Areas from serialization
        List<Area> savedAreas = SerializationService.deSerializeAreaDatao();
        areas.addAll(savedAreas
                .stream().filter(
                        area -> area.getName() != null && !areas.stream()
                                .anyMatch(existingArea -> existingArea.getName() != null
                                        && existingArea.getName().equals(area.getName())))
                .collect(Collectors.toList()));
    }

    private void areaEditView(boolean status) {
        if (status) {
            buttonModifyArea.setText("Zurück");
        } else {
            buttonModifyArea.setText("Anpassen");
        }
        textfieldAreaName.setVisible(status);
        textareaDescription.setVisible(status);
        labelAreaName.setVisible(!status);
        textDescription.setVisible(!status);
    }

}
