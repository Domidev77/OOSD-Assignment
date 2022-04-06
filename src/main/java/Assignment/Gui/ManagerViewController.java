package Assignment.Gui;

import Assignment.Data.AccommodationData;
import Assignment.Main;
import Assignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

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

    ObservableList<Product> observableList = new AccommodationData().loadData();

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

        // Validate lease number
        //-----------------------------------------------
        int leaseNumber;
        try {
            leaseNumber = Integer.parseInt(textLeaseNumber.getText());
        }
        catch (Exception e){
            showErrorMessage("Lease number is invalid");
            return;
        }

        if(leaseNumber <= 0  && occupancyStatusChoiceBox.getValue() == "Occupied"){
            showErrorMessage("Lease number cannot be negative or zero");
            return;
        }
        //-----------------------------------------------

        // Validate room price
        //-----------------------------------------------
        double roomPrice;
        try {
            roomPrice = Double.parseDouble(textRoomPrice.getText());
        }
        catch (Exception e){
            showErrorMessage("Price is invalid");
            return;
        }

        if(roomPrice <= 0){
            showErrorMessage("Price cannot be negative or zero");
            return;
        }
        //-----------------------------------------------

        Product product = new Product(leaseNumber,textHallName.getText(),Integer.parseInt(textHallNumber.getText()),
                Integer.parseInt(textRoomNum.getText()),textStudentName.getText(),occupancyStatusChoiceBox.getValue().toString(),textCleaningStatus.getText(),
                roomPrice,textRoomDes.getText());

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

    public static void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("DevLaunch Dialog");
        alert.setHeaderText("An error has been encountered");
        alert.setContentText(message);//from   www  .  ja va  2 s  .com

        alert.showAndWait();
    }
}
