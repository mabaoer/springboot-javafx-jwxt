package example.controller;

import de.felixroske.jfxsupport.FXMLController;

import example.Main;
import example.view.TeacherView;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport.getScene;

@FXMLController
public class ToolController {

@Autowired
protected example.service.test test;

    public void doSomething(final Event e) {
        //System.out.println("张宏伟牛逼");
        ((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
        Main.showView(TeacherView.class);
    }
}
