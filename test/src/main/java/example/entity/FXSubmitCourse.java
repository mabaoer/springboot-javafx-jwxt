package example.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class FXSubmitCourse {
    CheckBox checkBox = new CheckBox();
    private final SimpleIntegerProperty courseID = new SimpleIntegerProperty();
    private final SimpleStringProperty courseName = new SimpleStringProperty();
    private final SimpleIntegerProperty teacherID = new SimpleIntegerProperty();
    private final SimpleStringProperty courseTime = new SimpleStringProperty();
    private final SimpleStringProperty classRoom = new SimpleStringProperty();
    private final SimpleStringProperty permission = new SimpleStringProperty();



    public  FXSubmitCourse(int cid, String cN, int tid, String cT, String cR, String p){
        setTeacherID(tid);
        setPermission(p);
        setCourseTime(cT);
        setCourseName(cN);
        setCourseID(cid);
        setClassRoom(cR);
        setCheckBox();
    }

    public void setCheckBox(){
        checkBox = new CheckBox();
    }

    public CheckBox getCheckBox(){
        return checkBox;
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

    public int getTeacherID() {
        return teacherID.get();
    }

    public SimpleIntegerProperty teacherIDProperty() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID.set(teacherID);
    }

    public String getCourseTime() {
        return courseTime.get();
    }

    public SimpleStringProperty courseTimeProperty() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime.set(courseTime);
    }

    public String getClassRoom() {
        return classRoom.get();
    }

    public SimpleStringProperty classRoomProperty() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom.set(classRoom);
    }

    public String getPermission() {
        return permission.get();
    }

    public SimpleStringProperty permissionProperty() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission.set(permission);
    }


}
