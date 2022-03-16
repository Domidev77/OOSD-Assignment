package Assignment.Gui;

import Assignment.Main;
import Assignment.Model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Domi
 */
public class WardenViewController implements Initializable {
    //extends Application
    ObservableList<String> cleaningStatusList = FXCollections.observableArrayList("Clean","Dirty","Offline");

    public TableView<Product> tableview;

    public TableColumn<Product, Integer> colLeaseNumber;
    public TableColumn<Product, String> colHallName;
    public TableColumn<Product, Integer> colHallNumber;
    public TableColumn<Product, Integer> colRoomNumber;
    public TableColumn<Product, String> colStudentName;
    @FXML
    private TableColumn<Product, String> colOccupancyStatus;
    public TableColumn<Product, String> colCleaningStatus;
    public TableColumn<Product, String> colRoomPrice;
    public TableColumn<Product, String> colRoomDescription;
    public TextField textLeaseNumber;
    public TextField textStudentName;
    public TextField textHallName;
    public TextField textHallNumber;
    public TextField textRoomNum;
    public TextField textRoomPrice;
    public TextField textRoomDes;
    public TextField textOccupancyStatus;
    public TextField textCleaningStatus;
    @FXML
    public Button logoutButton;
    private SimpleObjectProperty<ChoiceBox<String>> myChoiceBox = new SimpleObjectProperty<>(this, "myChoiceBox");

    //private String[] cleaningStatus ={"Clean","Dirty","Offline"};

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.get().setValue("Clean");
        myChoiceBox.get().setItems(cleaningStatusList);


        colLeaseNumber.setCellValueFactory(new PropertyValueFactory<>("LeaseNumber"));
        colHallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("HallNumber"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        getColOccupancyStatus().setCellValueFactory(new PropertyValueFactory<>("OccupancyStatus"));
        colCleaningStatus.setCellValueFactory(new PropertyValueFactory<>("CleaningStatus"));
        colRoomPrice.setCellValueFactory(new PropertyValueFactory<>("RoomPrice"));
        colRoomDescription.setCellValueFactory(new PropertyValueFactory<>("RoomDescription"));
        tableview.setItems(observableList);

        tableview.setEditable(true);

    }

    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product(1, "WallCourt", 2, 1, "Albert Bielecki",
                    "Unavailable", "Clean", 500, "A single room with a bed, wardrobe" +
                    "and a desk and chair")
    );
    /*
    public void getCleaningStatus(ActionEvent event){
        String cleaningStatus = myChoiceBox.getValue();
    }

    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),textOccupancyStatus.getText(),textCleaningStatus.getText(),
                Integer.parseInt(textRoomPrice.getText()),textRoomDes.getText());
        tableview.getItems().add(product);

    }*/
    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),textOccupancyStatus.getText(), myChoiceBox.get().getValue(),
                Integer.parseInt(textRoomPrice.getText()),textRoomDes.getText());
        tableview.getItems().add(product);

    }

    public TableColumn<Product, String> getColOccupancyStatus() {
        return colOccupancyStatus;
    }

    public void setColOccupancyStatus(TableColumn<Product, String> colOccupancyStatus) {
        this.colOccupancyStatus = colOccupancyStatus;
    }
    public void logoutButton(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("LoginPage.fxml");
    }
}
