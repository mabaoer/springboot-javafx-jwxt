package example.controller.login;

import example.Main;
import example.dao.StudentRepository;
import example.dao.TeacherRepository;
import example.dao.UserloginRepository;
import example.entity.Student;
import example.entity.Teacher;
import example.entity.Userlogin;
import example.service.login.LoginService;
import example.view.admin.AdminView;
import example.view.student.StudentView;
import example.view.teacher.TeacherView;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.felixroske.jfxsupport.FXMLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
@FXMLController
public class LoginController  {
    public Userlogin user;
    static int a;
    public static Student student;
    public static Teacher teacher;
@FXML
private Pane pane;
    @Autowired
    UserloginRepository userdao;
    @Autowired
    StudentRepository studentdao;
    @Autowired
    TeacherRepository teacherdao;

    @FXML
    public TextField userfield;
    @FXML
    public TextField passwordfield;


int NUM =11;



    @Autowired
    public LoginService loginservice;

    public void showToolWindow(Event event) throws IOException {
        int flag=-1;
                flag=isright();
        if (flag==-1) {
            System.out.println("出错了了了了来了了了了来了了了");

        }
        else if (flag==2) {
            student=studentdao.findByStudentId(Integer.parseInt(user.getUserName()));
            System.out.println("学生来了了了了了来了了了了来了了了");
            Main.getStage().close();
            Main.showView(StudentView.class);
        }
        else if (flag==1) {
            teacher=teacherdao.findByTeacherId(Integer.parseInt(user.getUserName()));
            System.out.println("老师来了了了了来了了了了来了了了");
            Main.getStage().close();
            Main.showView(TeacherView.class);
        }
        else if (flag==0)
        {
            System.out.println("管理员来了了了了来了了了了来了了了");
            Main.getStage().close();
            Main.showView(AdminView.class);
        }
    }


    public int  isright()
    {

        for (int i=1;i<=NUM;i++) {
            user=userdao.findByUserloginId(i);

            System.out.println(user.getUserName());
            System.out.println("111111111");
            System.out.println(userfield.getText());
            if (userfield.getText().equals(user.getUserName())) {
                if (passwordfield.getText().equals(user.getPassword())) {
                    System.out.println("kdsajkdjasjkdjklsaldasdkas");
                    return user.getRole();
                }
            }
        }
        return -1;

    }

    public void initialize(){

        Image image=new Image(String.valueOf(getClass().getResource("/Login/1.jpg")));
        BackgroundSize backgroundSize=new BackgroundSize(620,620,false,false,false,false);
        BackgroundImage backgroundImage=new BackgroundImage(image,null,null,null,backgroundSize);
        Background background=new Background(backgroundImage);
        if(image==null)
        {
            System.exit(1);
        }
        else if(backgroundImage==null)
        {
            System.exit(2);
        }
        else if (background==null)
        {
            System.exit(3);
        }
        else if(pane==null)
        {
            System.exit(4);
        }

        pane.setBackground(background);

    }
}
