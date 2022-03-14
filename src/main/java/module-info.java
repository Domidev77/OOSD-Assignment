module com.example.assignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens Assignment to javafx.fxml;
    exports Assignment;
    exports Assignment.Gui;
    opens Assignment.Gui to javafx.fxml;
    exports Assignment.Model;
    opens Assignment.Model to javafx.fxml;
}