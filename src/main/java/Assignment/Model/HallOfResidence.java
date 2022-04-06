package Assignment.Model;
import java.util.List;

public class HallOfResidence {

    int hallNumber;
    String hallName;
    String hallAddress;
    String hallTelephoneNumber;
    HallWarden hallWarden;
    List<Room> rooms;

    public HallOfResidence(int hallNumber, String hallName, String hallAddress, String hallTelephoneNumber, HallWarden hallWarden, List<Room> rooms){
        this.hallNumber = hallNumber;
        this.hallName = hallName;
        this.hallAddress = hallAddress;
        this.hallTelephoneNumber = hallTelephoneNumber;
        this.hallWarden = hallWarden;
        this.rooms = rooms;
    }

    public int getHallNumber(){
        return hallNumber;
    }

    public String getHallName(){
        return hallName;
    }

    public String getHallAddress(){
        return hallAddress;
    }

    public String getHallTelephoneNumber(){
        return hallTelephoneNumber;
    }

    public List<Room> getRooms() { return rooms; }

}
