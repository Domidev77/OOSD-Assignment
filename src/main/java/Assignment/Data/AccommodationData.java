package Assignment.Data;
import Assignment.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

public class AccommodationData {

    HallManager hallManager = new HallManager();

    // Student Village
    //-------------------------------------------------------
    HallWarden hallWarden1 = new HallWarden();

    List<Room> studentVillageRooms = Arrays.asList(
            new Room(101, 500.0, "Clean", "Single bedroom with en-suite", new Lease(1, 12, new Student("John Smith", 20010010))),
            new Room(102, 850.0, "Offline", "Single bedroom with en-suite, nice view", null),
            new Room(103, 550.0, "Clean", "Single bedroom with en-suite", new Lease(2, 12, new Student("Victoria Baker", 20010011))),
            new Room(104, 650.0, "Dirty", "Single bedroom", new Lease(3, 12, new Student("Sarah Kowalska", 20010017))),
            new Room(105, 600.0, "Clean", "Single bedroom", new Lease(4, 12, new Student("Andy Knight", 20010019)))
    );
    //-------------------------------------------------------

    // Wallscourt Park
    //-------------------------------------------------------
    HallWarden hallWarden2 = new HallWarden();

    List<Room> wallscourtParkRooms = Arrays.asList(
            new Room(201, 500.0, "Clean", "Single bedroom with en-suite", new Lease(5, 12, new Student("Jason Holland", 20010012))),
            new Room(202, 850.0, "Clean", "Single bedroom with en-suite", new Lease(6, 12, new Student("Anne Doe", 20010013))),
            new Room(203, 550.0, "Dirty", "Single bedroom with en-suite, nice view", null),
            new Room(204,550.0,"Clean","Single bedroom with en-suite, nice view",null),
            new Room(205, 600.0, "Dirty", "Single bedroom", new Lease(7, 12, new Student("Bob Smith", 20030010)))
    );
    //-------------------------------------------------------

    List<HallOfResidence> halls = Arrays.asList(
            new HallOfResidence(1, "Student Village", "Student Village, UWE Bristol, Coldharbour Lane, Frenchay, Bristol", "+44 1275 100101", hallWarden1, studentVillageRooms),
            new HallOfResidence(1, "Wallscourt Park", "Wallscourt Park, UWE Bristol, Coldharbour Lane, Frenchay, Bristol, BS16 1ZU", "+44 1275 100102", hallWarden2, wallscourtParkRooms)
    );

    StudentAccommodationOffice accommodation = new StudentAccommodationOffice(hallManager, halls);

    public ObservableList<Product> loadData(){

        ObservableList<Product> observableList = FXCollections.observableArrayList();
        AccommodationData accommodationData = new AccommodationData();

        for (int i = 0; i < accommodationData.accommodation.getHallOfResidences().size(); i++) {

            HallOfResidence hallOfResidence = accommodationData.accommodation.getHallOfResidences().get(i);
            List<Room> roomList = hallOfResidence.getRooms();

            for (int j = 0; j < roomList.size(); j++) {
                Room room = roomList.get(j);
                Lease lease = room.getLease();
                Student student = null;
                if(lease != null)
                {
                    student = lease.getStudent();
                }

                Product product = new Product(
                        lease != null ? lease.getLeaseNumber() : 0,
                        hallOfResidence.getHallName(),
                        hallOfResidence.getHallNumber(),
                        room.getRoomNumber(),
                        lease != null ? student.getStudentName() : "",
                        room.getOccupancyStatus() == true ? "Occupied" : "Unoccupied",
                        room.getCleaningStatus(),
                        room.getRoomPrice(),
                        room.getRoomDescription());

                observableList.add(product);
            }
        }

        return observableList;
    }
}
