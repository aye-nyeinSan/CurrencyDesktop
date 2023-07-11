module com.example.chapter2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chapter2 to javafx.fxml;
    exports com.example.chapter2;
}