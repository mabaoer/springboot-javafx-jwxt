package example.controller.teacher;
/*
created by yzx
 */
import de.felixroske.jfxsupport.FXMLController;
import example.entity.Course;
import example.service.teacher.CourseService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static example.controller.login.LoginController.teacher;


@FXMLController
public class CourseController implements Initializable {

    @FXML private Button cancel;
    @FXML private Button commit;
    @FXML private TextField courseName;
    @FXML private TextField number;
    @FXML private TextField courseTime;
    @FXML private TextField classRoom;
    @FXML private TextField courseWeek;
    @FXML private TextField courseType;
    @FXML private TextField collegeId;
    @FXML private TextField credit;
    @FXML private TextField trainProId;
    @FXML private TextField endTime;
    private ApplicationContext applicationContext;
    private CourseService courseService;
    @Autowired
    public CourseController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        courseService = this.applicationContext.getBean(CourseService.class);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML private void Commit(ActionEvent event) {
        Course course = isDataValidAndCreate();
        if(course != null) {
            Alert alert  = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successful");
            alert.showAndWait();
            courseService.SaveCourse(course);
        }
        else {
            Alert alert  = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    @FXML private void Cancel(ActionEvent event) {
        TeacherController.getTabPane().getSelectionModel().select(0);
    }

    private Course isDataValidAndCreate() {
        Course course = new Course();

        if(courseName.getText().length() < 15)
            course.setCourseName(courseName.getText());
        else
            return null;

        if(Integer.parseInt(number.getText()) > 10)
            course.setNumber(Integer.parseInt(number.getText()));
        else
            return null;

        course.setCourseTime(courseTime.getText());

        course.setClassRoom(classRoom.getText());

        if(Integer.parseInt(courseWeek.getText()) > 3)
            course.setCourseWeek(Integer.parseInt(courseWeek.getText()));
        else
            return null;

        course.setCourseType(courseType.getText());

        if(Integer.parseInt(collegeId.getText()) < 10)
            course.setCollegeId(Integer.parseInt(collegeId.getText()));
        else
            return null;

        if(Integer.parseInt(credit.getText()) < 7)
            course.setCredit(Integer.parseInt(credit.getText()));
        else
            return null;

        if(Integer.parseInt(trainProId.getText()) < 10)
            course.setTrainProId(Integer.parseInt(trainProId.getText()));
        else
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            course.setEndTime(sdf.parse(endTime.getText()));
        } catch (ParseException e) {
            return null;
        }

        course.setPermission(0);

        course.setTeacherId(teacher.getTeacherId());

        course.setRestNumber(course.getNumber());

        course.setCourseId(-1);

        return course;
    }
}
