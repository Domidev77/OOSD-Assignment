package Assignment.Model;

public class Lease {

    int leaseNumber;
    int durationOfTheLeaseInMonths;
    String hallAddress;
    int roomNumber;
    Student student;

    Lease(int leaseNumber, int durationOfTheLeaseInMonths, String hallAddress, int roomNumber, Student student){
        this.leaseNumber = leaseNumber;
        this.durationOfTheLeaseInMonths = durationOfTheLeaseInMonths;
        this.hallAddress = hallAddress;
        this.roomNumber = roomNumber;
        this.student = student;
    }

    public void setLeaseNumber(int leaseNumber){
        this.leaseNumber = leaseNumber;
    }

    public void setDurationOfTheLeaseInMonths(int durationOfTheLeaseInMonths){
        this.durationOfTheLeaseInMonths = durationOfTheLeaseInMonths;
    }

    public int getLeaseNumber(){
        return leaseNumber;
    }

    public int getDurationOfTheLease(){
        return durationOfTheLeaseInMonths;
    }

    public String getHallAddress(){
        return hallAddress;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

}
