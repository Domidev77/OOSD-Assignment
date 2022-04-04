package Assignment.Gui;

import Assignment.Main;
import Assignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

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

        textLeaseNumber.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, intValidationFormatter));
        textRoomPrice.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0, doubleValidationFormatter));

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
        textCleaningStatus.setDisable(true);

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
        textCleaningStatus.setText(product.getCleaningStatus());
        textRoomPrice.setText(String.valueOf(product.getRoomPrice()));
        textRoomDes.setText(product.getRoomDescription());
        textHallName.setDisable(true);
        textHallNumber.setDisable(true);
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
        textCleaningStatus.setText("");
        textRoomPrice.setText("");
        textRoomDes.setText("");

    }

    public void buttonApply(ActionEvent actionEvent) {
        Product product = new Product(Integer.parseInt(textLeaseNumber.getText()),textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),occupancyStatusChoiceBox.getValue().toString(),textCleaningStatus.getText(),
                Double.parseDouble(textRoomPrice.getText()),textRoomDes.getText());

        if(occupancyStatusChoiceBox.getValue() == "Unoccupied"){
            product.setLeaseNumber(0);
            product.setStudentName("");
        }

        if(selectedRow != null){
            tableview.getItems().set(selectedRow.getIndex(), product);
        }
        else {
            tableview.getItems().add(product);
        }

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
            new Product(0,"WallCourt", 2,4,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(4,"WallCourt", 2,5,"Connor Miller","Occupied","Dirty",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(5,"WallCourt", 2,6,"George Gay","Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,7,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"WallCourt", 2,8,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(10,"Waterside", 3,10,"Kim Perry","Occupied","Clean",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(0,"Waterside", 3,20,"","Unoccupied","Offline",500,"A single room with a bed, wardrobe" +
                    "and a desk and chair"),
            new Product(20,"Waterside", 3,30,"John Crown","Occupied","Clean",500,"A single room with a bed, wardrobe" +
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

    UnaryOperator<TextFormatter.Change> intValidationFormatter = change -> {
        if (!change.getText().matches("\\d+")) {
            change.setText(""); //else make no change
            change.setRange(    //don't remove any selected text either.
                    change.getRangeStart(),
                    change.getRangeStart()
            );
        }
        return change; //if change is a number
    };

    UnaryOperator<TextFormatter.Change> doubleValidationFormatter = change -> {
        if (!change.getText().matches("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?")) {
            change.setText(""); //else make no change
            change.setRange(    //don't remove any selected text either.
                    change.getRangeStart(),
                    change.getRangeStart()
            );
        }
        return change; //if change is a number
    };
}
