module com.example.javagroup_10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javagroup_10 to javafx.fxml;
    exports com.example.javagroup_10;
}