module com.example.userlistapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.userlistapp to javafx.fxml;
    exports com.example.userlistapp;
}
