package example.controller.student;

import com.alibaba.fastjson.JSON;
import de.felixroske.jfxsupport.FXMLController;
import example.Main;
import example.controller.login.LoginController;
import example.dao.StudentRepository;
import example.entity.Course;
import example.entity.Student;
import example.service.student.StudentService;
import example.view.login.LoginView;
import example.view.student.MessageView;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author mbw
 * @date 2020/5/11 21:44
 */
@FXMLController
public class StudentController implements Initializable {
    @FXML
    WebView webview;

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MessageController messageController;
    @Autowired
    LoginController loginController;
    static Student student=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        student=loginController.student;
        final WebEngine webengine = webview.getEngine();
        String url = Main.class.getResource("/student/html/student-info.html").toExternalForm();
        webengine.load(url);
        webengine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                JSObject win = (JSObject) webengine.executeScript("window");
                win.setMember("apps", apps);//设置变量
                win.eval("studentinfo()");
            }
        });
        Stage stage = Main.getStage();
        //stage.setMaximized(true);
        double height = 1000;
        double width = 1800;
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setResizable(true);
        //stage.setAlwaysOnTop(true);
        webview.setPrefHeight(height);
        webview.setPrefWidth(width);
        Main.showView(MessageView.class, Modality.NONE);
    }

    js apps = new js();

    public class js {
        public String showInfo(String info) {
            System.out.println(123);
            return "A{info=" + info + "}";
        }
        public String studentinfo(){
            System.out.println(student);
            String json=JSON.toJSONString(student);
            return json;
        }
        public String courseList(){
            List<Course> courses=studentService.findAllCanBeSelectedCoursesByStudent(student);
            String json=JSON.toJSONString(courses);
            System.out.println(json);
            return json;
        }
        public String selectedcourseList(){
            List<Course> courses=studentService.findAllSelectedcourseByStudent(student);
            String json=JSON.toJSONString(courses);
            System.out.println(json);
            return json;
        }
        public String selectCourse(Integer courseid){
            String message=studentService.selectCourseByCourseIdAndStudentId(courseid,student.getStudentId());
            System.out.println(message);
            messageController.test(message);
            Main.showView(MessageView.class, Modality.NONE);
            return message;
        }
        public String deleteCourse(Integer courseid){
            return deleteSelectedcourse(studentService.findSelectedcourseByStudentidAndcourseid(student.getStudentId(),courseid));
        }

        public String deleteSelectedcourse(Integer selectedcourseid){
            String message=studentService.deleteSelectedourseBySelectedCourseid(selectedcourseid);
            System.out.println(message);
            messageController.test(message);
            Main.showView(MessageView.class, Modality.NONE);
            return message;
        }

        public void loginout(){
            System.out.println("推出 程序");
            Stage stage=(Stage) webview.getScene().getWindow();
            stage.close();
            messageController.test("exit student system");
            Main.showView(MessageView.class, Modality.NONE);
            Main.showView(LoginView.class, Modality.NONE);
        }
    }
}
