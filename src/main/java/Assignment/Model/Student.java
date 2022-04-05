package Assignment.Model;

public class Student {

    String studentName;
    int studentId;

    public Student(String studentName, int studentId){
        this.studentName = studentName;
        this.studentId = studentId;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    public String getStudentName(){
        return studentName;
    }

    public int getStudentId(){
        return studentId;
    }
}
