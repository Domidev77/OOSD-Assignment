package Assignment.Model;

public class Lease {

    int leaseNumber;
    int durationOfTheLeaseInMonths;
    Student student;

    public Lease(int leaseNumber, int durationOfTheLeaseInMonths, Student student){
        this.leaseNumber = leaseNumber;
        this.durationOfTheLeaseInMonths = durationOfTheLeaseInMonths;
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

}
