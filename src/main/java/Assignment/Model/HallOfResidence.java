package Assignment.Model;

import java.util.List;

public class HallOfResidence {

    int hallNumber;
    String hallName;
    String hallAddress;
    int hallTelephoneNumber;
    HallWarden hallWarden;
    List<Room> rooms;

    HallOfResidence(int hallNumber, String hallName, String hallAddress, int hallTelephoneNumber, HallWarden hallWarden){
        this.hallNumber = hallNumber;
        this.hallName = hallName;
        this.hallAddress = hallAddress;
        this.hallTelephoneNumber = hallTelephoneNumber;
        this.hallWarden = hallWarden;
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

    public int getHallTelephoneNumber(){
        return hallTelephoneNumber;
    }

}
