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
public class WardenViewController implements Initializable {
    //extends Application
    ObservableList<String> cleaningStatusList = FXCollections.observableArrayList("Clean","Dirty","Offline");
    ObservableList<String> cleaningStatusOccupiedList = FXCollections.observableArrayList("Clean","Dirty");

    ObservableList<String> occupancyStatusList = FXCollections.observableArrayList("Available", "Unavailable");

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
    public TextArea textRoomDes;
    public TextField textOccupancyStatus;
    public ChoiceBox cleaningStatusChoiceBox;
    @FXML
    public Button logoutButton;

    private TableRow<Product> selectedRow;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cleaningStatusChoiceBox.getItems().addAll(cleaningStatusList);

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
        colCleaningStatus.setCellFactory(ChoiceBoxTableCell.forTableColumn());
        textRoomDes.setWrapText(true);
        textOccupancyStatus.setDisable(true);

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

        if(product.getOccupancyStatus() == "Occupied"){

            cleaningStatusChoiceBox.getItems().setAll(cleaningStatusOccupiedList);
        }
        else {
            cleaningStatusChoiceBox.getItems().setAll(cleaningStatusList);
        }

        textLeaseNumber.setText(String.valueOf(product.getLeaseNumber()));
        textHallName.setText(product.getHallName());
        textHallNumber.setText(String.valueOf(product.getHallNumber()));
        textRoomNum.setText(String.valueOf(product.getRoomNumber()));
        textStudentName.setText(product.getStudentName());
        cleaningStatusChoiceBox.setValue(product.getCleaningStatus());
        textOccupancyStatus.setText(product.getOccupancyStatus());
        textRoomPrice.setText(String.valueOf(product.getRoomPrice()));
        textRoomDes.setText(product.getRoomDescription());
        textLeaseNumber.setDisable(true);
        textStudentName.setDisable(true);
        textRoomNum.setDisable(true);
        textHallNumber.setDisable(true);
        textHallName.setDisable(true);
        textRoomPrice.setDisable(true);
    }

    public void clearDisplayedItems() {
        selectedRow = null;

        textLeaseNumber.setText("");
        textHallName.setText("");
        textHallNumber.setText("");
        textRoomNum.setText("");
        textStudentName.setText("");
        textOccupancyStatus.setText("");
        cleaningStatusChoiceBox.setValue("");
        textRoomPrice.setText("");
        textRoomDes.setText("");
    }

    ObservableList<Product> observableList = FXCollections.observableArrayList(
            new Product(1, "WallCourt", 2, 1, "Albert Dover",
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
            new Product(0,"WallCourt", 2,7,"","Unoccupied","Clean",500,"A single room with a bed, wardrobe" +
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
            new Product(0,"Waterside", 3,50,"","Unoccupied","Clean",500,"A single room with a bed, wardrobe" +
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
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),textOccupancyStatus.getText(), cleaningStatusChoiceBox.getValue().toString(),
                Double.parseDouble(textRoomPrice.getText()),textRoomDes.getText());

        tableview.getItems().set(selectedRow.getIndex(), product);
        clearDisplayedItems();
    }

    public TableColumn<Product, String> getColOccupancyStatus() {
        return colOccupancyStatus;
    }

    public void logoutButton(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("LoginPage.fxml");
    }
    public void onEditChanged(TableColumn.CellEditEvent<Product, String> productStringCellEditEvent) {
        Product product = tableview.getSelectionModel().getSelectedItem();
        product.setCleaningStatus(productStringCellEditEvent.getNewValue());

    }

    public TableRow<Product> getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(TableRow<Product> selectedRow) {
        this.selectedRow = selectedRow;
    }
}
