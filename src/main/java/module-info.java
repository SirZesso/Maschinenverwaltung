module ch.hftm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens ch.hftm to javafx.fxml;
    exports ch.hftm;
}