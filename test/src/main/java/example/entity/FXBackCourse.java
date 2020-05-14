package example.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class FXBackCourse {

    CheckBox checkBox = new CheckBox();


    private final SimpleIntegerProperty selectedCourseID = new SimpleIntegerProperty();
    private final SimpleIntegerProperty courseID = new SimpleIntegerProperty();
    private final SimpleStringProperty courseName = new SimpleStringProperty();
    private final SimpleIntegerProperty studentID = new SimpleIntegerProperty();
    private final SimpleStringProperty studentName = new SimpleStringProperty();

    public  FXBackCourse(int cid,String cN,int sid,String sN,int tid){
        setSelectedCourseID(tid);
        setCourseName(cN);
        setCourseID(cid);
        setStudentID(sid);
        setStudentName(sN);
        setCheckBox();
    }




    public int getSelectedCourseID() {
        return selectedCourseID.get();
    }

    public void setSelectedCourseID(int selectedCourseID) {
        this.selectedCourseID.set(selectedCourseID);
    }


    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox() {
       checkBox = new CheckBox();
    }

    public int getCourseID() {
        return courseID.get();
    }

    public SimpleIntegerProperty courseIDProperty() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID.set(courseID);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public int getStudentID() {
        return studentID.get();
    }

    public SimpleIntegerProperty studentIDProperty() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID.set(studentID);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }



}
