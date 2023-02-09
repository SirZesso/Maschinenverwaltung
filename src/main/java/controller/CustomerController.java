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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerController {

    private static ObservableList<ProcessCell> processCells;
    private static ObservableList<Area> areas;
    private static ObservableList<Site> sites;
    private MainApp mainApp;
    private Stage stage;

    private Area selectedArea;
    // FXML Elements*************************************************
    @FXML
    private Button buttonAddProcessCell;
    @FXML
    private Button buttonCreateArea;
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
    private Label labelSpecification;
    @FXML
    private TableView<Area> tableAreas;
    @FXML
    private TableView<ProcessCell> tableProcessCells;
    @FXML
    private TextArea textareaDescription;
    @FXML
    private ImageView imageProcessCell;

    // Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

        sites = createSites();
        // areas = createDemoAreas();

        // sites = SerializationService.deSerializeSiteDatao();
        areas = SerializationService.deSerializeAreaDatao();
        processCells = SerializationService.deSerializeProcessCellDatao();
        System.out.println("Cells" + processCells);
        System.out.println("Areas" + areas);

        tableAreas.setItems(areas);
        columnArea.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        showAreaInfo(null);

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
    void createArea(ActionEvent event) {

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
            // ProcessCells der Area auswählen und in die Liste einfügen
            ObservableList<ProcessCell> areaProcessCells = FXCollections.observableList(processCells.stream()
                    .filter(processCell -> {
                        if (processCell.getArea() == null || processCell.getArea().getName() == null) {
                            return false;
                        }
                        return processCell.getArea().getName().equals(area.getName());
                    })
                    .collect(Collectors.toList()));
            tableProcessCells.setItems(areaProcessCells);
            columnSerialnumber.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty().asObject());
            columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            columnType.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asString());

            // Label
            labelSpecification.setText(area.getName());
            // Textfield
            textareaDescription.setText(area.getDescription());
            choiceboxSite.setValue(area.getSiteId());
            choiceboxFloor.setValue(area.getFloor());

        } else {

        }

    }

}
