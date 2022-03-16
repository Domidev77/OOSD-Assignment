package Assignment.Gui;

import Assignment.Main;
import Assignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Domi
 */
public class ManagerViewController implements Initializable {
    //extends Application

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
    public Button buttonLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colLeaseNumber.setCellValueFactory(new PropertyValueFactory<>("LeaseNumber"));
        colHallName.setCellValueFactory(new PropertyValueFactory<>("HallName"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("HallNumber"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colOccupancyStatus.setCellValueFactory(new PropertyValueFactory<>("OccupancyStatus"));
        colCleaningStatus.setCellValueFactory(new PropertyValueFactory<>("CleaningStatus"));
        colRoomPrice.setCellValueFactory(new PropertyValueFactory<>("RoomPrice"));
        colRoomDescription.setCellValueFactory(new PropertyValueFactory<>("RoomDescription"));
        tableview.setItems(observableList);

        tableview.setEditable(true);
        colOccupancyStatus.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product(1, "WallCourt", 2, 1, "Albert Bielecki",
                    "Unavailable", "Clean", 500, "A single room with a bed, wardrobe" +
                    "and a desk and chair")
    );

    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),textOccupancyStatus.getText(),textCleaningStatus.getText(),
                Integer.parseInt(textRoomPrice.getText()),textRoomDes.getText());
        tableview.getItems().add(product);

    }
    /*
    public TableColumn<Product, String> getColOccupancyStatus() {
        return colOccupancyStatus;
    }

    public void setColOccupancyStatus(TableColumn<Product, String> colOccupancyStatus) {
        this.colOccupancyStatus = colOccupancyStatus;
    }
    */
    public void logoutButton(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("LoginPage.fxml");
    }

    public void onEditChanged(TableColumn.CellEditEvent<Product, String> productStringCellEditEvent) {
        Product product = tableview.getSelectionModel().getSelectedItem();
        product.setOccupancyStatus(productStringCellEditEvent.getNewValue());
    }
}
