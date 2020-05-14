package example.controller.teacher;
/*
created by yzx
 */

import de.felixroske.jfxsupport.FXMLController;
import example.entity.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;

import static de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport.getStage;


@FXMLController
public class TeacherController implements Initializable {
    private ResourceBundle resourceBundle;

    public static Teacher teacher;
    @FXML private TabPane root;
    static private TabPane tabPane;
    private ApplicationContext applicationContext;
    @Autowired
    public TeacherController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static TabPane getTabPane() {
        return tabPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        Stage stage = getStage();
        URL url = getClass().getResource("/teacher/images/background.png");
        BackgroundSize backgroundSize = new BackgroundSize(stage.getWidth(),stage.getHeight(),false,false,false,true);
        root.setBackground(new Background(new BackgroundImage(new Image(String.valueOf(url)),null,null,null,backgroundSize)));
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);
        //stage.setFullScreen(true);
        tabPane = root;

    }

}
