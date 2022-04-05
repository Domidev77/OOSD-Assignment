package Assignment.Model;

import java.util.List;

public class StudentAccommodationOffice extends All {

    HallManager hallManager;
    List<HallOfResidence> hallOfResidences;

    public StudentAccommodationOffice(HallManager hallManager, List<HallOfResidence> hallOfResidences){
        this.hallManager = hallManager;
        this.hallOfResidences = hallOfResidences;
    }

    public int getTotalNumberOfRooms(){

        int totalNumberOfRooms = 0;

        for (int i=0; i<hallOfResidences.size(); i++)
        {
            totalNumberOfRooms += hallOfResidences.get(i).rooms.size();
        }

        return totalNumberOfRooms;
    }
}
