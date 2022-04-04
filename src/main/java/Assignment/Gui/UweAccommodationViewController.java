package Assignment.Gui;

import Assignment.Main;
import Assignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Domi
 */
public class UweAccommodationViewController implements Initializable {
    //extends Application
    ObservableList<String> occupancyStatusList = FXCollections.observableArrayList("Unoccupied", "Occupied");
    ObservableList<String> cleaningStatusList = FXCollections.observableArrayList("Clean","Dirty","Offline");

    public TableView<Product> tableview;

    public TableColumn<Product, Integer> colLeaseNumber;
    public TableColumn<Product, String> colHallName;
    public TableColumn<Product, Integer> colHallNumber;
    public TableColumn<Product, Integer> colRoomNumber;
    public TableColumn<Product, String> colStudentName;
    public TableColumn<Product, String> colOccupancyStatus;
    public TableColumn<Product, String> colCleaningStatus;
    public TableColumn<Product, String> colRoomPrice;
    public TableColumn<Product, String> colRoomDescription;
    public TextField textLeaseNumber;
    public TextField textStudentName;
    public TextField textHallName;
    public TextField textHallNumber;
    public TextField textRoomNum;
    public TextField textRoomPrice;
    public TextArea textRoomDes;
    @FXML
    public ChoiceBox occupancyStatusChoiceBox;
    public ChoiceBox cleaningStatusChoiceBox;
    @FXML
    public Button buttonLogout;

    private TableRow<Product> selectedRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cleaningStatusChoiceBox.getItems().addAll(cleaningStatusList);
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
        colOccupancyStatus.setCellFactory(ChoiceBoxTableCell.forTableColumn());
        colCleaningStatus.setCellFactory(ChoiceBoxTableCell.forTableColumn());
        textRoomDes.setWrapText(true);

        tableview.setRowFactory(productTableView -> {
            final TableRow<Product> row = new TableRow<>();

            row.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
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
            });
            return row;
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
        cleaningStatusChoiceBox.setValue(product.getCleaningStatus());
        textRoomPrice.setText(String.valueOf(product.getRoomPrice()));
        textRoomDes.setText(product.getRoomDescription());
        textHallNumber.setDisable(true);
        textHallName.setDisable(true);
        textRoomNum.setDisable(true);

        if (product.getCleaningStatus() == "Offline"){
            textLeaseNumber.setDisable(true);
            textStudentName.setDisable(true);
            occupancyStatusChoiceBox.setDisable(true);
        }
        else {
            textLeaseNumber.setDisable(false);
            textStudentName.setDisable(false);
            occupancyStatusChoiceBox.setDisable(false);

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
        cleaningStatusChoiceBox.setValue("");
        textRoomPrice.setText("");
        textRoomDes.setText("");

    }


    public void selectedNewTableRow(Product row){
        textLeaseNumber.setText(String.valueOf(row.getLeaseNumber()));
        textHallName.setText(row.getHallName());
        textHallNumber.setText(String.valueOf(row.getHallNumber()));
        textRoomNum.setText(String.valueOf(row.getRoomNumber()));
        textStudentName.setText(row.getStudentName());
        occupancyStatusChoiceBox.setValue(row.getOccupancyStatus());
        cleaningStatusChoiceBox.setValue(row.getCleaningStatus());
        textRoomPrice.setText(String.valueOf(row.getRoomPrice()));
        textRoomDes.setText(row.getRoomDescription());

    }

    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product(1, "WallCourt", 2, 1, "Albert Duff",
                    "Occupied", "Clean", 500, "A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(2,"WallCourt",2,2,"Tom Smith",
                    "Occupied","Clean",550,"A single room with a bed" +
                    "desk and chair and a window view"),
            new Product(3,"WallCourt",2,3,"Kate Jones",
                    "Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,3,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
            "and a desk and chair"),
            new Product(4,"WallCourt", 2,5,"Connor Miller","Occupied","Dirty",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(5,"WallCourt", 2,6,"George Gay","Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,7,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,8,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(10,"Waterside", 3,1,"Kim Perry","Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,2,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(20,"Waterside", 3,3,"John Crown","Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,40,"","Unoccupied","Dirty",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,50,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,60,"Allison Goodall","Unoccupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,70,"Jennifer Barlow","Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,80,"","Unoccupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair")
    );



    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),occupancyStatusChoiceBox.getValue().toString(),cleaningStatusChoiceBox.getValue().toString(),
                Double.parseDouble(textRoomPrice.getText()),textRoomDes.getText());

        if(occupancyStatusChoiceBox.getValue() == "Unoccupied"){
            product.setLeaseNumber(0);
            product.setStudentName("");
        }

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
        clearDisplayedItems();
    }

    public void logoutButton(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("LoginPage.fxml");
    }
    public void onEditChanged(TableColumn.CellEditEvent<Product, String> productStringCellEditEvent) {
        Product product = tableview.getSelectionModel().getSelectedItem();
        product.setCleaningStatus(productStringCellEditEvent.getNewValue());
        product.setOccupancyStatus(productStringCellEditEvent.getNewValue());

    }

    public TableRow<Product> getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(TableRow<Product> selectedRow) {
        this.selectedRow = selectedRow;
    }
}
