package Assignment.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleIntegerProperty leaseNumber;
    private SimpleStringProperty hallName;
    private SimpleIntegerProperty hallNumber;
    private SimpleIntegerProperty roomNumber;
    private SimpleStringProperty studentName;
    private SimpleStringProperty occupancyStatus;
    private SimpleStringProperty cleaningStatus;
    private SimpleDoubleProperty roomPrice;
    private SimpleStringProperty roomDescription;

    public Product(Integer leaseNumber, String hallName, int hallNumber, int roomNumber, String studentName, String occupancyStatus, String cleaningStatus, double roomPrice, String roomDescription) {
        this.leaseNumber = new SimpleIntegerProperty(leaseNumber);
        this.hallName = new SimpleStringProperty(hallName);
        this.hallNumber = new SimpleIntegerProperty(hallNumber);
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
        this.studentName = new SimpleStringProperty(studentName);
        this.occupancyStatus = new SimpleStringProperty(occupancyStatus);
        this.cleaningStatus = new SimpleStringProperty(cleaningStatus);
        this.roomPrice = new SimpleDoubleProperty(roomPrice);
        this.roomDescription = new SimpleStringProperty(roomDescription);
    }

    public Integer getLeaseNumber() {
        return leaseNumber.get();
    }

    public void setLeaseNumber(Integer leaseNumber) {
        this.leaseNumber = new SimpleIntegerProperty(leaseNumber);;
    }

    public String getHallName() {
        return hallName.get();
    }

    public void setHallName(String hallName) {
        this.hallName = new SimpleStringProperty(hallName);;
    }

    public int getHallNumber() {
        return hallNumber.get();
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = new SimpleIntegerProperty(hallNumber);
    }

    public int getRoomNumber() {
        return roomNumber.get();
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName = new SimpleStringProperty(studentName);
    }

    public String getOccupancyStatus() {
        return occupancyStatus.get();
    }

    public void setOccupancyStatus(String occupancyStatus) {
        this.occupancyStatus = new SimpleStringProperty(occupancyStatus);
    }

    public String getCleaningStatus() {
        return cleaningStatus.get();
    }

    public void setCleaningStatus(String cleaningStatus) {
        this.cleaningStatus = new SimpleStringProperty(cleaningStatus);
    }

    public double getRoomPrice() {
        return roomPrice.get();
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = new SimpleDoubleProperty(roomPrice);
    }

    public String getRoomDescription() {
        return roomDescription.get();
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = new SimpleStringProperty(roomDescription);
    }


}
