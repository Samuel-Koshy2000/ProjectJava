module org.example.womenshopfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.womenshopfx to javafx.fxml;
    exports org.example.womenshopfx;
}