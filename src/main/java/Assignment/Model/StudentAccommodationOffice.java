package Assignment.Model;

import java.util.List;

public class StudentAccommodationOffice extends All {

    int totalNumberOfRooms;
    HallManager hallManager;
    List<HallOfResidence> hallOfResidences;

    StudentAccommodationOffice(HallManager hallManager){
        this.hallManager = hallManager;
    }

    public int getTotalNumberOfRooms(){
        return totalNumberOfRooms;
    }
}
