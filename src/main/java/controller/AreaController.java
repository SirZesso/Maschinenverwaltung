package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.MainApp;
import model.machine.*;
import service.SerializationService;
import model.customer.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AreaController {

    private static ObservableList<ProcessCell> processCells;
    private static ObservableList<Area> areas;
    private static ObservableList<Site> sites;
    private MainApp mainApp;
    Area selectedArea;
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
    private TableColumn<ProcessCell, Integer> columnSpezification;
    @FXML
    private TableColumn<ProcessCell, MachineType> columnType;
    @FXML
    private Label labelSpecification;
    @FXML
    private TableView<Area> tableAreas;
    @FXML
    private TableView<ProcessCell> tableProcessCells;
    @FXML
    private TextArea textareaDescription;

    // Methodes******************************************************
    public void setMainApp(MainApp mainApp) {
       this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

        sites = createSites();
        //areas = createDemoAreas();

        processCells = SerializationService.deSerializeProcessCellDatao();
        //sites = SerializationService.deSerializeSiteDatao();
        areas = SerializationService.deSerializeAreaDatao();
        
        

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

    }

    private ObservableList<Site> createSites(){

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
        areas.add(new Area("W01","T0 Montagepark", "Automatisierte Montage von T0 Komponenten", Floor.EG, 643.00,"Sascha Seepferd"));
        areas.add(new Area("W02","LaserXD", "Manuelle visuelle Kontrolle von Lasergravuren", Floor.OG3, 643.00,"Nina Nielpferd"));

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
    void addProcessCell(ActionEvent event) {

    }
    private void showAreaInfo(Area area) {
        if (area != null) {
            // Label
            labelSpecification.setText(area.getName());
            // Textfield
            choiceboxSite.setValue(area.getSiteId());
            choiceboxFloor.setValue(area.getFloor());

        } else {

        }

    }

}
