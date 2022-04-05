package Assignment.Model;

public class Room {

    int roomNumber;
    double roomPrice;
    String cleaningStatus;
    String roomDescription;
    Lease lease;

    public Room(int roomNumber, double roomPrice, String cleaningStatus, String roomDescription, Lease lease){
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.cleaningStatus = cleaningStatus;
        this.roomDescription = roomDescription;
        this.lease = lease;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void setRoomPrice(double roomPrice){
        this.roomPrice = roomPrice;
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

    public boolean getOccupancyStatus(){ return lease != null; }

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
