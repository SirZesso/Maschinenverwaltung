package main;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class SharedMenu extends MenuBar {
    public MenuBar init(MainApp main) {
        MenuBar menuBar = new MenuBar();

        // Menu Creation
        Menu menuNew = new Menu("Neu");
        MenuItem areaCreation = new MenuItem("Area erstellen");
        areaCreation.setOnAction(e -> {
            main.showSupplierView();
        });

        MenuItem processCellCreation = new MenuItem("Process Cell erstellen");
        processCellCreation.setOnAction(e -> {
            main.showSupplierView();
        });

        menuNew.getItems().add(areaCreation);
        menuNew.getItems().add(processCellCreation);

        // Menu Settings
        Menu menuSettings = new Menu("Einstellungen");
        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> {
            main.showMainView();
        });

        MenuItem about = new MenuItem("App Autors");
        about.setOnAction(e -> {
        });

        menuSettings.getItems().add(logout);
        menuSettings.getItems().add(about);

        //
        menuBar.getMenus().add(menuNew);
        menuBar.getMenus().add(menuSettings);
        return menuBar;
    }

}
