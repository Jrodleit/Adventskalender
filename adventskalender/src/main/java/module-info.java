module htl.steyr.adventskalender {
    requires javafx.controls;
    requires javafx.fxml;


    opens htl.steyr.adventskalender to javafx.fxml;
    exports htl.steyr.adventskalender;
}