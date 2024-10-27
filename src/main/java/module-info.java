module me.brenny.mcclone {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.brenny.mcclone to javafx.fxml;
    exports me.brenny.mcclone;
}