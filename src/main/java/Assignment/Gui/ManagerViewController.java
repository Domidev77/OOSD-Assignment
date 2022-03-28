package Assignment.Gui;

import Assignment.Main;
import Assignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Domi
 */
public class ManagerViewController implements Initializable {
    //extends Application
    ObservableList<String> occupancyStatusList = FXCollections.observableArrayList("Unoccupied", "Occupied");

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
    public ChoiceBox occupancyStatusChoiceBox;
    public TextField textCleaningStatus;
    public Button buttonLogout;
    public Button buttonDelete;
    public TextArea textRoomDes;

    private TableRow<Product> selectedRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        occupancyStatusChoiceBox.getItems().addAll(occupancyStatusList);

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
        textRoomDes.setWrapText(true);

        //tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        //    if (newSelection != null) {


                //newSelection.

                //selectedRoom = newSelection;
                //selectedNewTableRow(selectedRoom);
        //    }
        //});

        tableview.setRowFactory(new Callback<TableView<Product>, TableRow<Product>>() {
            @Override
            public TableRow<Product> call(TableView<Product> productTableView) {
                final TableRow<Product> row = new TableRow<>();

                row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        final int i = row.getIndex();

                        // If user clicked on selected row within range
                        if (i >= 0 && i < tableview.getItems().size() && tableview.getSelectionModel().isSelected(i)) {

                            tableview.getSelectionModel().clearSelection();
                            clearDisplayedItems();
                            mouseEvent.consume();
                        }
                        // If user clicked on unselected row within range
                        else if (i >= 0 && i < tableview.getItems().size()) {
                            processSelectedRow(row);
                        }
                    }
                });
                return row;
            }
        });
    }

    public void processSelectedRow(TableRow<Product> row) {
        selectedRow = row;
        Product product = row.getItem();

        textLeaseNumber.setText(String.valueOf(product.getLeaseNumber()));
        textHallName.setText(product.getHallName());
        textHallNumber.setText(String.valueOf(product.getHallNumber()));
        textRoomNum.setText(String.valueOf(product.getRoomNumber()));
        textStudentName.setText(product.getStudentName());
        occupancyStatusChoiceBox.setValue(product.getOccupancyStatus());
        textCleaningStatus.setText(product.getCleaningStatus());
        textRoomPrice.setText(String.valueOf(product.getRoomPrice()));
        textRoomDes.setText(product.getRoomDescription());
        textHallName.setDisable(true);
        textHallNumber.setDisable(true);

        if (product.getCleaningStatus() == "Offline"){
            textLeaseNumber.setDisable(true);
            textStudentName.setDisable(true);
        }
        else if(product.getOccupancyStatus() == ""){

        }
    }

    public void clearDisplayedItems() {
        selectedRow = null;

        textLeaseNumber.setText("");
        textHallName.setText("");
        textHallNumber.setText("");
        textRoomNum.setText("");
        textStudentName.setText("");
        occupancyStatusChoiceBox.setValue("");
        textCleaningStatus.setText("");
        textRoomPrice.setText("");
        textRoomDes.setText("");
        textLeaseNumber.setDisable(false);
        textStudentName.setDisable(false);
    }

    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),occupancyStatusChoiceBox.getValue().toString(),textCleaningStatus.getText(),
                Double.parseDouble(textRoomPrice.getText()),textRoomDes.getText());

        tableview.getItems().set(selectedRow.getIndex(), product);
        clearDisplayedItems();
    }

    public void deleteButton(ActionEvent event) {
        textStudentName.clear();
        textLeaseNumber.clear();
        occupancyStatusChoiceBox.setValue("Unoccupied");

        Product product = selectedRow.getItem();
        product.setLeaseNumber(0);
        product.setStudentName("");
        product.setOccupancyStatus("Unoccupied");

        tableview.getItems().set(selectedRow.getIndex(), product);
    }

    /*
    public TableColumn<Product, String> getColOccupancyStatus() {
        return colOccupancyStatus;
    }

    public void setColOccupancyStatus(TableColumn<Product, String> colOccupancyStatus) {
        this.colOccupancyStatus = colOccupancyStatus;
    }
    */

    public void logoutButton(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("LoginPage.fxml");
    }

    public void onEditChanged(TableColumn.CellEditEvent<Product, String> productStringCellEditEvent) {
        Product product = tableview.getSelectionModel().getSelectedItem();
        product.setOccupancyStatus(productStringCellEditEvent.getNewValue());
    }



    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product(1, "WallCourt", 2, 1, "Anne Clarke",
                    "Occupied", "Clean", 500, "A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(2,"WallCourt",2,2,"Tom Smith",
                    "Occupied","Clean",550,"A single room with a bed" +
                    "desk and chair and a window view"),
            new Product(3,"WallCourt",2,3,"Kate Jones",
                    "Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,3,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair")
    );
}
