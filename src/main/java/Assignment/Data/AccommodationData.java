package Assignment.Data;
import Assignment.Model.*;
import java.util.Arrays;
import java.util.List;

public class AccommodationData {

    HallManager hallManager = new HallManager();

    // Student Village
    //-------------------------------------------------------
    HallWarden hallWarden1 = new HallWarden();

    List<Room> studentVillageRooms = Arrays.asList(
            new Room(101, 500.0, "Clean", "Single bedroom with en-suite", new Lease(1, 12, new Student("John Smith", 20010010))),
            new Room(102, 850.0, "Offline", "Two bedroom with en-suite", null),
            new Room(103, 550.0, "Clean", "Single bedroom with en-suite, nice view", new Lease(3, 12, new Student("Victoria Baker", 20010012)))
    );
    //-------------------------------------------------------

    // Wallscourt Park
    //-------------------------------------------------------
    HallWarden hallWarden2 = new HallWarden();

    List<Room> wallscourtParkRooms = Arrays.asList(
            new Room(201, 500.0, "Clean", "Single bedroom with en-suite", new Lease(1, 12, new Student("Jason Holland", 20010010))),
            new Room(202, 850.0, "Clean", "Two bedroom with en-suite", new Lease(2, 12, new Student("Anne Doe", 20010011))),
            new Room(203, 550.0, "Dirty", "Single bedroom with en-suite, nice view", null)
    );
    //-------------------------------------------------------

    List<HallOfResidence> halls = Arrays.asList(
            new HallOfResidence(1, "Student Village", "Student Village, UWE Bristol, Coldharbour Lane, Frenchay, Bristol", "+44 1275 100101", hallWarden1, studentVillageRooms),
            new HallOfResidence(1, "Wallscourt Park", "Wallscourt Park, UWE Bristol, Coldharbour Lane, Frenchay, Bristol, BS16 1ZU", "+44 1275 100102", hallWarden2, wallscourtParkRooms)
    );

    public StudentAccommodationOffice accommodation = new StudentAccommodationOffice(hallManager, halls);
}
