package example.service.login;

import example.dao.StudentRepository;
import example.entity.Student;
import example.entity.Userlogin;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.dao.UserloginRepository;
import java.awt.*;
import example.dao.*;
import java.util.List;
import example.controller.login.LoginController;
/**
 * @author mbw
 * @date 2020/5/9 21:45
 */
@Service
public class Loginservice {
    public LoginController loginController;
    public Userlogin user;

    @Autowired
    UserloginRepository userdao;

    @Autowired
    StudentRepository studentdao;
    @Autowired
    TeacherRepository teacherdao;
    public Loginservice(UserloginRepository userdao )
    {
        this.userdao=userdao;
    }
}
