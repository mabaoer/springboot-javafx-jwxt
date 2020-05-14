package example;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import example.view.login.LoginView;
import example.view.student.StudentView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Main.class,  LoginView.class, args);
    }
}
