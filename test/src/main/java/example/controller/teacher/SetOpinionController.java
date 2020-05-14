package example.controller.teacher;
/*
created by yzx
 */

import de.felixroske.jfxsupport.FXMLController;

import example.service.teacher.CourseService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class SetOpinionController implements Initializable {
    @FXML
    private Button cancel;
    @FXML
    private Button commit;
    @FXML
    public static TextArea area;//反馈的内容
    @FXML
    public Label four;
    @FXML
    public Label five;
    @FXML
    public Label six;
    private ResourceBundle resourceBundle;
    private ApplicationContext applicationContext;
    private CourseService courseService;

    @Autowired
    public SetOpinionController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        courseService = this.applicationContext.getBean(CourseService.class);
    }


    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private void Commit(ActionEvent event) {
        Alert alert  = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successful");
        alert.showAndWait();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        TeacherController.getTabPane().getSelectionModel().select(0);
    }
}


