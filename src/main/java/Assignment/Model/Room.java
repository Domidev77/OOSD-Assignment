package Assignment.Model;

public class Room {

    int roomNumber;
    double roomPrice;
    boolean occupancyStatus;
    String cleaningStatus;
    String roomDescription;
    Lease lease;

    Room(int roomNumber, double roomPrice, boolean occupancyStatus, String cleaningStatus, String roomDescription){
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.occupancyStatus = occupancyStatus;
        this.cleaningStatus = cleaningStatus;
        this.roomDescription = roomDescription;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void setRoomPrice(double roomPrice){
        this.roomPrice = roomPrice;
    }

    public void setOccupancyStatus(boolean occupancyStatus){
        this.occupancyStatus = occupancyStatus;
    }

    public void setCleaningStatus(String cleaningStatus){
        this.cleaningStatus = cleaningStatus;
    }

    public void setRoomDescription(String roomDescription){
        this.roomDescription = roomDescription;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public double getRoomPrice(){
        return roomPrice;
    }

    public boolean getOccupancyStatus(){
        return occupancyStatus;
    }

    public String getCleaningStatus(){
        return cleaningStatus;
    }

    public String getRoomDescription(){
        return roomDescription;
    }

    public Lease getLease(){
        return lease;
    }

    public void addLease(Lease lease){
        this.lease = lease;
    }

    public void removeLease(){
    }
}
