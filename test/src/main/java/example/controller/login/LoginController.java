package example.controller.login;

import example.Main;
import example.entity.Student;
import example.entity.Teacher;
import example.entity.Userlogin;
import example.service.login.LoginService;
import example.view.teacher.TeacherView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import de.felixroske.jfxsupport.FXMLController;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class LoginController implements Initializable {
    public List<Userlogin> listUser;
    public static Student student;
    public static Teacher teacher;
    public static Userlogin user;
    @FXML
    private Pane pane;

    @FXML
    public TextField userField;
    @FXML
    public TextField passwordField;

    final
    protected LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void LogIn(ActionEvent event) throws IOException {
        Button button = (Button)event.getSource();
        Stage stage = (Stage)button.getScene().getWindow();
        int flag = -1;
        flag = isRight();
        if (flag == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "your account or password is wrong!", new ButtonType("取消", ButtonBar.ButtonData.NO),
                    new ButtonType("确定", ButtonBar.ButtonData.YES));
            alert.setTitle("确认");
            alert.showAndWait();

        } else if (flag == 2) {
            stage.close();
            student = loginService.findByStudentId(Integer.parseInt(user.getUserName()));
            Main.showView(TeacherView.class);
        } else if (flag == 1) {
            stage.close();
            teacher = loginService.findByTeacherId(Integer.parseInt(user.getUserName()));
            Main.showView(TeacherView.class);
        } else if (flag == 0) {
            stage.close();
            Main.showView(TeacherView.class);
        }
    }

    public int isRight() {

        for (int i = 0; i < listUser.size(); i++) {
            user = listUser.get(i);

            if (userField.getText().equals(user.getUserName())) {
                if (passwordField.getText().equals(user.getPassword())) {
                    System.out.println(user.getPassword());
                    return user.getRole();
                }

            }
        }
        return -1;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listUser = loginService.findAllUsers();
        Image image = new Image(String.valueOf(getClass().getResource("/login/1.jpg")));
        BackgroundSize backgroundSize = new BackgroundSize(620, 620, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, backgroundSize);
        Background background = new Background(backgroundImage);
        if (image == null) {
            System.exit(1);
        } else if (backgroundImage == null) {
            System.exit(2);
        } else if (background == null) {
            System.exit(3);
        } else if (pane == null) {
            System.exit(4);
        }
        pane.setBackground(background);
    }
}
